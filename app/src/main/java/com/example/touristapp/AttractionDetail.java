package com.example.touristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class AttractionDetail extends AppCompatActivity {

    // @TODO
    //  Need to know what user
//    if (email.equals("thanos")) {
//        write thanos.json
//    } else if (email.equals("wonderwoman")) {
//        write thanos.json
//    }

    //  attraction id and ratting

    final String TAG = "Tourist-Detail";
    RatingBar ratingBar;
    String username;
    ArrayList<Rating> ratings ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_detail);

        Intent intent = getIntent();
        Attraction attraction = (Attraction) intent.getSerializableExtra("attractionDetail");

        username = intent.getStringExtra("u");

        Log.d(TAG, "Attraction Detail " + attraction.name + " " + username);
        //  Toast.makeText(this, "Attraction " +  attraction.mainImage, Toast.LENGTH_SHORT).show();

        if (getIntent() != null && getIntent().getExtras() != null) {
            TextView detailName = findViewById(R.id.tvName);
            detailName.setText(attraction.name);

            TextView detailDescription = findViewById(R.id.tvDescription);
            detailDescription.setText(attraction.description);

            TextView detailAddress = findViewById(R.id.tvAddress);
            detailAddress.setText(attraction.address);

            TextView detailPrice = findViewById(R.id.tvPrice);
            String msgPrice;

            if (attraction.price == 0) {
                msgPrice = " $ FREE";
            } else {
                msgPrice = " $ " + attraction.price;
            }
            detailPrice.setText(msgPrice);
            //  detailPrice.setText(String.valueOf(attraction.price));

            TextView detailPhone = findViewById(R.id.tvPhone);
            detailPhone.setText(attraction.phone);
//            Linkify.addLinks(detailPhone, Linkify.PHONE_NUMBERS);

            TextView detailWebsite = findViewById(R.id.tvWebsite);
            detailWebsite.setText(attraction.website);
            Linkify.addLinks(detailWebsite, Linkify.ALL);

            ImageView img1 = findViewById(R.id.imImage1);
            String image1 = (String) attraction.image1;
            int resID1 = getResources().getIdentifier(image1, "drawable", getPackageName());
            img1.setImageResource(resID1);

            ImageView img2 = findViewById(R.id.imImage2);
            String image2 = (String) attraction.image2;
            int resID2 = getResources().getIdentifier(image2, "drawable", getPackageName());
            img2.setImageResource(resID2);

//            detailWebsite.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(), "WEBSITE", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse(attraction.website));
//
//                    if (intent.resolveActivity(getPackageManager()) != null) {
//                        startActivity(intent);
//                    }
//
//                }
//
//            });
//
            detailPhone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), "PHONE ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+attraction.phone));
                    startActivity(intent);

                    PackageManager pm = getPackageManager();
                    ComponentName cn = intent.resolveActivity(pm);
                    if (cn == null) {
                        Log.e(TAG, "Intent could not resolve to an Activity.");
                    } else {
                        startActivity(intent);
                    }
                }

            });

//            ratingBar = (RatingBar) findViewById(R.id.rbAttraction);
//            // @TODO getRating
//            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//                @Override
//                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
//                    Log.d("TAG", "Ratting" + v + " " + b);
//                    storeRatingJSON();
//                }
//            });


        }
    }

   private void storeRatingJSON() {
        //  JSON
        Log.d(TAG, "Save Rating");
        JSONArray data = new JSONArray();
        for (int i = 0; i < this.ratings.size(); i++) {
            Rating rating = ratings.get(i);
            JSONObject object = null;

            try {
                object = new JSONObject();
                object.put("attractionRating", rating.getAttractionRating());
                object.put("rating", rating.getRating());
                data.put(i, object);
            }
            catch (Exception e) {
                Log.d(TAG, "JSON failed");
                e.printStackTrace();
            }

        }
        String toWrite  = data.toString();
        Log.d(TAG, "Data: " + toWrite);
   // this.writeToFile("test.json", data);
         /*

    // create a file object
File f = new File("program.txt");

// create a file with the absolute path
// of file f
File absolute = f.getAbsoluteFile();
f.getPath()
     */
//
//    JsonArray json = new ...
//            Json.add(userObject)

        //    try {
//        OutputStreamWriter outputStreamWriter =
//                new OutputStreamWriter(openFileOutput(USERS_FILE, Context.MODE_PRIVATE));
//        outputStreamWriter.write(data);
//        outputStreamWriter.close();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    public void saveRating(String userEmail) {
//      //  final String FILE_NAME="thanos.json";
//        File file = new File("thanos.json");
//        File absolute = file.getAbsoluteFile();
//        String path = file.getPath();
//
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
//        fileReader = new FileReader(file.getAbsolutePath());
//
//
//    }
   // }

}  // end JSON

    public void btnGoBack(View view) {
        // create an Intent
        Intent intent = new Intent(this, AttractionList.class);
        intent.putExtra("u", username);
        startActivity(intent);
    }

    public void logoutPressed(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }



}