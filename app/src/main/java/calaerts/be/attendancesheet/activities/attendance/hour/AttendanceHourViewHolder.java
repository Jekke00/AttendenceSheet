package calaerts.be.attendancesheet.activities.attendance.hour;

import android.view.View;
import android.widget.TextView;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.detail.hour.HourViewHolder;
import calaerts.be.attendancesheet.activities.klas.detail.hour.OnHourListInteraction;
import calaerts.be.attendancesheet.model.Hour;

public class AttendanceHourViewHolder extends HourViewHolder {
    private final TextView textView;
    private final View view;
    private Hour hour;

    public AttendanceHourViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        textView = itemView.findViewById(R.id.id);

    }

    @Override
    public void setSelected(boolean selected) {
        view.setActivated(selected);
    }

    @Override
    public Hour getItem() {
        return hour;
    }

    @Override
    public void setHour(final Hour hour, final OnHourListInteraction onHourListInteraction) {
        super.setHour(hour, onHourListInteraction);
        this.textView.setText(Integer.toString(hour.getHour()));
        this.hour = hour;
        this.view.setOnClickListener(view -> {
            if (null != onHourListInteraction) {
                onHourListInteraction.onListFragmentInteraction(hour);
            }
        });
    }
}
