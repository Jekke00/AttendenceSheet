package calaerts.be.attendancesheet;

import javax.inject.Singleton;

import calaerts.be.attendancesheet.activities.attendance.AttendanceHourList;
import calaerts.be.attendancesheet.activities.attendance.EnterAttendanceActivity;
import calaerts.be.attendancesheet.activities.klas.detail.ClassDetailFragment;
import calaerts.be.attendancesheet.activities.klas.detail.day.DayListFragment;
import calaerts.be.attendancesheet.activities.klas.detail.hour.HourFragment;
import calaerts.be.attendancesheet.activities.klas.list.KlasListActivity;
import calaerts.be.attendancesheet.activities.klas.newKlas.NewKlas;
import calaerts.be.attendancesheet.activities.klas.newKlas.NewKlasActivity;
import calaerts.be.attendancesheet.activities.klas.student.StudentDetail;
import calaerts.be.attendancesheet.activities.klas.student.StudentListFragment;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(NewKlas newKlas);

    void inject(KlasListActivity newKlas);

    void inject(NewKlasActivity newKlasActivity);

    void inject(ClassDetailFragment classDetailFragment);

    void inject(StudentDetail studentDetail);

    void inject(DayListFragment dayListFragment);

    void inject(HourFragment hourFragment);

    void inject(StudentListFragment studentListFragment);

    void inject(AttendanceHourList attendanceHourList);

    void inject(EnterAttendanceActivity enterAttendanceActivity);
}
