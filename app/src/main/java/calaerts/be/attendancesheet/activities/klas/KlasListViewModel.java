package calaerts.be.attendancesheet.activities.klas;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import java.util.List;

import calaerts.be.attendancesheet.model.Day;
import calaerts.be.attendancesheet.model.DayOfWeek;
import calaerts.be.attendancesheet.model.Hour;
import calaerts.be.attendancesheet.model.Klas;
import calaerts.be.attendancesheet.model.KlasDB;
import calaerts.be.attendancesheet.model.Moment;
import calaerts.be.attendancesheet.model.Student;
import calaerts.be.attendancesheet.repository.KlasRepository;
import calaerts.be.attendancesheet.repository.MomentDao;

public class KlasListViewModel extends ViewModel {
    private final KlasRepository klasRepository;
    private final MomentDao momentDao;
    private final MediatorLiveData<Klas> selectedKlasLiveData = new MediatorLiveData<>();
    private final MutableLiveData<Student> studentLiveData = new MutableLiveData<>();
    private final MediatorLiveData<Day> dayMediator = new MediatorLiveData<>();

    private LiveData<Klas> currentLiveDataOfKlas;
    private Integer selectedKlasId;
    private LiveData<Day> currentSelectedDayLiveData;

    public KlasListViewModel(KlasRepository klasRepository, MomentDao momentDao) {
        this.klasRepository = klasRepository;
        this.momentDao = momentDao;
    }

    public LiveData<List<KlasDB>> allKlassen() {
        return klasRepository.getAllKlassen();
    }

    public LiveData<Klas> getSelectedKlas() {
        return selectedKlasLiveData;
    }

    public void setSelectedKlas(int klasId) {
        if (currentLiveDataOfKlas != null)
            selectedKlasLiveData.removeSource(currentLiveDataOfKlas);
        this.selectedKlasId = klasId;
        this.currentLiveDataOfKlas = klasRepository.getKlas(klasId);
        selectedKlasLiveData.addSource(currentLiveDataOfKlas, new Observer<Klas>() {
            @Override
            public void onChanged(@Nullable Klas klas) {
                selectedKlasLiveData.setValue(klas);
            }
        });
    }

    public LiveData<Student> selectedStudent() {
        return studentLiveData;
    }

    public void selectStudent(Student student) {
        studentLiveData.setValue(student);
    }

    public LiveData<Day> selectedDay() {
        return dayMediator;
    }

    public void selectedDay(final DayOfWeek dayOfWeek) {
        if (currentLiveDataOfKlas != null)
            dayMediator.removeSource(currentSelectedDayLiveData);
        final LiveData<List<Moment>> allMomentsByKlasIdAndDayOfWeek = momentDao.getAllMomentsByKlasIdAndDayOfWeek(selectedKlasId, dayOfWeek);
        LiveData<Day> transformed = Transformations.map(allMomentsByKlasIdAndDayOfWeek, new Function<List<Moment>, Day>() {
            @Override
            public Day apply(List<Moment> moments) {
                List<Hour> hours = dayOfWeek.getAvailableHours();
                for (Moment moment : moments) {
                    hours.get(moment.getHour().getHour() - 1).setSelected(true);
                }
                return new Day(dayOfWeek, hours);
            }
        });
        currentSelectedDayLiveData = transformed;
        dayMediator.addSource(transformed, new Observer<Day>() {
            @Override
            public void onChanged(@Nullable Day day) {
                dayMediator.setValue(day);
            }
        });
    }
}
