package calaerts.be.attendancesheet.activities.attendance.hour;

import android.arch.core.util.Function;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.activities.attendance.AttendanceViewModel;
import calaerts.be.attendancesheet.activities.klas.detail.hour.AbstractHourFragment;
import calaerts.be.attendancesheet.activities.klas.detail.hour.AbstractHourRecyclerViewAdapter;
import calaerts.be.attendancesheet.activities.klas.detail.hour.OnHourListInteraction;
import calaerts.be.attendancesheet.model.Hour;


public class AttendanceHourList extends AbstractHourFragment {
    @Inject
    AttendanceViewModel attendanceViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = super.onCreateView(inflater, container, savedInstanceState);
        attendanceViewModel.getUsedHours().observe(this, new Observer<List<Hour>>() {
            @Override
            public void onChanged(@Nullable List<Hour> hours) {
                getAdapter().setHours(hours);
            }
        });
        return view;
    }

    @Override
    public void onHourClicked(Hour item) {
        this.attendanceViewModel.selectHour(item);
    }

    @Override
    protected Function<OnHourListInteraction, ? extends AbstractHourRecyclerViewAdapter> adapterFactory() {
        return new Function<OnHourListInteraction, AbstractHourRecyclerViewAdapter>() {
            @Override
            public AbstractHourRecyclerViewAdapter apply(OnHourListInteraction onHourListInteraction) {
                return new AttendanceHourAdapter(onHourListInteraction);
            }
        };
    }
}
