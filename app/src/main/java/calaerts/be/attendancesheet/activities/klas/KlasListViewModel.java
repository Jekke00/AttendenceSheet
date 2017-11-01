package calaerts.be.attendancesheet.activities.klas;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import calaerts.be.attendancesheet.model.Day;
import calaerts.be.attendancesheet.model.DayOfWeek;
import calaerts.be.attendancesheet.model.Hour;
import calaerts.be.attendancesheet.model.Klas;
import calaerts.be.attendancesheet.model.KlasDB;
import calaerts.be.attendancesheet.model.Moment;
import calaerts.be.attendancesheet.model.StudentDb;
import calaerts.be.attendancesheet.repository.KlasRepository;
import calaerts.be.attendancesheet.repository.MomentDao;

public class KlasListViewModel extends ViewModel {
    private final KlasRepository klasRepository;
    private final MomentDao momentDao;

    private final MediatorLiveData<Klas> selectedKlasLiveData = new MediatorLiveData<>();
    private final MutableLiveData<StudentDb> studentLiveData = new MutableLiveData<>();
    private final MediatorLiveData<Day> dayMediator = new MediatorLiveData<>();

    private Integer selectedKlasId;

    private LiveData<Klas> currentLiveDataOfKlas;
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
        selectedKlasLiveData.addSource(currentLiveDataOfKlas, selectedKlasLiveData::setValue);
    }

    public LiveData<StudentDb> selectedStudent() {
        return studentLiveData;
    }

    public void selectStudent(StudentDb student) {
        studentLiveData.setValue(student);
    }

    public LiveData<Day> selectedDay() {
        return dayMediator;
    }

    public void selectedDay(@NonNull final DayOfWeek dayOfWeek) {
        if (currentLiveDataOfKlas != null)
            dayMediator.removeSource(currentSelectedDayLiveData);
        final LiveData<List<Moment>> allMomentsByKlasIdAndDayOfWeek = momentDao.getAllMomentsByKlasIdAndDayOfWeek(selectedKlasId, dayOfWeek);
        LiveData<Day> transformed = Transformations.map(allMomentsByKlasIdAndDayOfWeek, moments -> {
            List<Hour> hours = dayOfWeek.getAvailableHours();
            for (Moment moment : moments) {
                hours.get(moment.getHour().getHour() - 1).setSelected(true);
            }
            return new Day(dayOfWeek, hours);
        });
        currentSelectedDayLiveData = transformed;
        dayMediator.addSource(transformed, dayMediator::setValue);
    }
}
