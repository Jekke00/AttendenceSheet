package calaerts.be.attendancesheet.activities.klas.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.student.AbstractSelectableRecyclerViewAdapter;
import calaerts.be.attendancesheet.model.KlasDB;

public class KlasRecycleViewAdapter extends AbstractSelectableRecyclerViewAdapter<KlasDB, KlasViewHolder> {
    private KlasViewHolderListener klasViewHolderListener;

    public void setKlasViewHolderListener(KlasViewHolderListener klasViewHolderListener) {
        this.klasViewHolderListener = klasViewHolderListener;
    }

    @Override
    public KlasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_list_content, parent, false);
        return new KlasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final KlasViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setKlas(getData().get(position), klasViewHolderListener);
    }

}
