package com.example.travelapp.ui.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import java.util.List;

public class TravelItemAdapter extends RecyclerView.Adapter<TravelItemAdapter.ViewHolder> {
    private Context context;
    private List<TravelItem> travelItems;

    public TravelItemAdapter(Context context, List<TravelItem> items){
        this.context = context;
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

        Glide.with(context).load(imgUrl).into(imageView);
    }

    @Override
    public int getItemCount() {
        return travelItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView placeImage;
        TextView placeTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            placeImage = itemView.findViewById(R.id.searchImage);
            placeTitle = itemView.findViewById(R.id.searchTitle);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }


    public void setData(ArrayList<TravelItem> items) {
        this.travelItems = items;
        notifyDataSetChanged(); // Notify the adapter that the data has changed
    }

    public String getItemDocumentId(int position){
        return travelItems.get(position).getDocumentId();
    }

}


class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    GestureDetector mGestureDetector;

    public RecyclerItemClickListener(Context context, OnItemClickListener listener) {
        mListener = listener;
        mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView view, @NonNull MotionEvent e) {
        View childView = view.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView view, @NonNull MotionEvent motionEvent) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }
}



