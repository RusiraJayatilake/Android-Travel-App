package com.example.travelapp.ui.home;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.travelapp.R;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {
    private FrameLayout homeScreen;
    private ImageView mainImage;
    private ImageView placeImage;
    private ImageView restaurantImage;
    private ImageView hotelImage;
    private TextView mainTitle;
    private TextView placeTitle;
    private TextView restaurantTitle;
    private TextView hotelTitle;
    private TextView mainDescription;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        homeScreen = rootView.findViewById(R.id.home_screen);

        mainImage = rootView.findViewById(R.id.mainImage);
        placeImage = rootView.findViewById(R.id.img_place);
        restaurantImage = rootView.findViewById(R.id.img_resturant);
        hotelImage = rootView.findViewById(R.id.img_hotel);

        mainTitle = rootView.findViewById(R.id.mainTitle);
        mainDescription = rootView.findViewById(R.id.mainDes);
        placeTitle = rootView.findViewById(R.id.place_title);
        restaurantTitle = rootView.findViewById(R.id.restaurant_title);
        hotelTitle = rootView.findViewById(R.id.hotel_title);

        
        // Fetch data from Firestore
        Executor executor = Executors.newFixedThreadPool(4);
        executor.execute(() -> {
            try {
                db.collection("TravelApp").document("home_screen")  // document ID to retrieve
                        .get().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    // Populate data into views
                                    mainTitle.setText(document.getString("main_title"));
                                    placeTitle.setText(document.getString("place_title"));
                                    restaurantTitle.setText(document.getString("restaurants_title"));
                                    hotelTitle.setText(document.getString("hotel_title"));
                                    mainDescription.setText(document.getString("main_des"));

                                    // Load images using Glide
                                    Glide.with(this).load(document.getString("main_img_url")).into(mainImage);
                                    Glide.with(this).load(document.getString("place_img")).into(placeImage);
                                    Glide.with(this).load(document.getString("restaurants_img")).into(restaurantImage);
                                    Glide.with(this).load(document.getString("hotel_img")).into(hotelImage);
                                } else {
                                    Toast.makeText(getContext(), "Document not found.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                // Handle any errors that occur.
                                requireActivity().runOnUiThread(() -> {
                                    Toast.makeText(getContext(), "Error getting data: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                });
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
                // Handle any other exceptions that may occur.
            }
        });

        return rootView;
    }
}