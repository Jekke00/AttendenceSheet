package calaerts.be.attendencesheet;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import calaerts.be.attendencesheet.HourFragment.OnListFragmentInteractionListener;
import calaerts.be.attendencesheet.dummy.DummyContent.DummyItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyHourRecyclerViewAdapter extends RecyclerView.Adapter<MyHourRecyclerViewAdapter.ViewHolder> {

    private List<Hour> hours = new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;

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
        holder.mIdView.setText(hour.getHour()+ "");
        holder.checkBox.setSelected(hour.isSelected());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return hours.size();
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

    public void setHours(List<Hour> hours) {
        this.hours = hours;
        notifyDataSetChanged();
    }
}
