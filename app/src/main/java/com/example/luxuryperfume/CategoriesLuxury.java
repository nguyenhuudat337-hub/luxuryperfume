package com.example.luxuryperfume;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

public class CategoriesLuxury extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories_luxury);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.luxury), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button edtBack = findViewById(R.id.buttonlux);
        edtBack.setOnClickListener(v -> {
            Intent intent = new Intent(CategoriesLuxury.this,HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }
}