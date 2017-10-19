package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import calaerts.be.attendancesheet.model.Hour;

abstract class AbstractHourRecyclerViewAdapter<T extends HourViewHolder> extends RecyclerView.Adapter<T> {
    private final OnHourListInteraction mListener;
    private List<Hour> hours = new ArrayList<>();

    AbstractHourRecyclerViewAdapter(OnHourListInteraction listener) {
        mListener = listener;
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    private List<Hour> getHours() {
        return hours;
    }

    void setHours(List<Hour> hours) {
        this.hours = hours;
        notifyDataSetChanged();
    }

    private OnHourListInteraction getListener() {
        return mListener;
    }

    @Override
    public void onBindViewHolder(T holder, int position) {
        final Hour hour = getHours().get(position);
        holder.setHour(hour, getListener());
    }

}
