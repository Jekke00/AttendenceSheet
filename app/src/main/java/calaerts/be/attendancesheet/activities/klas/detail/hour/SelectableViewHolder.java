package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class SelectableViewHolder<T> extends RecyclerView.ViewHolder {
    private T item;

    public SelectableViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setSelected(boolean selected);

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}
