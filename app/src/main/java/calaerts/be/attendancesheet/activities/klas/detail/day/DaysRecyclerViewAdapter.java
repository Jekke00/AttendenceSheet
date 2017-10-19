package calaerts.be.attendancesheet.activities.klas.detail.day;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.model.DayOfWeek;

public class DaysRecyclerViewAdapter extends RecyclerView.Adapter<DaysRecyclerViewAdapter.ViewHolder> {

    private final DayListFragment.OnListFragmentInteractionListener mListener;
    private List<DayOfWeek> days = new ArrayList<>();

    public DaysRecyclerViewAdapter(DayListFragment.OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_day, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = days.get(position);
        holder.mIdView.setText(days.get(position).toString());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onDayInteracted(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public void setDays(List<DayOfWeek> days) {
        this.days = days;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DayOfWeek mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView =  view.findViewById(R.id.id);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
