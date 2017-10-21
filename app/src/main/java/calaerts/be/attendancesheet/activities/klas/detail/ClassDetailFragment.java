package calaerts.be.attendancesheet.activities.klas.detail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import calaerts.be.attendancesheet.AttendanceApp;
import calaerts.be.attendancesheet.R;
import calaerts.be.attendancesheet.activities.klas.KlasListViewModel;
import calaerts.be.attendancesheet.activities.klas.detail.day.DaysHoursFragment;
import calaerts.be.attendancesheet.activities.klas.student.StudentListContainer;
import calaerts.be.attendancesheet.repository.KlasRepository;

public class ClassDetailFragment extends Fragment {
    public static final String KLAS_ID = "klas_id";
    @Inject
    KlasRepository klasRepository;
    @Inject KlasListViewModel klasViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendanceApp) getActivity().getApplication())
                .getAppComponent()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.class_detail, container, false);
        ViewPager viewPager = view.findViewById(R.id.klasPager);
        TabLayout tabs = view.findViewById(R.id.tab_layout);
        tabs.addTab(tabs.newTab().setText("Students"));
        tabs.addTab(tabs.newTab().setText("Uren"));
        tabs.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        final PagerAdapter adapter = new FragmentStatePagerAdapter(getFragmentManager()) {
            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public Fragment getItem(int i) {
                switch (i) {
                    case 0:
                        return new StudentListContainer();
                    case 1:
                        return new DaysHoursFragment();
                }
                return null;
            }
        };
        viewPager.setAdapter(adapter);
        return view;
    }
}
