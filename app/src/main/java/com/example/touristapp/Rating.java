package com.example.touristapp;

public class Rating {
    int attractionRating;
    float rating;

    public Rating(int attractionRating, float rating) {
        this.attractionRating = attractionRating;
        this.rating = rating;
    }

    public int getAttractionRating() {
        return attractionRating;
    }

    public void setAttractionRating(int attractionRating) {
        this.attractionRating = attractionRating;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float  rating) {
         this.rating = rating;
    }
}

