package com.example.travelapp.ui.search;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.example.travelapp.MainActivity;
import com.example.travelapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private static final String TAG = "TravelSearch";
    private FirebaseFirestore db;
    private CollectionReference collectionRef;
    private RecyclerView searchResults;
    private TravelItemAdapter adapter;
    private SearchView searchView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        db = FirebaseFirestore.getInstance();
        collectionRef = db.collection("SearchScreenData");

        searchResults = rootView.findViewById(R.id.searchResults);
        adapter = new TravelItemAdapter(getContext(), new ArrayList<>()); // Initialize with an empty list
        searchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        searchResults.setAdapter(adapter);

        searchView = rootView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchDataFromFirestore(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // Handle real-time search
                searchDataFromFirestore(s);
                return false;
            }
        });

        fetchDataFromFirestore();

        return rootView;
    }

    // ** when fetching data from the firestore that data should be set into an arraylist
    private void searchDataFromFirestore(String query) {
        collectionRef.whereEqualTo("search", query.toLowerCase())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                        ArrayList<TravelItem> items = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String imageUrl = document.getString("placeImage");
                            String title = document.getString("placeTitle");
                            items.add(new TravelItem(imageUrl, title));
                        }
                        adapter.setData(items);
                        adapter.notifyDataSetChanged();
                        } else {
                            Log.e(TAG, "Error getting documents: " + task.getException());
                        }
                    }
                });

    }

    private void fetchDataFromFirestore() {
        collectionRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<TravelItem> items = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String imageUrl = document.getString("placeImg");
                    String title = document.getString("placeTitle");
                    items.add(new TravelItem(imageUrl, title));
                }
                // Update the adapter with the retrieved data
                adapter.setData(items);
                adapter.notifyDataSetChanged();
            } else {
                // Handle the error
                Log.e(TAG, "Error getting documents: " + task.getException());
            }
        });
    }

}