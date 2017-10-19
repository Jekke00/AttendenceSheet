package calaerts.be.attendancesheet.activities.klas.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.model.KlasDB;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {
    private List<KlasDB> klassen = new ArrayList<>();
    private KlasViewHolderListener klasViewHolderListener;

    public SimpleItemRecyclerViewAdapter() {
    }

    public void setKlasViewHolderListener(KlasViewHolderListener klasViewHolderListener) {
        this.klasViewHolderListener = klasViewHolderListener;
    }

    public void setKlassen(List<KlasDB> klassen) {
        this.klassen = klassen;
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.klas = klassen.get(position);
        holder.mIdView.setText(klassen.get(position).getName());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (klasViewHolderListener != null) {
                    klasViewHolderListener.onKlasSelected(holder.klas);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return klassen.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mIdView;

        public KlasDB klas;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
        }
    }
}
