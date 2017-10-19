package calaerts.be.attendancesheet.activities.klas.student;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.model.Klas;
import calaerts.be.attendancesheet.model.Student;

public class StudentListFragment extends Fragment implements StudentInteractionListener {

    @Inject
    KlasListViewModel klasViewModel;
    private MyStudentRecyclerViewAdapter adapter;
    private Klas klas;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);
        klasViewModel.getSelectedKlas().observe(this, new Observer<Klas>() {
            @Override
            public void onChanged(@Nullable Klas klas) {
                updateWithKlas(klas);
            }
        });
    }

    private void updateWithKlas(@Nullable Klas klas) {
        this.klas = klas;
        if (klas != null && adapter != null) {
            adapter.setStudents(klas.getStudents());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        setupRecyclerView(view);
        final Button button = view.findViewById(R.id.newStudentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNewStudentClicked();
            }
        });
        return view;
    }

    private void setupRecyclerView(View view) {
        Context context = view.getContext();
        RecyclerView recyclerView = view.findViewById(R.id.studentList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyStudentRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
        updateWithKlas(klasViewModel.getSelectedKlas().getValue());
    }

    @Override
    public void onStudentSelected(Student student) {
        klasViewModel.selectStudent(student);
    }

    public void onNewStudentClicked() {
        Student student = new Student();
        student.setKlasId(klas.getId());
        klasViewModel.selectStudent(student);
    }
}
