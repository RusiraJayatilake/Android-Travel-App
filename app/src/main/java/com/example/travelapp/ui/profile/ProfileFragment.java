package com.example.travelapp.ui.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.travelapp.R;

public class ProfileFragment extends Fragment {
    private FrameLayout profileScreen;
    private TextView profileText;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        profileScreen = rootView.findViewById(R.id.profile_screen);
        profileText = rootView.findViewById(R.id.profileText);

        return rootView;
    }
}