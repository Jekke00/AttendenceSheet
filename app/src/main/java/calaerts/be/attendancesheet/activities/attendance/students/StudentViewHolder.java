package calaerts.be.attendancesheet.activities.attendance.students;

import android.view.View;
import android.widget.CheckBox;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.detail.hour.SelectableViewHolder;
import calaerts.be.attendancesheet.model.Student;

public class StudentViewHolder extends SelectableViewHolder {
    public final View mView;
    public final CheckBox checkBox;
    public Student mItem;

    public StudentViewHolder(View view) {
        super(view);
        mView = view;
        checkBox = view.findViewById(R.id.studentCheckBox);
    }

    @Override
    public void setSelected(boolean selected) {
        checkBox.setSelected(selected);
    }
}
