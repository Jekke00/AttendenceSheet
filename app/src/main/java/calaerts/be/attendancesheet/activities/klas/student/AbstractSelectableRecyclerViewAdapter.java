package calaerts.be.attendancesheet.activities.klas.student;

import java.util.HashSet;
import java.util.Set;

import calaerts.be.attendancesheet.activities.klas.detail.hour.SelectableViewHolder;

public abstract class AbstractSelectableRecyclerViewAdapter<T, VH extends SelectableViewHolder<T>> extends AbstractAttendanceRecyclerViewAdapter<T, VH> {
    private Set<T> selected = new HashSet<>();

    public void setSelected(Set<T> selected) {
        this.selected = selected;

    }

    public void setSelected(T selected) {
        this.selected.clear();
        this.selected.add(selected);
        notifyDataSetChanged();
    }

    public void clearSelected() {
        this.selected.clear();
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(VH vh, int i) {
        vh.setSelected(selected.contains(vh.getItem()));
    }

}
