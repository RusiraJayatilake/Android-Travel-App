package com.example.travelapp.ui.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.travelapp.R;
import java.util.ArrayList;

public class TravelItemAdapter extends RecyclerView.Adapter<TravelItemAdapter.ViewHolder> {

    private Context context;
    private ArrayList<TravelItem> travelItems;

    public TravelItemAdapter(ArrayList<TravelItem> items){
        this.travelItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.travel_item, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TravelItem item = travelItems.get(position);

        // Bind data to the views in the ViewHolder here
        holder.placeTitle.setText(item.getPlaceTitle());

        String imgUrl = item.getPlaceImage();
        ImageView imageView = holder.placeImage;

        Glide.with(context).load(imgUrl).apply(
                new RequestOptions().placeholder(R.id.searchImage)
        ).into(imageView);

    }

    @Override
    public int getItemCount() {
        return travelItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView placeImage;
        TextView placeTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            placeImage = itemView.findViewById(R.id.searchImage);
            placeImage = itemView.findViewById(R.id.searchTitle);
        }
    }

    public void setData(ArrayList<TravelItem> items) {
        this.travelItems = items;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }
}

