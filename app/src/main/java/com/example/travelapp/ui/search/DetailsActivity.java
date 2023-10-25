package com.example.travelapp.ui.search;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.travelapp.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;

public class DetailsActivity extends AppCompatActivity {
    private ImageView activityImg;
    private TextView activityTitle, activityDes;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        activityImg = findViewById(R.id.activityImage);
        activityTitle = findViewById(R.id.activityTitle);
        activityDes = findViewById(R.id.activityDes);
        db = FirebaseFirestore.getInstance();

    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        Intent intent = getIntent();
//        if (intent != null) {
//            String placeId = intent.getStringExtra("placeId"); // Assuming you pass the Firestore document ID
//
//            if (placeId != null) {
//                // Reference to the Firestore document
//                DocumentReference placeRef = db.collection("places").document(placeId);
//
//                // Retrieve data from Firestore
//                placeRef.get().addOnSuccessListener(documentSnapshot -> {
//                    if (documentSnapshot.exists()) {
//                        String placeImageUrl = documentSnapshot.getString("image");
//                        String placeTitle = documentSnapshot.getString("title");
//                        String placeDescription = documentSnapshot.getString("description");
//
//                        // Update the views with the retrieved data
//                        Glide.with(this).load(placeImageUrl).into(activityImg);
//                        activityTitle.setText(placeTitle);
//                        activityDes.setText(placeDescription);
//                    }
//                }).addOnFailureListener(e -> {
//                    // Handle the case where data retrieval from Firestore fails
//                });
//            }
//        }
//    }
}