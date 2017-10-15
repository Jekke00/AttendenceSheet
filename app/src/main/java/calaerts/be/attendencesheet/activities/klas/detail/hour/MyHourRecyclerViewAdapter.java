package calaerts.be.attendencesheet.activities.klas.detail.hour;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import calaerts.be.attendencesheet.R;
import calaerts.be.attendencesheet.activities.klas.detail.hour.HourFragment.OnListFragmentInteractionListener;
import calaerts.be.attendencesheet.model.Hour;

public class MyHourRecyclerViewAdapter extends RecyclerView.Adapter<MyHourRecyclerViewAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    private List<Hour> hours = new ArrayList<>();

    public MyHourRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_hour, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Hour hour = hours.get(position);
        holder.mItem = hour;
        holder.checkBox.setText(Integer.toString(hour.getHour()));
        holder.checkBox.setChecked(hour.isSelected());

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hours.size();
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final CheckBox checkBox;
        public Hour mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.hourId);
            checkBox = view.findViewById(R.id.checkBox);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + checkBox.getText() + "'";
        }
    }
}
