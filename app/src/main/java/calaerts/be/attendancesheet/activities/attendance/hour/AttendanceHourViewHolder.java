package calaerts.be.attendancesheet.activities.attendance.hour;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.detail.hour.HourViewHolder;
import calaerts.be.attendancesheet.activities.klas.detail.hour.OnHourListInteraction;
import calaerts.be.attendancesheet.model.Hour;

public class AttendanceHourViewHolder extends HourViewHolder {
    private final TextView textView;
    private final View view;

    public AttendanceHourViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.hourId);
        view = itemView;

    }

    @Override
    public void setSelected(boolean selected) {
        view.setBackgroundColor(selected ? Color.GRAY : Color.WHITE);
    }

    @Override
    public void setHour(final Hour hour, final OnHourListInteraction onHourListInteraction) {
        this.textView.setText(Integer.toString(hour.getHour()));
        this.view.setOnClickListener(view -> {
            if (null != onHourListInteraction) {
                onHourListInteraction.onListFragmentInteraction(hour);
            }
        });
    }
}
