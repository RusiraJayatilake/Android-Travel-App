package com.example.travelapp.ui.search;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.Toast;

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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class SearchFragment extends Fragment {
    private static final String TAG = "TravelSearch";
    private FirebaseFirestore db;
    private CollectionReference collectionRef;
    private RecyclerView searchResults;
    private TravelItemAdapter adapter;
    private SearchView searchView;
    private TravelItem travelItem;

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

        // Event listeners
        searchResults.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // Get the Firestore document ID of the clicked item
                String documentId = adapter.getItemDocumentId(position);

                // Create an Intent to start the DetailsActivity
                Intent intent = new Intent(getContext(), DetailsActivity.class);
                intent.putExtra("place1", documentId); // Pass the Firestore document ID as "place1"
                startActivity(intent);
            }
        }));


        fetchDataFromFirestore();

        return rootView;
    }

    // Search data
    private void searchDataFromFirestore(String query) {
        collectionRef.whereEqualTo("search", query.toLowerCase())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                        ArrayList<TravelItem> items = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String imageUrl = document.getString("image");
                            String title = document.getString("title");
                            String documentId = document.getId();
                            items.add(new TravelItem(imageUrl, title, documentId));
                            if(travelItem != null){
                                travelItem.setDocumentId(documentId);
                            } else{
                                Toast.makeText(getContext(), "Document id is null", Toast.LENGTH_SHORT).show();
                            }
                            //travelItem.setDocumentId(documentId);

                            Log.d("Document ID", documentId);
                        }
                        adapter.setData(items);
                        adapter.notifyDataSetChanged();
                        } else {
                            Log.e(TAG, "Error getting documents: " + task.getException());
                        }
                    }
                });

    }

    // Fetch data from firestore
    private void fetchDataFromFirestore() {
        Executor executor = Executors.newFixedThreadPool(4);
        executor.execute(() -> {
            try{
                collectionRef.get().addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<TravelItem> items = new ArrayList<>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String imageUrl = document.getString("image");
                            String title = document.getString("title");
                            String docId = document.getId();
                            items.add(new TravelItem(imageUrl, title, docId));
                        }
                        // Update the adapter with the retrieved data
                        adapter.setData(items);
                        adapter.notifyDataSetChanged();
                    } else {
                        // Handle the error
                        requireActivity().runOnUiThread(() -> {
                            Toast.makeText(getContext(), "Error getting documents:", Toast.LENGTH_SHORT).show();
                            //Log.e(TAG, " " + task.getException());
                        });

                    }
                });

            } catch (Exception e){
                e.printStackTrace();

            }
        });

    }

}