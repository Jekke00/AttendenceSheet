package calaerts.be.attendancesheet.activities.attendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import org.joda.time.LocalDate;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;


public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public static final String DATE = "date";
    @Inject
    AttendanceViewModel attendanceViewModel;

    public static DatePickerFragment newInstance(LocalDate date) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putSerializable(DATE, date);
        datePickerFragment.setArguments(args);
        return datePickerFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LocalDate date = (LocalDate) getArguments().getSerializable(DATE);
        return new DatePickerDialog(getActivity(), this, date.getYear(), date.getMonthOfYear() - 1, date.getDayOfMonth());
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        attendanceViewModel.selectDate(new LocalDate(year, month + 1, day));
    }
}