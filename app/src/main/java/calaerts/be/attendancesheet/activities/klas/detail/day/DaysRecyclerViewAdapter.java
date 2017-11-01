package calaerts.be.attendancesheet.activities.klas.detail.day;

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
                .inflate(R.layout.simple_list_content, parent, false);
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

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id);
        }

        @Override
        public void setSelected(boolean selected) {
            mView.setActivated(selected);
        }

        @Override
        public void setItem(DayOfWeek Item) {
            super.setItem(Item);
            mIdView.setText(getItem().toString());
        }
    }
}
