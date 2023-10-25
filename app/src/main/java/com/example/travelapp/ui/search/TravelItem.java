package com.example.travelapp.ui.search;

import java.io.Serializable;

public class TravelItem implements Serializable {
    private String placeTitle, placeImage, documentId;

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
    public String getDocumentId() {
        return documentId;
    }
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

}
