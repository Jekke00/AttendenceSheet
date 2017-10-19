package calaerts.be.attendancesheet.activities.klas.list;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.AbstractAttendanceAppActivity;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.activities.klas.detail.ClassDetailActivity;
import calaerts.be.attendancesheet.activities.klas.detail.ClassDetailFragment;
import calaerts.be.attendancesheet.activities.klas.newKlas.NewKlasActivity;
import calaerts.be.attendancesheet.activities.klas.student.StudentDetail;
import calaerts.be.attendancesheet.model.Klas;
import calaerts.be.attendancesheet.model.KlasDB;
import calaerts.be.attendancesheet.model.Student;

public class KlasListActivity extends AbstractAttendanceAppActivity implements KlasViewHolderListener {
    @Inject
    KlasListViewModel klasViewModel;
    private boolean isTwoPane;
    private SimpleItemRecyclerViewAdapter adapter;
    private Klas currentKlas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getApplication()).getAppComponent().inject(this);
        setupViews();
        klasViewModel.allKlassen().observe(this, new Observer<List<KlasDB>>() {
            @Override
            public void onChanged(@Nullable List<KlasDB> klases) {
                adapter.setKlassen(klases);
            }
        });
        klasViewModel.getSelectedKlas().observe(this, new Observer<Klas>() {
            @Override
            public void onChanged(@Nullable Klas klas) {
                klasChanged(klas);
            }
        });
        klasViewModel.selectedStudent().observe(this, new Observer<Student>() {
            @Override
            public void onChanged(@Nullable Student student) {
                onStudentSelected(student);
            }
        });

    }

    private void setupViews() {
        setupActionButton();
        if (findViewById(R.id.class_detail_container) != null) {
            isTwoPane = true;
            setupRecyclerView();
        }
    }

    protected int getContentView() {
        return R.layout.activity_class_list;
    }

    private void setupActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewKlasActivity();
            }
        });
    }

    private void startNewKlasActivity() {
        Intent intent = new Intent(this, NewKlasActivity.class);
        startActivity(intent);
    }


    private void setupRecyclerView() {
        adapter = new SimpleItemRecyclerViewAdapter();
        adapter.setKlasViewHolderListener(this);
        RecyclerView recyclerView = findViewById(R.id.class_list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onKlasSelected(KlasDB klas) {
        klasViewModel.setSelectedKlas(klas.getId());
    }

    private void klasChanged(Klas klas) {
        if (isTwoPane) {
            changePaneToKlas(klas);
        } else {
            openDetailActivity(klas);
        }
    }

    private void openDetailActivity(Klas klas) {
        Intent intent = new Intent(this, ClassDetailActivity.class);
        intent.putExtra(ClassDetailFragment.KLAS_ID, klas.getId());
        this.startActivity(intent);
    }

    private void changePaneToKlas(Klas klas) {
        if (currentKlas != null && currentKlas.getId() == klas.getId()) {
            return;
        }
        alwaysChangeToKlas(klas);
    }

    private void alwaysChangeToKlas(Klas klas) {
        Bundle arguments = new Bundle();
        arguments.putInt(ClassDetailFragment.KLAS_ID, klas.getId());
        ClassDetailFragment fragment = new ClassDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.class_detail_container, fragment)
                .commit();
        currentKlas = klas;
    }

    private void changePaneToStudent(Student student) {
        StudentDetail fragment = new StudentDetail();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.class_detail_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void onStudentSelected(@Nullable Student student) {
        if (student != null)
            changePaneToStudent(student);
        else
            alwaysChangeToKlas(klasViewModel.getSelectedKlas().getValue());
    }
}
