package calaerts.be.attendancesheet.activities.attendance.students;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.LocalDate;

import java.util.List;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.attendance.AttendanceViewModel;
import calaerts.be.attendancesheet.model.Hour;
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
        adapter = new StudentViewAdapter(this::onStudentSelected);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void onStudentSelected(Student item) {
        final LocalDate date = attendanceViewModel.selectedDate().getValue();
        final Hour hour = attendanceViewModel.selectedHour().getValue();
        if (!item.hasMissedAttendanceAtDate(date, hour)) {
            final MissedAttendance missedAttendance = new MissedAttendance(item.getId(), hour, date);
            missedAttendanceDao.saveAttendance(missedAttendance);
        } else {
            missedAttendanceDao.deleteMissedAttendance(item.getMissedAttendanceAtDate(date, hour));
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        attendanceViewModel.getStudents().observe(this, this::onStudentsUpdates);
        attendanceViewModel.selectedDate().observe(this, localDate -> adapter.setDate(localDate));
        attendanceViewModel.selectedHour().observe(this, hour -> adapter.setCurrentHour(hour));
    }

    private void onStudentsUpdates(List<Student> studentList) {
        adapter.setData(studentList);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Student item);
    }
}
