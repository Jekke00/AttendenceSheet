package calaerts.be.attendancesheet.activities.klas.student;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.model.StudentDb;

public class ModifyStudentListViewHolder extends RecyclerView.ViewHolder {
    public final View mainView;
    public final TextView contentView;
    public StudentDb mItem;

    public ModifyStudentListViewHolder(View view) {
        super(view);
        mainView = view;
        contentView = view.findViewById(R.id.content);
    }

    public void setStudent(final StudentDb student, final StudentInteractionListener studentInteractionListener) {
        this.contentView.setText(student.getName());
        this.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentInteractionListener.onStudentSelected(student);
            }
        });
    }
}
