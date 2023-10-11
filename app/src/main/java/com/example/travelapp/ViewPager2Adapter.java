package com.example.travelapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.travelapp.ui.home.HomeFragment;
import com.example.travelapp.ui.search.SearchFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public ViewPager2Adapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
//        switch (position){
//            case 0:
//                return new SearchFragment();
////            case 1:
////                return new SearchFragment();
//            default:
//                return new HomeFragment();
//        }

        if(position == 0){
            return new HomeFragment();
        } else if(position == 1){
            return new SearchFragment();
        } else if(position == 2){
            return null;
        } else {
            return null;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
