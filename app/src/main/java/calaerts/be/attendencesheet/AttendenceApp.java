package calaerts.be.attendencesheet;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class AttendenceApp extends Application {
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        Stetho.initializeWithDefaults(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
