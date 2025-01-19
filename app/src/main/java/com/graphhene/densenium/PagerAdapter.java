package com.graphhene.densenium;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {

    private int numberofTabs;
    public PagerAdapter(@NonNull FragmentManager fm, int behavior , int numberofTabs) {
        super(fm, behavior);
        this.numberofTabs= numberofTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new LoginTabFragment();
            case 1:
                return new SignupTabFragment();
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return numberofTabs;
    }
}
