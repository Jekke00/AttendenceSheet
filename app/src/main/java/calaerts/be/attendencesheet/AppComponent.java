package calaerts.be.attendencesheet;

import javax.inject.Singleton;

import calaerts.be.attendencesheet.activities.klas.DayListFragment;
import calaerts.be.attendencesheet.activities.klas.detail.ClassDetailFragment;
import calaerts.be.attendencesheet.activities.klas.detail.hour.HourFragment;
import calaerts.be.attendencesheet.activities.klas.list.ClassListActivity;
import calaerts.be.attendencesheet.activities.klas.newKlas.NewKlas;
import calaerts.be.attendencesheet.activities.klas.newKlas.NewKlasActivity;
import calaerts.be.attendencesheet.activities.klas.student.StudentDetail;
import calaerts.be.attendencesheet.activities.klas.student.StudentListFragment;
import dagger.Component;
@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject(NewKlas newKlas);
    void inject(ClassListActivity newKlas);
    void inject(NewKlasActivity newKlasActivity);
    void inject(ClassDetailFragment classDetailFragment);
    void inject(KlasViewModel klasViewModel);
    void inject(StudentDetail studentDetail);
    void inject(DayListFragment dayListFragment);

    void inject(HourFragment hourFragment);

    void inject(StudentListFragment studentListFragment);
}
