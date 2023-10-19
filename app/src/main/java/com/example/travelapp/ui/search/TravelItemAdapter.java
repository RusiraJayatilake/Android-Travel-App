package com.example.travelapp.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelapp.R;

import java.util.List;

public class TravelItemAdapter extends RecyclerView.Adapter<TravelItemAdapter.ViewHolder> {

    private List<TravelItem> travelItems;

    public TravelItemAdapter(List<TravelItem> travelItems) {
        this.travelItems = travelItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TravelItem item = travelItems.get(position);
        // Bind data to the views in the ViewHolder here
        holder.nameTextView.setText(item.getPlaceTitle());
        holder.descriptionTextView.setText(item.getPlaceImage());
        // Add more bindings as needed
    }

    @Override
    public int getItemCount() {
        return travelItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.searchImage);
            descriptionTextView = itemView.findViewById(R.id.searchTitle);
        }
    }

    public void setData(List<TravelItem> data) {
        travelItems.clear(); // Clear the existing data
        travelItems.addAll(data); // Add the new data
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }
}

