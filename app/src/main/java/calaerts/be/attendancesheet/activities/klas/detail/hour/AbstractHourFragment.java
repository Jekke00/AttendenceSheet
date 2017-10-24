package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.arch.core.util.Function;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.model.Hour;

public abstract class AbstractHourFragment extends Fragment {
    private AbstractHourRecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_hour_list, container, false);
        setupRecyclerView(recyclerView);
        return recyclerView;
    }

    public void clearHours() {
        getAdapter().setData(new ArrayList<Hour>());
    }

    private void setupRecyclerView(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        adapter = adapterFactory().apply(new OnHourListInteraction() {
            @Override
            public void onListFragmentInteraction(Hour item) {
                onHourClicked(item);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    public abstract void onHourClicked(Hour item);

    protected AbstractHourRecyclerViewAdapter getAdapter() {
        return adapter;
    }

    abstract protected Function<OnHourListInteraction, ? extends AbstractHourRecyclerViewAdapter> adapterFactory();
}
