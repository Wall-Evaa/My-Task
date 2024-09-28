package com.example.mytask;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_second);

        // Initialize views
//        TextView store = findViewById(R.id.storeName);
        ShapeableImageView imageView = findViewById(R.id.headingImage);
        TextView tvTitle = findViewById(R.id.headingTitle);
        TextView promo = findViewById(R.id.detailPromo);
        TextView startDate = findViewById(R.id.startDate);
        TextView category = findViewById(R.id.category);
        TextView description = findViewById(R.id.description);
        TextView endDate = findViewById(R.id.endDate);

        // Retrieve the intent
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();

            // Extract data with default values to prevent null
            String pTitle = bundle.getString("title", "No Title");
            String pImage = bundle.getString("image", "");
            String pPromo = bundle.getString("promocode", "No Promo");
            String pStart = bundle.getString("startdate", "No Start Date");
            String pEnd = bundle.getString("enddate", "No End Date");
            String pCategory = bundle.getString("category", "No Category");
            String pDesc = bundle.getString("description", "No Description");
            String pStorename = bundle.getString("storeName", "No Store Name");

            // Log the values for debugging
            Log.d("DetailActivity", "Title: " + pTitle);
            Log.d("DetailActivity", "Image: " + pImage);
            Log.d("DetailActivity", "Promo: " + pPromo);
            Log.d("DetailActivity", "Start Date: " + pStart);
            Log.d("DetailActivity", "End Date: " + pEnd);
            Log.d("DetailActivity", "Category: " + pCategory);
            Log.d("DetailActivity", "Description: " + pDesc);
            Log.d("DetailActivity", "Store Name: " + pStorename);

            // Load image and set text
            if (!pImage.isEmpty()) {
                Glide.with(DetailActivity.this)
                        .load(pImage)
                        .placeholder(R.drawable.ic_launcher_background)
                        .into(imageView);
            }

//            store.setText(pStorename);
            tvTitle.setText(pTitle);
            promo.setText(pPromo);
            startDate.setText(pStart);
            endDate.setText(pEnd);
            category.setText(pCategory);
            description.setText(pDesc);
        } else {
            Log.e("DetailActivity", "Intent or extras are null");
        }
    }
}
