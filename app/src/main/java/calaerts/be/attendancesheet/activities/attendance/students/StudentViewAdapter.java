package calaerts.be.attendancesheet.activities.attendance.students;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.joda.time.LocalDate;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.attendance.students.StudentFragment.OnListFragmentInteractionListener;
import calaerts.be.attendancesheet.activities.klas.student.AbstractSelectableRecyclerViewAdapter;
import calaerts.be.attendancesheet.model.Hour;
import calaerts.be.attendancesheet.model.Student;

public class StudentViewAdapter extends AbstractSelectableRecyclerViewAdapter<Student, StudentViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    private LocalDate currentDate = new LocalDate();
    private Hour currentHour;

    public StudentViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_student_selecteable, parent, false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StudentViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.mItem = getData().get(position);
        holder.mView.setBackgroundColor(holder.mItem.getColor());
        holder.checkBox.setText(getData().get(position).getName());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) mListener.onListFragmentInteraction(holder.mItem);
            }
        });
    }

    public void setData(List<Student> students) {
        Collections.sort(students);
        setMissedStudentsAsSelected(students);
        super.setData(students);
    }

    private void setMissedStudentsAsSelected(List<Student> students) {
        Set<Student> missedStudent = new HashSet<>();
        for (Student student : students) {
            if (student.hasMissedAttendanceAtDate(currentDate, currentHour))
                missedStudent.add(student);
        }
        super.setSelected(missedStudent);
    }

    public void setDate(LocalDate localDate) {
        currentDate = localDate == null ? new LocalDate() : localDate;
        notifyDataSetChanged();
    }

    public void setCurrentHour(Hour hour) {
        currentHour = hour;
        notifyDataSetChanged();
    }

}
