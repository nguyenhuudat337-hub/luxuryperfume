package com.example.luxuryperfume;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetail extends AppCompatActivity {
    ImageView imgProduct;
    TextView txtName, txtPrice, txtDescription;
    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.productdetail), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgProduct = findViewById(R.id.imgProduct);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);
        ratingBar = findViewById(R.id.ratingBar);

        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        int image = intent.getIntExtra("image", 0);
        String description = intent.getStringExtra("description");
        boolean isFavorite = intent.getBooleanExtra("isFavorite", false);

        txtName.setText(name);
        txtPrice.setText(price);
        txtDescription.setText(description);
        imgProduct.setImageResource(image);

        ratingBar.setRating(4.5f);

        Button button3 = findViewById(R.id.Button3);
        button3.setOnClickListener(v -> {
            finish();
        });

        ImageButton btnFavoriteDetail = findViewById(R.id.imageButton2);

        // Trong onCreate của ProductDetail
        Product product = (Product) getIntent().getSerializableExtra("product_obj");

        if (isFavorite) {
            btnFavoriteDetail.setImageResource(R.drawable.ic_favoritedam);
        } else {
            btnFavoriteDetail.setImageResource(R.drawable.ic_favorite);
        }


    }
}
