package calaerts.be.attendancesheet.activities.klas.detail.day;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.model.DayOfWeek;

public class DayListFragment extends Fragment {
    @Inject
    KlasListViewModel klasViewModel;
    private DaysRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_list, container, false);

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new DaysRecyclerViewAdapter(item -> klasViewModel.selectedDay(item));
        recyclerView.setAdapter(adapter);
        adapter.setData(Arrays.asList(DayOfWeek.values()));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        klasViewModel.selectedDay().observe(this, day -> {
            if (day == null) {
                adapter.clearSelected();
                return;
            }
            adapter.setSelected(day.getDayOfWeek());
        });
    }

    public interface OnListFragmentInteractionListener {
        void onDayInteracted(DayOfWeek item);
    }
}
