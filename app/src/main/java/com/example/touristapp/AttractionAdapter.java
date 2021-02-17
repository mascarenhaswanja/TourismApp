package com.example.touristapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.pm.PackageManager;

import java.util.ArrayList;

public class AttractionAdapter extends ArrayAdapter<Attraction> {
    public AttractionAdapter (Context context, ArrayList<Attraction> attractions) {
        super(context, 0, attractions);
    }

    final String TAG = "Tourist";

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       Attraction attraction = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.attraction_row_layout, parent, false);
        }
        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.tvAttractionName);
        tvName.setText(attraction.name);

        TextView tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
        tvAddress.setText(attraction.address);

        ImageView mainImage = (ImageView) convertView.findViewById(R.id.imMain);
        String image = (String)attraction.mainImage;
        int imgID = getContext().getResources().getIdentifier(image, "drawable", getContext().getPackageName());
        mainImage.setImageResource(imgID);

        return convertView;
    }

}