package calaerts.be.attendencesheet.activities.klas.student;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import calaerts.be.attendencesheet.AttendenceApp;
import calaerts.be.attendencesheet.KlasListViewModel;
import calaerts.be.attendencesheet.R;
import calaerts.be.attendencesheet.model.Student;
import calaerts.be.attendencesheet.repository.StudentDao;

public class StudentDetail extends Fragment {
    @Inject KlasListViewModel klasViewModel;
    private EditText nameEditText;
    @Inject
    StudentDao studentDao;
    private Student student;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendenceApp) getActivity().getApplication()).getAppComponent().inject(this);
        klasViewModel.selectedStudent().observe(this, new Observer<Student>() {
            @Override
            public void onChanged(@Nullable Student student) {
                updateStudent(student);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_student_detail, container, false);
        nameEditText = view.findViewById(R.id.studentName);
        updateStudent(klasViewModel.selectedStudent().getValue());
        view.findViewById(R.id.saveStudentButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Editable name = nameEditText.getText();
                if(!name.equals("")){
                    student.setKlasId(klasViewModel.getSelectedKlas().getValue().getId());
                    student.setName(name.toString());
                    studentDao.insertStudent(student);
                    klasViewModel.selectStudent(null);
                }
            }
        });
        return view;
    }

    private void updateStudent(Student student){
        this.student = student;
        if (this.student != null && nameEditText != null) {
            nameEditText.setText(student.getName());
        }
    }
}
