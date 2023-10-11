package com.example.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.travelapp.ui.home.HomeFragment;
import com.example.travelapp.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private ViewPager2 viewPager2;
    private ViewPager2Adapter viewPager2Adapter;
    private Fragment[] fragments;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        viewPager2Adapter = new ViewPager2Adapter(this);

        fragments = new Fragment[]{
                new HomeFragment(),
                new SearchFragment()
        };

        viewPager2Adapter.setFragment(fragments);

        viewPager2 = findViewById(R.id.viewPager2);
        viewPager2.setAdapter(viewPager2Adapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //bottomNavigationView.setSelectedItemId(R.id.home_screen);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.home_screen) {
            viewPager2.setCurrentItem(0);
            return true;
        } else if (item.getItemId() == R.id.search_screen) {
            viewPager2.setCurrentItem(1);
            return true;
        }

        return false;
    }

}