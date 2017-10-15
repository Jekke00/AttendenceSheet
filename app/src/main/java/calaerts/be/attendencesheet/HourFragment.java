package calaerts.be.attendencesheet;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import calaerts.be.attendencesheet.model.Day;
import calaerts.be.attendencesheet.model.Moment;
import calaerts.be.attendencesheet.repository.MomentDao;


public class HourFragment extends Fragment {
    @Inject MomentDao momentDao;
    @Inject KlasListViewModel klasViewModel;
    private MyHourRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendenceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hour_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new MyHourRecyclerViewAdapter(new OnListFragmentInteractionListener() {
                @Override
                public void onListFragmentInteraction(Hour item) {
                    if (item.isSelected()) {
                        momentDao.deleteMoment(klasViewModel.getSelectedKlas().getValue().getId(), klasViewModel.selectedDay().getValue().getDayOfWeek().id, item.getHour());
                    } else {
                        Moment moment = new Moment();
                        moment.setKlasId(klasViewModel.getSelectedKlas().getValue().getId());
                        moment.setHour(item);
                        moment.setDayOfWeek(klasViewModel.selectedDay().getValue().getDayOfWeek());
                        momentDao.insert(moment);
                    }
                }
            });
            recyclerView.setAdapter(adapter);
            klasViewModel.selectedDay().observe(this, new Observer<Day>() {
                @Override
                public void onChanged(@Nullable Day day) {
                    adapter.setHours(day.getHours());
                }
            });

        }
        return view;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Hour item);
    }
}
