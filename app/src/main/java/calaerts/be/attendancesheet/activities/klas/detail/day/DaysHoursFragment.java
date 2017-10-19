package calaerts.be.attendancesheet.activities.klas.detail.day;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calaerts.be.attendancesheet.R;


public class DaysHoursFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_days_hours, container, false);
    }

}
