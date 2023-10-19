package com.example.travelapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private ArrayList<Fragment> fragmentArrayList;

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragmentsList) {
        super(fragmentActivity);
        this.fragmentArrayList = fragmentsList;
    }

    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }


    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }


}
