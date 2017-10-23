package calaerts.be.attendancesheet.activities.attendance;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;

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
        attendanceViewModel.selectedDate().observe(this, new Observer<LocalDate>() {
            @Override
            public void onChanged(@Nullable LocalDate date) {
                setTitle("Attendance at: " + date.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final boolean ok = super.onCreateOptionsMenu(menu);
        menu.add(0, 0, 0, "Select day");
        return ok;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 0) {
            DatePickerFragment datePickerFragment = DatePickerFragment.newInstance(attendanceViewModel.selectedDate().getValue());
            datePickerFragment.show(getSupportFragmentManager(), "datePicker");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_enter_attendance;
    }

}
