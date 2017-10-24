package calaerts.be.attendancesheet.activities.klas.newKlas;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.AbstractAttendanceAppActivity;
import calaerts.be.attendancesheet.model.KlasDB;
import calaerts.be.attendancesheet.repository.KlasDao;

public class KlasDetailActivity extends AbstractAttendanceAppActivity implements ColorPickerDialogListener {
    @Inject
    KlasDao klasDao;
    private KlasDB klas = new KlasDB();
    private EditText klasNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        klasNameEditText = findViewById(R.id.klasName);

        ((AttendanceApp) getApplication()).getAppComponent().inject(this);
        klas = getKlasFromIntent();
        setTitle(klas.getName() == null ? "New klas" : klas.getName());
        klasNameEditText.setText(klas.getName());
        initListeners();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_new_klas;
    }

    private KlasDB getKlasFromIntent() {
        KlasDB klas = (KlasDB) getIntent().getSerializableExtra("klas");
        if (klas == null) {
            final KlasDB klasDB = new KlasDB();
            klasDB.setColor(Color.BLUE);
            return klasDB;
        }
        return klas;
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
        this.klas.setName(klasNameEditText.getText().toString());
        if (klas.getId() == 0)
            klasDao.insertKlas(klas);
        else klasDao.updateKlas(klas);
        finish();
    }

    public void onColorChangedPressed(View view) {
        ColorPickerDialog.newBuilder()
                .setColor(klas.getColor())
                .setAllowPresets(true).show(this);

    }

    @Override
    public void onColorSelected(int i, @ColorInt int color) {
        klas.setColor(color);
        setToolbarColor(color);
    }

    @Override
    public void onDialogDismissed(int i) {

    }
}
