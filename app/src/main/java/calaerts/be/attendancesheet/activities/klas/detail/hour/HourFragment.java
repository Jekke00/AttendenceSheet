package calaerts.be.attendancesheet.activities.klas.detail.hour;

import android.arch.core.util.Function;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.model.Day;
import calaerts.be.attendancesheet.model.Hour;
import calaerts.be.attendancesheet.model.Klas;
import calaerts.be.attendancesheet.model.Moment;
import calaerts.be.attendancesheet.repository.MomentDao;


public class HourFragment extends AbstractHourFragment {
    @Inject
    MomentDao momentDao;
    @Inject
    KlasListViewModel klasViewModel;
    private Integer currentKlasId = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        klasViewModel.selectedDay().observe(this, new Observer<Day>() {
            @Override
            public void onChanged(@Nullable Day day) {
                onDayUpdated(day);
            }
        });
        klasViewModel.getSelectedKlas().observe(this, new Observer<Klas>() {
            @Override
            public void onChanged(@Nullable Klas klas) {
                if (klas == null) {
                    clearHours();
                    currentKlasId = null;
                    return;
                }
                if (currentKlasId == null || klas.getId() != currentKlasId) {
                    clearHours();
                    currentKlasId = klas.getId();
                }
            }
        });
        return view;
    }

    private void onDayUpdated(@Nullable Day day) {
        if (day != null)
            getAdapter().setHours(day.getHours());
    }

    @Override
    public void onHourClicked(Hour item) {
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

    @Override
    protected Function<OnHourListInteraction, ? extends AbstractHourRecyclerViewAdapter> adapterFactory() {
        return new Function<OnHourListInteraction, AbstractHourRecyclerViewAdapter>() {
            @Override
            public AbstractHourRecyclerViewAdapter apply(OnHourListInteraction onHourListInteraction) {
                return new ManageHourRecyclerViewAdapter(onHourListInteraction);
            }
        };
    }
}
