package calaerts.be.attendancesheet.activities.klas.newKlas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.model.KlasDB;

public class NewKlasActivity extends AppCompatActivity {
    private final KlasDB klas = new KlasDB();
    @Inject
    calaerts.be.attendancesheet.repository.KlasDao klasDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_new_klas);
        initListeners();
    }

    private void initListeners() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.create_class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreatePressed(null);
            }
        });
    }

    public void onCreatePressed(View view) {
        EditText editText = findViewById(R.id.clasName);
        this.klas.setName(editText.getText().toString());
        klasDao.insertKlas(klas);
        finish();
    }
}
