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
        super.onBindViewHolder(holder, position);
        holder.mItem = getData().get(position);
        holder.mIdView.setText(getData().get(position).toString());
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onDayInteracted(holder.mItem);
                }
            }
        });
    }

    public class ViewHolder extends SelectableViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DayOfWeek mItem;

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
    }
}
