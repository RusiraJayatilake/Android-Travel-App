package com.example.travelapp.ui.map;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.travelapp.R;


public class MapFragment extends Fragment {
    private FrameLayout mapScreen;
    private TextView mapText;

    public MapFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        mapScreen = rootView.findViewById(R.id.map_screen);
        //mapText = rootView.findViewById(R.id.mapText);

        return rootView;

    }
}