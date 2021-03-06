package calaerts.be.attendancesheet.activities.klas.student;

import android.view.View;
import android.widget.TextView;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.detail.hour.SelectableViewHolder;
import calaerts.be.attendancesheet.model.StudentDb;

public class ModifyStudentListViewHolder extends SelectableViewHolder<StudentDb> {
    public final View mainView;
    public final TextView contentView;

    public ModifyStudentListViewHolder(View view) {
        super(view);
        mainView = view;
        contentView = view.findViewById(R.id.id);
    }

    @Override
    public void setSelected(boolean selected) {
        mainView.setActivated(selected);
    }

    public void setStudent(final StudentDb student, final StudentInteractionListener studentInteractionListener) {
        super.setItem(student);
        this.contentView.setText(student.getName());
        this.mainView.setOnClickListener(view -> studentInteractionListener.onStudentSelected(student));
    }
}
