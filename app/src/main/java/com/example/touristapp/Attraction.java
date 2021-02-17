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
//  String image1;
//    String image2;
 //   ArrayList<String> images = new ArrayList<String>();

  //  public Attraction(int id, String name, String address, String description, int price, String website, String phone, ArrayList images) {
    public Attraction(int id, String name, String address, String description, int price, String website, String phone, String mainImage) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.price = price;
        this.website = website;
        this.phone = phone;
        this.mainImage = mainImage;
//        this.image1 = image1;
//        this.image2 = image2;

        //  @TODO - review  store images
//        for (int i=0; i < images.size(); i++){
//            this.images.add(String.valueOf(images.get(i)));
//        }

    }
}
