package calaerts.be.attendancesheet.activities.klas.student;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import calaerts.be.attendancesheet.R;

public class MyStudentRecyclerViewAdapter extends AbstractStudentRecyclerViewAdapter {

    public MyStudentRecyclerViewAdapter(StudentInteractionListener listener) {
        super(listener);
    }

    @Override
    public ModifyStudentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_student, parent, false);
        return new ModifyStudentListViewHolder(view);
    }


}
