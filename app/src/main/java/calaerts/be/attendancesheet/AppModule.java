package calaerts.be.attendancesheet;

import android.app.Application;

import javax.inject.Singleton;

import calaerts.be.attendancesheet.activities.attendance.AttendanceViewModel;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.repository.KlasDao;
import calaerts.be.attendancesheet.repository.KlasRepository;
import calaerts.be.attendancesheet.repository.MomentDao;
import calaerts.be.attendancesheet.repository.StudentDao;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return application;
    }

    @Provides
    @Singleton
    public KlasRepository klasRepository() {
        return new KlasRepository(klasDao());
    }

    @Provides
    @Singleton
    public AppDatabase appDatabase() {
        return AppDatabase.getInMemoryDatabase(providesApplication());
    }

    @Provides
    @Singleton
    public StudentDao studentDao() {
        return appDatabase().studentDao();
    }

    @Provides
    @Singleton
    public KlasDao klasDao() {
        return appDatabase().klasDao();
    }

    @Provides
    @Singleton
    public MomentDao momentDao() {
        return appDatabase().momentDao();
    }

    @Provides
    @Singleton
    public KlasListViewModel klasListViewModel() {
        return new KlasListViewModel(klasRepository(), momentDao());
    }

    @Provides
    @Singleton
    public AttendanceViewModel attendanceViewModel() {
        return new AttendanceViewModel(studentDao(), momentDao());
    }
}
