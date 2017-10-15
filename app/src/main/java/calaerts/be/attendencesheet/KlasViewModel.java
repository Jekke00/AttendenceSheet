package calaerts.be.attendencesheet;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import calaerts.be.attendencesheet.model.Day;
import calaerts.be.attendencesheet.model.Klas;
import calaerts.be.attendencesheet.model.Moment;
import calaerts.be.attendencesheet.model.Student;
import calaerts.be.attendencesheet.repository.KlasRepository;

public class KlasViewModel extends ViewModel {

    private final KlasRepository klasRepository;
    private MutableLiveData<Klas> klasLiveData = new MutableLiveData<>();
    private MutableLiveData<Student> studentLiveData = new MutableLiveData<>();
    private LiveData<Klas> selectedKlasData;
    private Observer<Klas> klasObserver;

    public KlasViewModel(KlasRepository klasRepository) {
        this.klasRepository = klasRepository;
    }

    public LiveData<Klas> getSelectedKlas() {
        return klasLiveData;
    }

    public void selectKlas(final int klasID) {
        selectedKlasData = klasRepository.getKlas(klasID);
        klasObserver = new Observer<Klas>() {
            @Override
            public void onChanged(@Nullable Klas klas) {
                klasLiveData.setValue(klas);
            }
        };
        selectedKlasData.observeForever(klasObserver);
    }

    public LiveData<Student> selectedStudent() {
        return studentLiveData;
    }

    public void selectStudent(Student student) {
        studentLiveData.setValue(student);
    }

    public LiveData<List<Day>> getDays() {
        return Transformations.map(getSelectedKlas(), new Function<Klas, List<Day>>() {
            @Override
            public List<Day> apply(Klas klas) {
                List<Day> days = new ArrayList<>();
                for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
                    final List<Hour> hours = getHoursForKlas(klas, dayOfWeek);
                    Day day = new Day(dayOfWeek, hours);
                    days.add(day);
                }
                return days;
            }
        });
    }

    @NonNull
    private List<Hour> getHoursForKlas(Klas klas, DayOfWeek dayOfWeek) {
        final List<Hour> hours = dayOfWeek.getAvailableHours();
        for (Hour hour : hours) {
            Moment moment = new Moment();
            moment.setDayOfWeek(dayOfWeek);
            moment.setHour(hour);
            hour.setSelected(klas.getMoments().contains(moment));

        }
        return hours;
    }

    public void selectDay(Day day) {
//        dayFlowable.combine;
    }
//
//    public Flowable<Day> getSelectedDay(){
//        return dayFlowable;
//    }


}
