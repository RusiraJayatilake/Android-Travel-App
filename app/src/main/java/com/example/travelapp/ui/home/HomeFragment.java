package com.example.travelapp.ui.home;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
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
import java.util.Map;
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
    private TextView categoryTitle;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        homeScreen = rootView.findViewById(R.id.home_screen);

        // Main Screen Images
        mainImage = rootView.findViewById(R.id.mainImage);
        // Category List Images
        placeImage = rootView.findViewById(R.id.img_place);
        restaurantImage = rootView.findViewById(R.id.img_resturant);
        hotelImage = rootView.findViewById(R.id.img_hotel);

        // Main Screens
        mainTitle = rootView.findViewById(R.id.mainTitle);
        mainDescription = rootView.findViewById(R.id.mainDes);
        categoryTitle = rootView.findViewById(R.id.mainCategory);
        // Category List Titles
        placeTitle = rootView.findViewById(R.id.place_title);
        restaurantTitle = rootView.findViewById(R.id.restaurant_title);
        hotelTitle = rootView.findViewById(R.id.hotel_title);

        
        // Fetch data from FireStore
        Executor executor = Executors.newFixedThreadPool(4);
        executor.execute(() -> {
            try {
                    // Fetch data from the "mainInfo" document
                    db.collection("HomeScreen").document("mainInfo")  // document ID to retrieve data
                        .get().addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    // Populate data into views
                                    mainTitle.setText(document.getString("mainTitle"));
                                    mainDescription.setText(document.getString("mainDescription"));
                                    // Load images using Glide
                                    Glide.with(this).load(document.getString("mainImage")).into(mainImage);

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


                // Fetch data from the "horizontalScrollView" document
                db.collection("HomeScreen").document("horizontalScrollView")
                        .get()
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    // Populate data from "scrollView" document
                                    Map<String, Object> categoryList = (Map<String, Object>) document.get("categoryList");
                                    String category_title = document.getString("categoryTitle");
                                    String hImg = (String) categoryList.get("hotelImg");
                                    String hTitle = (String) categoryList.get("hotelTitle");
                                    String  resImg = (String) categoryList.get("restaurantImg");
                                    String resTitle = (String) categoryList.get("restaurantTitle");
                                    String pImg = (String) categoryList.get("placeImg");
                                    String pTitle = (String) categoryList.get("placeTitle");

                                    categoryTitle.setText(category_title);
                                    hotelTitle.setText(hTitle);
                                    placeTitle.setText(pTitle);
                                    restaurantTitle.setText(resTitle);

                                    // Load images using Glide
                                    Glide.with(this).load(pImg).into(placeImage);
                                    Glide.with(this).load(resImg).into(restaurantImage);
                                    Glide.with(this).load(hImg).into(hotelImage);
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