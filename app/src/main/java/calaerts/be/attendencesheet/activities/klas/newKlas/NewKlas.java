package calaerts.be.attendencesheet.activities.klas.newKlas;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import javax.inject.Inject;

import calaerts.be.attendencesheet.AttendenceApp;
import calaerts.be.attendencesheet.R;
import calaerts.be.attendencesheet.model.KlasDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewKlas extends Fragment {
    private final KlasDB klas = new KlasDB();
    @Inject
    calaerts.be.attendencesheet.repository.KlasDao klasDao;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendenceApp) getActivity().getApplication())
                .getAppComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_klas, container, false);
    }

    public void onCreatePressed(){
        EditText editText = getView().findViewById(R.id.clasName);
        this.klas.setName(editText.getText().toString());
    }

}
