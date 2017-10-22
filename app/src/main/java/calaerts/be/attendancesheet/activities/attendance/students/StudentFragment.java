package calaerts.be.attendancesheet.activities.attendance.students;

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

import java.util.List;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.attendance.AttendanceViewModel;
import calaerts.be.attendancesheet.model.MissedAttendance;
import calaerts.be.attendancesheet.model.Student;
import calaerts.be.attendancesheet.repository.MissedAttendanceDao;

public class StudentFragment extends Fragment {

    @Inject
    AttendanceViewModel attendanceViewModel;
    @Inject
    MissedAttendanceDao missedAttendanceDao;
    private StudentViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_list_selecteable, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new StudentViewAdapter(new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(Student item) {
                onStudentSelected(item);
            }
        });
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void onStudentSelected(Student item) {
        if (item.getMissedAttendances().isEmpty()) {
            final MissedAttendance missedAttendance = new MissedAttendance(item.getId(), attendanceViewModel.selectedHour().getValue(), attendanceViewModel.selectedDate().getValue());
            missedAttendanceDao.saveAttendance(missedAttendance);
        } else {
            missedAttendanceDao.deleteMissedAttendance(item.getMissedAttendances().get(0));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attendanceViewModel.getStudents().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                onStudentsUpdates(students);
            }
        });
    }

    private void onStudentsUpdates(List<Student> studentList) {
        adapter.setStudents(studentList);
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Student item);
    }
}
