package calaerts.be.attendancesheet.activities.klas.newKlas;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.model.KlasDB;
import calaerts.be.attendancesheet.repository.KlasDao;

public class NewKlas extends Fragment {
    private final KlasDB klas = new KlasDB();
    @Inject
    KlasDao klasDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication())
                .getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_new_klas, container, false);
    }

    public void onCreatePressed(View view) {
        EditText editText = getView().findViewById(R.id.klasName);
        this.klas.setName(editText.getText().toString());
    }

}
