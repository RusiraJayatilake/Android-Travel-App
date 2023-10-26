package com.example.travelapp.ui.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travelapp.R;

public class ProfileFragment extends Fragment {
    private FrameLayout profileScreen;
    private TextView profileName, profileEmail;
    private ImageView profileImg;
    private Button profileBtn;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        profileScreen = rootView.findViewById(R.id.profile_screen);

        profileImg = rootView.findViewById(R.id.profileImage);
        profileName = rootView.findViewById(R.id.profileName);
        profileEmail = rootView.findViewById(R.id.profileEmail);
        profileBtn = rootView.findViewById(R.id.editProfileButton);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Yet to be Implemented", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }
}