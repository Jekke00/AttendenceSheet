package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import calaerts.be.attendancesheet.model.Hour;

abstract class HourViewHolder extends RecyclerView.ViewHolder {
    public HourViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setHour(Hour hour, OnHourListInteraction onHourListInteraction);
}
