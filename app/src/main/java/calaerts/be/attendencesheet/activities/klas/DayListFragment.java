package calaerts.be.attendencesheet.activities.klas;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import javax.inject.Inject;

import calaerts.be.attendencesheet.AttendenceApp;
import calaerts.be.attendencesheet.DayOfWeek;
import calaerts.be.attendencesheet.KlasListViewModel;
import calaerts.be.attendencesheet.R;
public class DayListFragment extends Fragment {
    @Inject
    KlasListViewModel klasViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendenceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            final DaysRecyclerViewAdapter adapter = new DaysRecyclerViewAdapter(new OnListFragmentInteractionListener() {
                @Override
                public void onListFragmentInteraction(DayOfWeek item) {
                    klasViewModel.selectedDay(item);
                }
            });
            recyclerView.setAdapter(adapter);
            adapter.setDays(Arrays.asList(DayOfWeek.values()));

        }
        return view;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DayOfWeek item);
    }
}
