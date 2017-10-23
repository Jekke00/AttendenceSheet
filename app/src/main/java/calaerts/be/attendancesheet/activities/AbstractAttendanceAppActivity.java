package calaerts.be.attendancesheet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.joda.time.LocalDate;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.attendance.EnterAttendanceActivity;
import calaerts.be.attendancesheet.activities.klas.list.KlasListActivity;

abstract public class AbstractAttendanceAppActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        setupToolbar();
    }

    protected abstract int getContentView();

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null)
            toolbar.setTitle(getTitle());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.manageKlas:
                toManageKlasActivity();
                return true;
            case R.id.enterAttendence:
                toAttendanceActivity(new LocalDate());
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void toAttendanceActivity(LocalDate localDate) {
        Intent intent = new Intent(this, EnterAttendanceActivity.class);
        intent.putExtra("date", localDate);
        startActivity(intent);
        this.finish();
    }

    private void toManageKlasActivity() {
        Intent intent = new Intent(this, KlasListActivity.class);
        startActivity(intent);
        this.finish();
    }
}
