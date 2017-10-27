package calaerts.be.attendancesheet.activities.klas.student;

import java.util.Collections;
import java.util.List;

import calaerts.be.attendancesheet.model.StudentDb;

public abstract class AbstractStudentRecyclerViewAdapter extends AbstractSelectableRecyclerViewAdapter<StudentDb, ModifyStudentListViewHolder> {
    private final StudentInteractionListener listener;

    AbstractStudentRecyclerViewAdapter(StudentInteractionListener listener) {
        this.listener = listener;
    }

    @Override
    public void setData(List<StudentDb> data) {
        Collections.sort(data, (studentDb, t1) -> studentDb.getName().compareTo(t1.getName()));
        super.setData(data);
    }

    @Override
    public void onBindViewHolder(ModifyStudentListViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setStudent(getData().get(position), listener);
    }
}
