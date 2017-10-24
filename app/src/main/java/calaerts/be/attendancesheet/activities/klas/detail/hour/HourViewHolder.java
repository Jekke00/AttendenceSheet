package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.view.View;

import calaerts.be.attendancesheet.model.Hour;

public abstract class HourViewHolder extends SelectableViewHolder {
    public HourViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setHour(Hour hour, OnHourListInteraction onHourListInteraction);
}
