package com.example.travelapp.ui.search;

public class TravelItem {
    private String placeTitle, placeImage;

    // Constructor
    public TravelItem(String placeImage, String placeTitle){
        this.setPlaceImage(placeImage);
        this.setPlaceTitle(placeTitle);
    }

    // Getters and Setters
    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }
    public String getPlaceTitle() {
        return placeTitle;
    }
    public void setPlaceImage(String placeImage) {
        this.placeImage = placeImage;
    }
    public String getPlaceImage() {
        return placeImage;
    }

}
