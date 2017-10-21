package calaerts.be.attendancesheet.activities.attendance.students;

import android.arch.core.util.Function;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.activities.attendance.AttendanceViewModel;
import calaerts.be.attendancesheet.activities.klas.student.AbstractStudentListFragment;
import calaerts.be.attendancesheet.activities.klas.student.AbstractStudentRecyclerViewAdapter;
import calaerts.be.attendancesheet.activities.klas.student.StudentInteractionListener;
import calaerts.be.attendancesheet.model.Student;

public class AttendanceStudentList extends AbstractStudentListFragment {
    @Inject
    AttendanceViewModel attendanceViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onStudentSelected(Student student) {

    }

    @Override
    public Function<StudentInteractionListener, AbstractStudentRecyclerViewAdapter> getAdapterFactory() {
        return new Function<StudentInteractionListener, AbstractStudentRecyclerViewAdapter>() {
            @Override
            public AbstractStudentRecyclerViewAdapter apply(StudentInteractionListener studentInteractionListener) {
                return null;
            }
        };
    }
}
