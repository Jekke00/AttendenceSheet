package calaerts.be.attendancesheet.activities.klas.student;

import android.arch.core.util.Function;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.model.Klas;
import calaerts.be.attendancesheet.model.StudentDb;

public class StudentListFragment extends AbstractStudentListFragment {

    @Inject
    KlasListViewModel klasViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication()).getAppComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        klasViewModel.getSelectedKlas().observe(this, new Observer<Klas>() {
            @Override
            public void onChanged(@Nullable Klas klas) {
                onKlasUpdated(klas);
            }
        });
    }

    private void onKlasUpdated(Klas klas) {
        setStudents(klas.getStudents());
    }

    @Override
    public Function<StudentInteractionListener, AbstractSelectableRecyclerViewAdapter> getAdapterFactory() {
        return new Function<StudentInteractionListener, AbstractSelectableRecyclerViewAdapter>() {
            @Override
            public AbstractSelectableRecyclerViewAdapter apply(StudentInteractionListener studentInteractionListener) {
                return new MyStudentRecyclerViewAdapter(studentInteractionListener);
            }
        };
    }

    @Override
    public void onStudentSelected(StudentDb student) {
        klasViewModel.selectStudent(student);
    }


}
