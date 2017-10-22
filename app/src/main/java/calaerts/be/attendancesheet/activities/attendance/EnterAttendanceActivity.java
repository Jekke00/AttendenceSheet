package calaerts.be.attendancesheet.activities.attendance;

import android.os.Bundle;

import org.joda.time.LocalDate;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.AbstractAttendanceAppActivity;

public class EnterAttendanceActivity extends AbstractAttendanceAppActivity {
    @Inject
    AttendanceViewModel attendanceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getApplication()).getAppComponent().inject(this);
        attendanceViewModel.selectDate((LocalDate) getIntent().getSerializableExtra("date"));
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_enter_attendance;
    }

}
