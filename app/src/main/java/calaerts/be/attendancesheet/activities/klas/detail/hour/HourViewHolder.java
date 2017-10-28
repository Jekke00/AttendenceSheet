package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.view.View;

import calaerts.be.attendancesheet.model.Hour;

public abstract class HourViewHolder extends SelectableViewHolder<Hour> {
    public HourViewHolder(View itemView) {
        super(itemView);
    }

    public void setHour(Hour hour, OnHourListInteraction onHourListInteraction) {
        this.setItem(hour);
    }
}
