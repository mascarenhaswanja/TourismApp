package com.example.touristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class AttractionDetail extends AppCompatActivity  {

    // @TODO
    //  Need to know what user
//    if (email.equals("thanos@gmail.com")) {
//        write thanosRatings.json
//    } else if (email.equals("wonderwoman@yahoo.com")) {
//        write thanosRatings.json
//    }

    //  attraction id and ratting

    final String TAG = "Tourist-Detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);

        Intent intent = getIntent();
        Attraction attraction = (Attraction)intent.getSerializableExtra("attractionDetail");

        //ERRO
        String emailRating = intent.getStringExtra("emailUser");

        Log.d(TAG, "Attraction Detail " + attraction.name + " " + emailRating);
      //  Toast.makeText(this, "Attraction " +  attraction.mainImage, Toast.LENGTH_SHORT).show();

        if (getIntent() != null && getIntent().getExtras() != null) {
                TextView detailName = findViewById(R.id.tvName);
                detailName.setText(attraction.name);

                TextView detailDescription = findViewById(R.id.tvDescription);
                detailDescription.setText(attraction.description);

                TextView detailAddress = findViewById(R.id.tvAddress);
                detailAddress.setText(attraction.address);

                TextView detailPrice = findViewById(R.id.tvPrice);
                String msgPrice = " $ " + attraction.price;
                detailPrice.setText(msgPrice);
              //  detailPrice.setText(String.valueOf(attraction.price));

                TextView detailPhone = findViewById(R.id.tvPhone);
                detailPhone.setText(attraction.phone);

                TextView detailWebsite = findViewById(R.id.tvWebsite);
                detailWebsite.setText(attraction.website);

                ImageView img1 = findViewById(R.id.imImage1) ;
                String image1 = (String)attraction.mainImage;
                int resID1 = getResources().getIdentifier(image1, "drawable", getPackageName());
                img1.setImageResource(resID1);

                ImageView img2 = findViewById(R.id.imImage2) ;
                String image2 = (String)attraction.mainImage;
                int resID2 = getResources().getIdentifier(image2, "drawable", getPackageName());
                img1.setImageResource(resID2);
        }
    }

    //  @TODO Change Button to onTouch
    public void btnGoToWebsite(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //@TODO change website to  valid Uri.parse
//        String detailWebsite = attraction.website;
//        Log.d(TAG, detailWebsite);
        intent.setData(Uri.parse("https://bondinho.com.br"));
        // Cheks if can be resolved before starting activity
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public void btnGoToDial(View view) {
        // Create the implicit Intent to use to start a new Activity.
        //@TODO change website to  valid Phone parse
//        String detailPhone = attraction.phone;
//        Log.d(TAG, detailPhone);
        Intent intent =
                new Intent(Intent.ACTION_DIAL, Uri.parse("tel:416-845-9000"));

        // Check if an Activity exists to perform this action.
        PackageManager pm = getPackageManager();
        ComponentName cn = intent.resolveActivity(pm);
        if (cn == null) {
            // There is no Activity available to perform the action
            // Log an error and modify app behavior accordingly,
            // typically by disabling the UI element that would allow
            // users to attempt this action.
            Log.e(TAG, "Intent could not resolve to an Activity.");
        }
        else {
            startActivity(intent);
        }
    }

    public void btnGoBack(View view) {
        // create an Intent
        Intent intent = new Intent(this, AttractionList.class);
        startActivity(intent);
    }

    // @TODO SAVE to JSON
    // public void saveRating(View view) {
//    public void saveRating(String userEmail) {
//        final String FILE_NAME="userEmail.json";
//        File file = new File(this,getFilesDir(), FILE_NAME);
//
//        FileReader fileReader = null;
//        FileWriter fileWriter = null;
//        BufferedReader bufferedReader = null;
//        BufferedWriter bufferedWriter = null;
//
//        String response = null;
//
//        if (!file.exists()) {
//            try {
//                file.createNewFile();
//                fileWriter = new FileWriter((file.getAbsoluteFile()));
//                bufferedWriter = new BufferedWriter(fileWriter);
//                bufferedWriter.write("{}");
//                bufferedWriter.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        SringBuffer output = new StringBuffer();
//        fileReader = new FileReader(file.getAbsolutePath())
//
//
//    }
}