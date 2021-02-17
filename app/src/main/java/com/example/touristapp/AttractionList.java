package com.example.touristapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class AttractionList extends AppCompatActivity implements Serializable {

    final String TAG = "Tourist-List";

    ArrayList<Attraction> attractions = new ArrayList<Attraction>();
    AttractionAdapter adapter = null;
    Intent user;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction_list);

        user = getIntent();
        username = user.getStringExtra("u");
        Log.d(TAG, "User " + username);

        ListView lv = findViewById((R.id.lvAttractions));
        adapter = new AttractionAdapter(this, attractions);
        lv.setAdapter(adapter);

       String fileContents = loadDataFromFile("attractions.json");
        JSONObject listAttractions = convertToJSON(fileContents);
        parseJSONData(listAttractions);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long id) {
                Intent intent = new Intent(AttractionList.this, AttractionDetail.class);
                intent.putExtra("attractionDetail", attractions.get(position));
                intent.putExtra("u", username);
                startActivity(intent);
            }
        });
    }

        public String loadDataFromFile(String FILENAME) {
                String jsonString;

                try {
                    // open the file
                    InputStream fileData = this.getAssets().open(FILENAME);

                    // get information about the file
                    int fileSize = fileData.available();

                    // set up a array to store each piece of data in the file
                    // the size of the array is the same size as the file
                    byte[] buffer = new byte[fileSize];

                    // get all the data from the file
                    fileData.read(buffer);

                    // close the file
                    fileData.close();

                    // convert the data to json
                    jsonString = new String(buffer, "UTF-8");

                    return jsonString;

                } catch (IOException e) {
                    Log.d(TAG, "Error opening file Attractions.");
                    e.printStackTrace();
                    return null;
                }
        }

            // 2. convert that string to either a JSONArray or JSONObject (depends on the file)
        public JSONObject convertToJSON(String fileData) {
                JSONObject jsonData;
                try {
                    // 1. try to convert the string to the JSONObject data type
                    jsonData = new JSONObject(fileData);
                    // 2. if successful return it
                    return jsonData;

                } catch (JSONException e) {
                    // 2. if conversion fails, then print error message and return
                    e.printStackTrace();
                    return null;
                }

        }

            // 3. Parse the relevant data that we need
        public void parseJSONData(JSONObject jsonObject) {
                Log.d(TAG, "Successfully got the file contents as a json object");

                try {
                    JSONArray attactionList = jsonObject.getJSONArray("attractions");
                    for (int i = 0; i < attactionList.length(); i++) {
                        JSONObject attraction = attactionList.getJSONObject(i);
                        String attName = attraction.getString("name");
                        String attAddress = attraction.getString("address");
                        String attDescription = attraction.getString("description");
                        int attPrice = attraction.getInt("price");
                        String attWebsite = attraction.getString("website");
                        String attPhone = attraction.getString("phone");
                        String attMainImage = attraction.getString("mainImage");
                        JSONArray imageList = attraction.getJSONArray("images");
                        String attImage1 = imageList.getString(0);
                        String attImage2 = imageList.getString(1);

                        attractions.add(new Attraction(i, attName, attAddress, attDescription, attPrice, attWebsite, attPhone, attMainImage, attImage1, attImage2));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
        }

    public void logoutPressed(View view) {
        // create an Intent
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}