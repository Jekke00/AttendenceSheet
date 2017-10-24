package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.model.Hour;

public class ManageHourRecyclerViewAdapter extends AbstractHourRecyclerViewAdapter<KlasHourViewHolder> {

    public ManageHourRecyclerViewAdapter(OnHourListInteraction listener) {
        super(listener);
    }

    @Override
    public void setData(List<Hour> data) {
        super.setData(data);
        Set<Hour> selectedHours = new HashSet<>();
        for (Hour hour : data) {
            if (hour.isSelected()) {
                selectedHours.add(hour);
            }
        }
        setSelected(selectedHours);
    }

    @Override
    public KlasHourViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_hour, parent, false);
        return new KlasHourViewHolder(view);
    }
}