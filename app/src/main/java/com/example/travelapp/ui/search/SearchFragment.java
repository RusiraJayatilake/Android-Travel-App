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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private static final String TAG = "TravelSearch";
    private FirebaseFirestore db;
    private RecyclerView searchResults;
    private TravelItemAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);

        db = FirebaseFirestore.getInstance();
        searchResults = rootView.findViewById(R.id.searchResults);
        adapter = new TravelItemAdapter(new ArrayList<>()); // Initialize with an empty list

        searchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        searchResults.setAdapter(adapter);

        SearchView searchView = rootView.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                PerformSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                PerformSearch(newText);
                return true;
            }
        });


        return rootView;
    }

    private void PerformSearch(String query){
        // Query Firestore and update the adapter with data
        db.collection("search_screen")
                .whereEqualTo("place_title", query)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<TravelItem> items = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            TravelItem item = document.toObject(TravelItem.class);
                            items.add(item);
                        }
                        adapter.setData(items); // Assuming you have a setData method in TravelItemAdapter
                    } else {
                        Log.e(TAG, "Error getting documents", task.getException());
                    }
                });

    }
}