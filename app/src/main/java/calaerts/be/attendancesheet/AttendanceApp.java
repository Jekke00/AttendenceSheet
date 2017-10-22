package calaerts.be.attendancesheet;

import android.app.Application;

import com.facebook.stetho.Stetho;

import net.danlew.android.joda.JodaTimeAndroid;

public class AttendanceApp extends Application {
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        Stetho.initializeWithDefaults(this);
        JodaTimeAndroid.init(this);
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
