package calaerts.be.attendencesheet.activities.klas.detail.hour;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import calaerts.be.attendencesheet.AttendenceApp;
import calaerts.be.attendencesheet.KlasListViewModel;
import calaerts.be.attendencesheet.R;
import calaerts.be.attendencesheet.model.Day;
import calaerts.be.attendencesheet.model.Hour;
import calaerts.be.attendencesheet.model.Klas;
import calaerts.be.attendencesheet.model.Moment;
import calaerts.be.attendencesheet.repository.MomentDao;


public class HourFragment extends Fragment {
    @Inject
    MomentDao momentDao;
    @Inject
    KlasListViewModel klasViewModel;
    private MyHourRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendenceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_hour_list, container, false);

        setupRecyclerView(recyclerView);
        klasViewModel.selectedDay().observe(this, new Observer<Day>() {
            @Override
            public void onChanged(@Nullable Day day) {
                onDayUpdated(day);
            }
        });
        klasViewModel.getSelectedKlas().observe(this, new Observer<Klas>() {
            @Override
            public void onChanged(@Nullable Klas klas) {
                clearHours();
            }
        });

        return recyclerView;
    }

    private void clearHours() {
        adapter.setHours(new ArrayList<Hour>());
    }

    private void onDayUpdated(@Nullable Day day) {
        if (day != null)
            adapter.setHours(day.getHours());
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter = new MyHourRecyclerViewAdapter(new OnListFragmentInteractionListener() {
            @Override
            public void onListFragmentInteraction(Hour item) {
                onHourClicked(item);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void onHourClicked(Hour item) {
        if (item.isSelected()) {
            deleteMoment(item);
        } else {
            addMoment(item);
        }
    }

    private void deleteMoment(Hour item) {
        momentDao.deleteMoment(klasViewModel.getSelectedKlas().getValue().getId(), klasViewModel.selectedDay().getValue().getDayOfWeek().id, item.getHour());
    }

    private void addMoment(Hour item) {
        Moment moment = new Moment();
        moment.setKlasId(klasViewModel.getSelectedKlas().getValue().getId());
        moment.setHour(item);
        moment.setDayOfWeek(klasViewModel.selectedDay().getValue().getDayOfWeek());
        momentDao.insert(moment);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Hour item);
    }
}
