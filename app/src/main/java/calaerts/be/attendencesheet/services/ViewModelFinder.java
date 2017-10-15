package calaerts.be.attendencesheet.services;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import calaerts.be.attendencesheet.ViewModelHolder;

public class ViewModelFinder {
    public <T extends ViewModel> T findViewModelHolder(@NonNull final Fragment fragment, @NonNull final Class<T> viewmModelClass) {
        T workingFragment1 = findViewModelInFragmentStack(fragment, viewmModelClass);
        if (workingFragment1 != null) return workingFragment1;
        return ViewModelProviders.of(fragment.getActivity()).get(viewmModelClass);
    }

    @Nullable
    private <T extends ViewModel> T findViewModelInFragmentStack(@NonNull Fragment fragment, @NonNull Class<T> viewmModelClass) {
        Fragment workingFragment = fragment;
        while (workingFragment != null) {
            if (workingFragment instanceof ViewModelHolder) {
                return ViewModelProviders.of(workingFragment).get(viewmModelClass);
            }
            workingFragment = fragment.getParentFragment();
        }
        return null;
    }
}
