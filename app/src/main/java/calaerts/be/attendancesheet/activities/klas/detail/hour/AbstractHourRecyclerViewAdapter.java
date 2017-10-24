package calaerts.be.attendancesheet.activities.klas.detail.hour;

import calaerts.be.attendancesheet.activities.klas.student.AbstractSelectableRecyclerViewAdapter;
import calaerts.be.attendancesheet.model.Hour;

public abstract class AbstractHourRecyclerViewAdapter<VH extends HourViewHolder> extends AbstractSelectableRecyclerViewAdapter<Hour, VH> {
    private final OnHourListInteraction listener;

    public AbstractHourRecyclerViewAdapter(OnHourListInteraction listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setHour(getData().get(position), listener);
    }

}
