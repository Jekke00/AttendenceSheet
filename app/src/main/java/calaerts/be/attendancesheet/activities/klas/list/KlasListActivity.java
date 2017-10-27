package calaerts.be.attendancesheet.activities.klas.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.AbstractAttendanceAppActivity;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.activities.klas.detail.ClassDetailActivity;
import calaerts.be.attendancesheet.activities.klas.detail.ClassDetailFragment;
import calaerts.be.attendancesheet.activities.klas.newKlas.KlasDetailActivity;
import calaerts.be.attendancesheet.activities.klas.student.StudentDetail;
import calaerts.be.attendancesheet.model.Klas;
import calaerts.be.attendancesheet.model.KlasDB;
import calaerts.be.attendancesheet.model.StudentDb;

public class KlasListActivity extends AbstractAttendanceAppActivity implements KlasViewHolderListener {
    @Inject
    KlasListViewModel klasViewModel;
    private boolean isTwoPane;
    private KlasRecycleViewAdapter adapter;
    private Klas currentKlas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getApplication()).getAppComponent().inject(this);
        setupViews();
        klasViewModel.allKlassen().observe(this, klassen -> adapter.setData(klassen));
        klasViewModel.getSelectedKlas().observe(this, this::klasChanged);
        klasViewModel.selectedStudent().observe(this, this::onStudentSelected);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.removeItem(R.id.manageKlas);
        return true;
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
        fab.setOnClickListener(view -> startNewKlasActivity());
    }

    private void startNewKlasActivity() {
        Intent intent = new Intent(this, KlasDetailActivity.class);
        startActivity(intent);
    }


    private void setupRecyclerView() {
        adapter = new KlasRecycleViewAdapter();
        adapter.setKlasViewHolderListener(this);
        RecyclerView recyclerView = findViewById(R.id.class_list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onKlasSelected(KlasDB klas) {
        klasViewModel.setSelectedKlas(klas.getId());
    }

    @Override
    public void onKlasLongClicked(KlasDB klas) {
        Intent intent = new Intent(this, KlasDetailActivity.class);
        intent.putExtra("klas", klas);
        startActivity(intent);
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
        adapter.setSelected(klas.getKlasDb());
    }

    private void changePaneToStudent(StudentDb student) {
        StudentDetail fragment = new StudentDetail();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.class_detail_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void onStudentSelected(@Nullable StudentDb student) {
        if (student != null)
            changePaneToStudent(student);
        else
            alwaysChangeToKlas(klasViewModel.getSelectedKlas().getValue());
    }
}
