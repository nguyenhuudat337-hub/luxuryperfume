package com.example.luxuryperfume;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        // Load HomeFragment by default
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, new HomeFragment())
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment fragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                fragment = new HomeFragment();
            } else if (itemId == R.id.favorite) {
                fragment = new FavoriteFragment();
            } else if (itemId == R.id.cart) {
                fragment = new CartFragment();
            } else if (itemId == R.id.person) {
                fragment = new ProfileFragment();
            }

            if (fragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_container, fragment)
                        .commit();
                return true;
            }

            return false;
        });


    }
}
