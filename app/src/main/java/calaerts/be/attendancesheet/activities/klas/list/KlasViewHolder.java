package calaerts.be.attendancesheet.activities.klas.list;

import android.view.View;
import android.widget.TextView;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.detail.hour.SelectableViewHolder;
import calaerts.be.attendancesheet.model.KlasDB;

public class KlasViewHolder extends SelectableViewHolder<KlasDB> {
    private final View mView;
    private final TextView mIdView;

    public KlasViewHolder(View view) {
        super(view);
        mView = view;
        mIdView = view.findViewById(R.id.id);
    }

    @Override
    public void setSelected(boolean selected) {
        mView.setActivated(selected);
    }

    public void setKlas(final KlasDB klas, final KlasViewHolderListener klasViewHolderListener) {
        super.setItem(klas);
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
