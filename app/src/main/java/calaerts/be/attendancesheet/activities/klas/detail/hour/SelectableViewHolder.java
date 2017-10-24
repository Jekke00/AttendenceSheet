package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.support.v7.widget.RecyclerView;
import android.view.View;

public abstract class SelectableViewHolder extends RecyclerView.ViewHolder {
    public SelectableViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void setSelected(boolean selected);
}
