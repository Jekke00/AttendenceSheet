package calaerts.be.attendancesheet.activities.klas.student;


import android.arch.core.util.Function;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.model.StudentDb;

public abstract class AbstractStudentListFragment extends Fragment implements StudentInteractionListener {
    private MyStudentRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_student_list, container, false);
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new MyStudentRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    public void setStudents(List<StudentDb> students) {
        if (students != null)
            adapter.setStudents(students);
    }

    public abstract Function<StudentInteractionListener, AbstractStudentRecyclerViewAdapter> getAdapterFactory();
}
