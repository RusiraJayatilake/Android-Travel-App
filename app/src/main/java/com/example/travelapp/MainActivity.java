package com.example.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.travelapp.ui.home.HomeFragment;
import com.example.travelapp.ui.map.MapFragment;
import com.example.travelapp.ui.profile.ProfileFragment;
import com.example.travelapp.ui.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private ViewPager2Adapter viewPager2Adapter;
    private ArrayList<Fragment> fragmentArrayList;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager2 = findViewById(R.id.viewPager2);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Add fragments to the arraylist
        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new SearchFragment());
        fragmentArrayList.add(new MapFragment());
        fragmentArrayList.add(new ProfileFragment());

        viewPager2Adapter = new ViewPager2Adapter(this, fragmentArrayList);
        viewPager2.setAdapter(viewPager2Adapter);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.nav_home);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.nav_search);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.nav_map);
                        break;
                    case 3:
                        bottomNavigationView.setSelectedItemId(R.id.nav_profile);
                        break;
                }

                super.onPageSelected(position);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_home) {
                    viewPager2.setCurrentItem(0);
                } else if (item.getItemId() == R.id.nav_search) {
                    viewPager2.setCurrentItem(1);
                } else if (item.getItemId() == R.id.nav_map){
                    viewPager2.setCurrentItem(2);
                } else if(item.getItemId() == R.id.nav_profile){
                    viewPager2.setCurrentItem(3);
                }
                return true;
            }
        });
    }
}