package com.example.travelapp;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.travelapp.ui.home.HomeFragment;
import com.example.travelapp.ui.search.SearchFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private Fragment[] fragments;

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPager2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return fragments.length;
    }

    public void setFragment(Fragment[] fragments) {
        this.fragments = fragments;
    }

}
