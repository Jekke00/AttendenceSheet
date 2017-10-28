package calaerts.be.attendancesheet.activities.klas.detail.day;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.detail.hour.SelectableViewHolder;
import calaerts.be.attendancesheet.activities.klas.student.AbstractSelectableRecyclerViewAdapter;
import calaerts.be.attendancesheet.model.DayOfWeek;

public class DaysRecyclerViewAdapter extends AbstractSelectableRecyclerViewAdapter<DayOfWeek, DaysRecyclerViewAdapter.ViewHolder> {

    private final DayListFragment.OnListFragmentInteractionListener mListener;

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
        holder.setItem(getData().get(position));
        holder.mView.setOnClickListener(v -> {
            if (null != mListener) {
                mListener.onDayInteracted(holder.getItem());
            }
        });
        super.onBindViewHolder(holder, position);
    }

    public class ViewHolder extends SelectableViewHolder<DayOfWeek> {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public void setSelected(boolean selected) {
            mView.setBackgroundColor(selected ? Color.GRAY : Color.WHITE);
        }

        @Override
        public void setItem(DayOfWeek Item) {
            super.setItem(Item);
            mIdView.setText(getItem().toString());
        }
    }
}
