package calaerts.be.attendencesheet.activities.klas.detail;

import android.arch.lifecycle.Observer;
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

import calaerts.be.attendencesheet.AttendenceApp;
import calaerts.be.attendencesheet.KlasListViewModel;
import calaerts.be.attendencesheet.R;
import calaerts.be.attendencesheet.activities.klas.DaysHoursFragment;
import calaerts.be.attendencesheet.activities.klas.student.StudentListFragment;
import calaerts.be.attendencesheet.model.Klas;
import calaerts.be.attendencesheet.repository.KlasRepository;

public class ClassDetailFragment extends Fragment {
    public static final String KLAS_ID = "klas_id";
    private Klas klas;
    @Inject
    KlasRepository klasRepository;
    @Inject KlasListViewModel klasViewModel;
    private TabLayout tabs;
    private ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AttendenceApp) getActivity().getApplication())
                .getAppComponent()
                .inject(this);
        klasViewModel.getSelectedKlas().observe(this, new Observer<Klas>() {
            @Override
            public void onChanged(@Nullable Klas klas) {
                onKlasUpdated(klas);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.class_detail, container, false);
        viewPager = view.findViewById(R.id.klasPager);
        tabs = view.findViewById(R.id.tab_layout);
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
                        return new StudentListFragment();
                    case 1:
                        return new DaysHoursFragment();
                }
                return null;
            }
        };
        viewPager.setAdapter(adapter);
        return view;
    }

    public void onKlasUpdated(Klas klas) {
        this.klas = klas;
    }
}
