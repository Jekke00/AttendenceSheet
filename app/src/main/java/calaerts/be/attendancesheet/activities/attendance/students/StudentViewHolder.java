package calaerts.be.attendancesheet.activities.attendance.students;

import android.view.View;
import android.widget.CheckBox;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.detail.hour.SelectableViewHolder;
import calaerts.be.attendancesheet.model.Student;

public class StudentViewHolder extends SelectableViewHolder<Student> {
    public final View mView;
    public final CheckBox checkBox;

    public StudentViewHolder(View view) {
        super(view);
        mView = view;
        checkBox = view.findViewById(R.id.studentCheckBox);
    }

    @Override
    public void setItem(Student Item) {
        super.setItem(Item);
        this.mView.setBackgroundColor(getItem().getColor());
        this.checkBox.setText(getItem().getName());
    }

    @Override
    public void setSelected(boolean selected) {
        checkBox.setChecked(selected);
    }
}
