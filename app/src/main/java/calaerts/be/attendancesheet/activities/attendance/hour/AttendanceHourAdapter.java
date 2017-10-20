package calaerts.be.attendancesheet.activities.attendance.hour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.detail.hour.AbstractHourRecyclerViewAdapter;
import calaerts.be.attendancesheet.activities.klas.detail.hour.OnHourListInteraction;


public class AttendanceHourAdapter extends AbstractHourRecyclerViewAdapter<AttendanceHourViewHolder> {
    public AttendanceHourAdapter(OnHourListInteraction listener) {
        super(listener);
    }

    @Override
    public AttendanceHourViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_hour_attendance, viewGroup, false);
        return new AttendanceHourViewHolder(view);
    }
}
