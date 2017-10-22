package calaerts.be.attendancesheet.activities.klas.student;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.model.Klas;
import calaerts.be.attendancesheet.model.StudentDb;


public class StudentListContainer extends Fragment {
    @Inject
    KlasListViewModel klasViewModel;
    private AbstractStudentListFragment studentList;
    private Klas klas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_list_container, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        klasViewModel.getSelectedKlas().observe(this, new Observer<Klas>() {
            @Override
            public void onChanged(@Nullable Klas klas) {
                onKlasUpdated(klas);
            }
        });
        setupNewStudentButton(view);
    }

    private void setupNewStudentButton(View view) {
        final Button button = view.findViewById(R.id.newStudentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNewStudentClicked();
            }
        });
    }

    private void onKlasUpdated(@Nullable Klas klas) {
        this.klas = klas;
    }

    public void onNewStudentClicked() {
        StudentDb student = new StudentDb();
        student.setKlasId(klas.getId());
        klasViewModel.selectStudent(student);
    }
}
