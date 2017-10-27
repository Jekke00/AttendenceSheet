package calaerts.be.attendancesheet.activities.attendance;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import calaerts.be.attendancesheet.model.DayOfWeek;
import calaerts.be.attendancesheet.model.Hour;
import calaerts.be.attendancesheet.model.Student;
import calaerts.be.attendancesheet.model.StudentWithMissedAttendances;
import calaerts.be.attendancesheet.repository.MomentDao;
import calaerts.be.attendancesheet.repository.StudentDao;

public class AttendanceViewModel extends ViewModel {
    private final StudentDao studentDao;
    private final MomentDao momentDao;
    private final MediatorLiveData<List<Hour>> availableHourAtDayMediator = new MediatorLiveData<>();
    private final MediatorLiveData<List<Student>> studentMediator = new MediatorLiveData<>();
    private final MutableLiveData<Hour> selectedHourLiveDate = new MutableLiveData<>();
    private final MutableLiveData<LocalDate> selectedDateLiveDate = new MutableLiveData<>();
    private final Function<List<StudentWithMissedAttendances>, List<Student>> studentMapper = studentsWithMissedAttendances -> {
        List<Student> students = new ArrayList<>();
        for (StudentWithMissedAttendances studentWithMissedAttendance : studentsWithMissedAttendances) {
            students.add(new Student(studentWithMissedAttendance.student, studentWithMissedAttendance.missedAttendances, studentWithMissedAttendance.color));
        }
        return students;
    };
    private LiveData<List<Hour>> currentHoursLiveData;
    private LiveData<List<Student>> currentStudentLiveData;
    private LocalDate selectedDate;

    public AttendanceViewModel(StudentDao studentDao, MomentDao momentDao) {
        this.studentDao = studentDao;
        this.momentDao = momentDao;
    }

    public LiveData<List<Hour>> getUsedHours() {
        return availableHourAtDayMediator;
    }

    public void selectDate(LocalDate date) {
        clearData();
        selectedDate = date;
        selectedDateLiveDate.setValue(date);
        currentHoursLiveData = momentDao.getHoursAtDay(DayOfWeek.of(date));
        availableHourAtDayMediator.addSource(currentHoursLiveData, availableHourAtDayMediator::setValue);
    }

    private void clearData() {
        if (currentHoursLiveData != null) {
            availableHourAtDayMediator.removeSource(currentHoursLiveData);
            availableHourAtDayMediator.setValue(new ArrayList<>());
        }
        if (currentHoursLiveData != null) {
            studentMediator.removeSource(currentHoursLiveData);
            studentMediator.setValue(new ArrayList<>());
        }
    }

    public LiveData<LocalDate> selectedDate() {
        return selectedDateLiveDate;
    }

    public void selectHour(Hour hour) {
        if (currentStudentLiveData != null) {
            studentMediator.removeSource(currentStudentLiveData);
        }
        selectedHourLiveDate.setValue(hour);
        if (DayOfWeek.of(selectedDate) != null && hour != null) {
            addStudentDataToMediator(hour);
        }
    }

    public LiveData<Hour> selectedHour() {
        return selectedHourLiveDate;
    }

    private void addStudentDataToMediator(Hour hour) {
        generateStudentLiveData(hour);

        studentMediator.addSource(currentStudentLiveData, studentMediator::setValue);
    }

    private void generateStudentLiveData(Hour hour) {
        this.currentStudentLiveData = Transformations.map(studentDao.getStudentsAtDayOfWeekAndHour(DayOfWeek.of(selectedDate), hour), studentMapper);
    }

    public LiveData<List<Student>> getStudents() {
        return studentMediator;
    }
}
