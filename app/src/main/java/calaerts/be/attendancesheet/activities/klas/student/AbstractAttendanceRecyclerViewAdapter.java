package calaerts.be.attendancesheet.activities.klas.student;

import android.support.v7.widget.RecyclerView;

import java.util.List;

public abstract class AbstractAttendanceRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    private List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
