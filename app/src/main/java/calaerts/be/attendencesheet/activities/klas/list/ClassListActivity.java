package calaerts.be.attendencesheet.activities.klas.list;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import javax.inject.Inject;

import calaerts.be.attendencesheet.AttendenceApp;
import calaerts.be.attendencesheet.KlasListViewModel;
import calaerts.be.attendencesheet.R;
import calaerts.be.attendencesheet.activities.klas.detail.ClassDetailActivity;
import calaerts.be.attendencesheet.activities.klas.detail.ClassDetailFragment;
import calaerts.be.attendencesheet.activities.klas.newKlas.NewKlasActivity;
import calaerts.be.attendencesheet.activities.klas.student.StudentDetail;
import calaerts.be.attendencesheet.model.Klas;
import calaerts.be.attendencesheet.model.KlasDB;
import calaerts.be.attendencesheet.model.Student;

public class ClassListActivity extends AppCompatActivity implements KlasViewHolderListener {
    private boolean isTwoPane;
    private SimpleItemRecyclerViewAdapter adapter;
    @Inject
    KlasListViewModel klasViewModel;
    private Klas currentKlas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendenceApp) getApplication()).getAppComponent().inject(this);
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
        setContentView(R.layout.activity_class_list);
        setupToolbar();
        setupActionButton();

        if (findViewById(R.id.class_detail_container) != null) {
            isTwoPane = true;
            setupRecyclerView();
        }
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

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
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
        if(currentKlas != null && currentKlas.getId() == klas.getId()){
            return;
        }
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
            changePaneToKlas(klasViewModel.getSelectedKlas().getValue());
    }


}
