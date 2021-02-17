package com.example.touristapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Attraction implements Serializable {
    int id;
    String name;
    String address;
    String description;
    int price;
    String website;
    String phone;
    String mainImage;
    String image1;
    String image2;

    public Attraction(int id, String name, String address, String description, int price, String website, String phone, String mainImage, String image1, String image2) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.price = price;
        this.website = website;
        this.phone = phone;
        this.mainImage = mainImage;
        this.image1 = image1;
        this.image2 = image2;
    }
}
