package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calaerts.be.attendancesheet.R;

public class HourRecyclerViewAdapter extends AbstractHourRecyclerViewAdapter<HourViewHolder> {

    public HourRecyclerViewAdapter(OnHourListInteraction listener) {
        super(listener);
    }

    @Override
    public HourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_hour, parent, false);
        return new KlasHourViewHolder(view);
    }
}
