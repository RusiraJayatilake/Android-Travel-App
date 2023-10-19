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
import com.example.travelapp.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {
    private static final String TAG = "Travel Search";
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FrameLayout searchScreen;
    private RecyclerView searchResults;
    private TravelItemAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        searchScreen = rootView.findViewById(R.id.search_screen);

        searchResults = rootView.findViewById(R.id.searchResults);
        adapter = new TravelItemAdapter(new ArrayList<>());
        searchResults.setLayoutManager(new LinearLayoutManager(getContext()));
        searchResults.setAdapter(adapter);

        // Query Firestore and update the adapter with data
        // Example Firestore query:
//         db.collection("your_collection_name")
//           .get()
//           .addOnCompleteListener(task -> {
//               if (task.isSuccessful()) {
//                   List<TravelItem> items = new ArrayList<>();
//                   for (QueryDocumentSnapshot document : task.getResult()) {
//                       TravelItem item = document.toObject(TravelItem.class);
//                       items.add(item);
//                   }
//                   adapter.setData(items);
//               } else {
//                   Log.d(TAG, "Error getting documents: ", task.getException());
//               }
//           });

        return rootView;
    }
}