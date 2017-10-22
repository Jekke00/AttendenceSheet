package calaerts.be.attendancesheet.activities.klas.student;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import calaerts.be.attendancesheet.model.StudentDb;

public abstract class AbstractStudentRecyclerViewAdapter extends RecyclerView.Adapter<ModifyStudentListViewHolder> {
    private StudentInteractionListener listener;
    private List<StudentDb> students = new ArrayList<>();

    AbstractStudentRecyclerViewAdapter(StudentInteractionListener listener) {
        this.listener = listener;
    }

    public void setStudents(List<StudentDb> students) {
        this.students = students;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    @Override
    public void onBindViewHolder(ModifyStudentListViewHolder holder, int position) {
        holder.setStudent(students.get(position), listener);
    }


}
