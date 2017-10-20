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

    public AttendanceHourViewHolder(View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.hourId);
        view = itemView;

    }

    @Override
    public void setHour(final Hour hour, final OnHourListInteraction onHourListInteraction) {
        this.textView.setText(Integer.toString(hour.getHour()));
        this.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != onHourListInteraction) {
                    onHourListInteraction.onListFragmentInteraction(hour);
                }
            }
        });
    }
}
