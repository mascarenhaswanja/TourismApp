package com.example.touristapp;

public class Rating {
    int attractionRating;
    int rating;

    public Rating(int attractionRating, int rating) {
        this.attractionRating = attractionRating;
        this.rating = rating;
    }

    public int getAttractionRating() {
        return attractionRating;
    }

    public void setAttractionRating(int attractionRating) {
        this.attractionRating = attractionRating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

