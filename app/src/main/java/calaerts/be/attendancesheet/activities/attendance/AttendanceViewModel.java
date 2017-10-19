package calaerts.be.attendancesheet.activities.attendance;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import java.util.Date;
import java.util.List;

import calaerts.be.attendancesheet.model.DayOfWeek;
import calaerts.be.attendancesheet.model.Hour;
import calaerts.be.attendancesheet.model.Student;
import calaerts.be.attendancesheet.repository.MomentDao;
import calaerts.be.attendancesheet.repository.StudentDao;

public class AttendanceViewModel extends ViewModel {
    private final StudentDao studentDao;
    private final MomentDao momentDao;
    private final MediatorLiveData<List<Hour>> availableHourAtDayMediator = new MediatorLiveData<>();
    private final MediatorLiveData<List<Student>> studentMediator = new MediatorLiveData<>();
    private LiveData<List<Hour>> currentHoursLiveData;
    private LiveData<List<Student>> currentStudentLiveData;
    private DayOfWeek selectedDayOfWeek;


    public AttendanceViewModel(StudentDao studentDao, MomentDao momentDao) {
        this.studentDao = studentDao;
        this.momentDao = momentDao;
    }


    public LiveData<List<Hour>> getUsedHours() {
        return availableHourAtDayMediator;
    }

    public void selectDate(Date date) {
        if (currentHoursLiveData != null)
            availableHourAtDayMediator.removeSource(currentHoursLiveData);
        selectedDayOfWeek = DayOfWeek.fromDate(date);
        currentHoursLiveData = momentDao.getHoursAtDay(selectedDayOfWeek);

        availableHourAtDayMediator.addSource(currentHoursLiveData, new Observer<List<Hour>>() {
            @Override
            public void onChanged(@Nullable List<Hour> hours) {
                availableHourAtDayMediator.setValue(hours);
            }
        });
    }

    public void selectHour(Hour hour) {
        if (currentStudentLiveData != null) {
            studentMediator.removeSource(currentHoursLiveData);
        }

        if (selectedDayOfWeek != null && hour != null) {
            this.currentStudentLiveData = studentDao.getStudentsAtDayOfWeekAndHour(selectedDayOfWeek, hour);
            studentMediator.addSource(currentStudentLiveData, new Observer<List<Student>>() {
                @Override
                public void onChanged(@Nullable List<Student> students) {
                    studentMediator.setValue(students);
                }
            });
        }
    }
}
