package calaerts.be.attendancesheet.activities.klas.student;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.model.Student;
import calaerts.be.attendancesheet.repository.StudentDao;

public class StudentDetail extends Fragment {
    @Inject
    KlasListViewModel klasViewModel;
    @Inject
    StudentDao studentDao;
    private EditText nameEditText;
    private Student student;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);
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
                saveStudent();
            }
        });
        return view;
    }

    private void saveStudent() {
        final Editable name = nameEditText.getText();
        if (name.toString().equals("")) {
            return;
        }
        saveStudent(name);

        closeKeyboard();
    }

    private void saveStudent(Editable name) {
        student.setKlasId(klasViewModel.getSelectedKlas().getValue().getId());
        student.setName(name.toString());
        studentDao.insertStudent(student);
        klasViewModel.selectStudent(null);
    }

    private void closeKeyboard() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) getActivity().getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    private void updateStudent(Student student) {
        this.student = student;
        if (this.student != null && nameEditText != null) {
            nameEditText.setText(student.getName());
        }
    }
}
