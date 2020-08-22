package com.example.travelguide;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ScenicSpotActivity extends AppCompatActivity {
    TextView name, address, price, about, hours;
    ImageView picture;
    ScenicSpot currentScenicSpot;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_inform);

        name = findViewById(R.id.detail_name_textView);
        address = findViewById(R.id.detail_address_textView);
        price = findViewById(R.id.detail_prices_textView);
        about = findViewById(R.id.detail_about_textView);
        hours = findViewById(R.id.detail_hours_textView);
        picture = findViewById(R.id.detail_imageView);

        currentScenicSpot = (ScenicSpot) getIntent().getSerializableExtra("ScenicSpot");

        setTitle(currentScenicSpot.getName());

        name.setText(currentScenicSpot.getName());
        address.setText(currentScenicSpot.getAddress());
        price.setText(currentScenicSpot.getPrice());
        about.setText(currentScenicSpot.getAbout());
        hours.setText(currentScenicSpot.getHours());

        picture.setImageResource(currentScenicSpot.getPicture());
    }


}
