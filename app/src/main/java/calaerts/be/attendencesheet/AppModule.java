package calaerts.be.attendencesheet;

import android.app.Application;

import javax.inject.Singleton;

import calaerts.be.attendencesheet.repository.KlasDao;
import calaerts.be.attendencesheet.repository.KlasRepository;
import calaerts.be.attendencesheet.repository.MomentDao;
import calaerts.be.attendencesheet.repository.StudentDao;
import calaerts.be.attendencesheet.services.ViewModelFinder;
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
        return new KlasRepository(klasDao(), studentDao());
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
    public ViewModelFinder viewModelFinder() {
        return new ViewModelFinder();
    }

    @Provides
    @Singleton
    public KlasListViewModel klasListViewModel() {
        return new KlasListViewModel(klasRepository(), momentDao());
    }

    @Provides
    @Singleton
    public KlasViewModel klasViewModel() {
        return new KlasViewModel(klasRepository());
    }
}
