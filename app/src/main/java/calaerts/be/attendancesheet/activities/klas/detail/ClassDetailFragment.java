package calaerts.be.attendancesheet.activities.klas.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class ClassDetailFragment extends Fragment {
    public static final String KLAS_ID = "klas_id";
    @Inject
    KlasListViewModel klasViewModel;
    private TabLayout tabLayout;
    private ViewPager viewPager;

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
        bindViews(view);
        setupTabLayout();
        return view;
    }

    private void setupTabLayout() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.students));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.hours));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
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
    }

    private void bindViews(View view) {
        viewPager = view.findViewById(R.id.klasPager);
        tabLayout = view.findViewById(R.id.tab_layout);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        klasViewModel.getSelectedKlas().observe(this, klas -> {
            if (klas != null)
                tabLayout.setBackgroundColor(klas.getColor());
        });
    }
}
