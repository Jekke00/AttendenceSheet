package calaerts.be.attendancesheet.activities.klas.list;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.detail.hour.SelectableViewHolder;
import calaerts.be.attendancesheet.model.KlasDB;

public class KlasViewHolder extends SelectableViewHolder {
    private final View mView;
    private final TextView mIdView;

    public KlasDB klas;

    public KlasViewHolder(View view) {
        super(view);
        mView = view;
        mIdView = view.findViewById(R.id.id);
    }

    @Override
    public void setSelected(boolean selected) {
        mView.setBackgroundColor(selected ? Color.GRAY : Color.WHITE);
    }

    public void setKlas(final KlasDB klas, final KlasViewHolderListener klasViewHolderListener) {
        this.klas = klas;
        mIdView.setText(klas.getName());

        mView.setOnClickListener(v -> {
            if (klasViewHolderListener != null) {
                klasViewHolderListener.onKlasSelected(klas);
            }
        });
        mView.setOnLongClickListener(view -> {
            klasViewHolderListener.onKlasLongClicked(klas);
            return true;
        });
    }
}
