package calaerts.be.attendancesheet.activities.klas.student;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.model.Klas;
import calaerts.be.attendancesheet.model.StudentDb;

public class StudentListFragment extends AbstractStudentListFragment {

    @Inject
    KlasListViewModel klasViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        klasViewModel.getSelectedKlas().observe(this, this::onKlasUpdated);
    }

    private void onKlasUpdated(Klas klas) {
        setStudents(klas.getStudents());
    }


    @Override
    public void onStudentSelected(StudentDb student) {
        klasViewModel.selectStudent(student);
    }


}
