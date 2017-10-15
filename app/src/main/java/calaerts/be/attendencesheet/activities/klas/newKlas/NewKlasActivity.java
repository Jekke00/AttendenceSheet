package calaerts.be.attendencesheet.activities.klas.newKlas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import javax.inject.Inject;

import calaerts.be.attendencesheet.AttendenceApp;
import calaerts.be.attendencesheet.R;
import calaerts.be.attendencesheet.model.KlasDB;

public class NewKlasActivity extends AppCompatActivity {
    private final KlasDB klas = new KlasDB();
    @Inject
    calaerts.be.attendencesheet.repository.KlasDao klasDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendenceApp) getApplication()).getAppComponent().inject(this);
        setContentView(R.layout.activity_new_klas);
        initListeners();
    }

    private void initListeners() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.create_class).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreatePressed();
            }
        });
    }

    public void onCreatePressed(){
        EditText editText = findViewById(R.id.clasName);
        this.klas.setName(editText.getText().toString());
        klasDao.insertKlas(klas);
        finish();
    }
}
