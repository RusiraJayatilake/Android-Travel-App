package com.example.travelapp.ui.home;

import androidx.lifecycle.ViewModelProvider;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.travelapp.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {
    private FrameLayout homeScreen;
    private ImageView homeImages;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference httpsReference;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        homeScreen = rootView.findViewById(R.id.home_screen);
        homeImages = rootView.findViewById(R.id.mainImage);
        homeImages = rootView.findViewById(R.id.img_place);

        // Firebase Storage reference URL.
        String[] imageUrl = {
                "https://firebasestorage.googleapis.com/v0/b/travelappdb-83e9e.appspot.com/o/HomeScreen%2Fhome_img1.jpg?alt=media&token=9a52f2e9-57b0-441f-83e8-61b7d0b07215",
                "https://firebasestorage.googleapis.com/v0/b/travelappdb-83e9e.appspot.com/o/HomeScreen%2Fplaces.jpg?alt=media&token=ae5cce80-525f-4037-9be5-f084bee70ad6",
                "https://firebasestorage.googleapis.com/v0/b/travelappdb-83e9e.appspot.com/o/HomeScreen%2Fhotels.jpg?alt=media&token=f12faa20-0674-4f6b-a904-dec91635a97d",
                "https://firebasestorage.googleapis.com/v0/b/travelappdb-83e9e.appspot.com/o/HomeScreen%2Frestaurants.jpg?alt=media&token=6901b8f6-4bff-4896-b729-74b17fc2e96f"


        };


        // Create a reference to the image
         httpsReference = storage.getReferenceFromUrl(imageUrl[0]);
         httpsReference = storage.getReferenceFromUrl(imageUrl[1]);

        // fetch images from firebase storage using a background thread
//        Executor executor = Executors.newSingleThreadExecutor();
//        executor.execute(() -> {
//            try {
//                // Fetch and display the image
//                imageUrl = httpsReference.getDownloadUrl().getResult().toString();
//
//                // Load the image using Glide on the UI thread
//                requireActivity().runOnUiThread(() -> {
//                    Glide.with(this)
//                            .load(imageUrl)
//                            .into(homeImages);
//                });
//            } catch (Exception e) {
//                e.printStackTrace();
//                // Handle any errors that occur.
//                requireActivity().runOnUiThread(() -> {
//                    Toast.makeText(getContext(), "Error downloading image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
//                });
//            }
//        });


        httpsReference.getDownloadUrl().addOnSuccessListener(uri -> {
            imageUrl[0] = uri.toString();
            imageUrl[1] = uri.toString();
            // Load the image using Glide
            Glide.with(this).load(imageUrl[0]).into(homeImages);
            Glide.with(this).load(imageUrl[1]).into(homeImages);
        }).addOnFailureListener(exception -> {
            // Handle any errors that occur.
            Toast.makeText(getContext(), "Error downloading image: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
        });

        return rootView;
    }


}