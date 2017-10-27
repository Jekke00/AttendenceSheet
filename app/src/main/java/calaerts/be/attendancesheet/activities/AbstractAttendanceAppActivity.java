package calaerts.be.attendancesheet.activities;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import org.joda.time.LocalDate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import calaerts.be.attendancesheet.AppDatabase;
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
            case R.id.enterAttendance:
                toAttendanceActivity(new LocalDate());
                return true;
            case R.id.backupDatabase:
                exportDB();
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

    protected void setToolbarColor(@ColorInt int color) {
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(color));
    }

    private void exportDB() {
        File sd = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "database");
        File data = Environment.getDataDirectory();

        String currentDBPath = AppDatabase.getDatabase(this).getLocation();
        String backupDBPath = "database";
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            FileChannel source = new FileInputStream(currentDB).getChannel();
            FileChannel destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();
            Toast.makeText(this, "DB Exported!", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
