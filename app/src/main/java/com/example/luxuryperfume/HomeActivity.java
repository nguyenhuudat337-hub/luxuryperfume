package com.example.luxuryperfume;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.Handler;
import android.os.Looper;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    ViewPager2 bannerSlider;
    LinearLayout layoutIndicator;
    Handler sliderHandler = new Handler(Looper.getMainLooper());
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.home), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        bannerSlider = findViewById(R.id.bannerSlider);
        layoutIndicator = findViewById(R.id.layoutIndicator);
        int[] banners = {
                R.drawable.img_banner,
                R.drawable.img_banner1,
                R.drawable.img_banner2,
                R.drawable.img_banner3
        };
        BannerAdapter bannerAdapter = new BannerAdapter(banners);
        bannerSlider.setAdapter(bannerAdapter);

        setupIndicators(banners.length);
        setCurrentIndicator(0);

        bannerSlider.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    setCurrentIndicator(position);
                    sliderHandler.removeCallbacks(sliderRunnable);
                    sliderHandler.postDelayed(sliderRunnable, 3000);
                }
        });

        TextView edtwomen = findViewById(R.id.c1);
        edtwomen.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoriesWomen.class);
            startActivity(intent);
            finish();
        });

        TextView edtman = findViewById(R.id.c2);
        edtman.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoriesMan.class);
            startActivity(intent);
            finish();
        });

        TextView edtluxury = findViewById(R.id.c3);
        edtluxury.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoriesLuxury.class);
            startActivity(intent);
            finish();
        });

        TextView edtsale = findViewById(R.id.c4);
        edtsale.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoriesSale.class);
            startActivity(intent);
            finish();
        });


        TextView edtnew = findViewById(R.id.c5);
        edtnew.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoriesNew.class);
            startActivity(intent);
            finish();
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerProducts);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Product> product = new ArrayList<>();
        product.add(new Product("Dior Sauvage", "$120", R.drawable.perfume1));
        product.add(new Product("Versace", "$135", R.drawable.perfume2));
        product.add(new Product("YSL Y Eau de Parfum", "$210", R.drawable.perfume3));
        product.add(new Product("Bleu de Chanel", "$325", R.drawable.perfume4));
        product.add(new Product("Tom Ford Oud Wood", "$145", R.drawable.perfume5));
        product.add(new Product("Acqua di Gio Profumo", "$150", R.drawable.perfume6));

        product.add(new Product("Creed Aventus", "$110", R.drawable.perfume7));
        product.add(new Product("Paco Rabanne 1 Million", "$115", R.drawable.perfume8));
        product.add(new Product("Jean Paul Gaultier Le Male", "$125", R.drawable.perfume9));
        product.add(new Product("Maison Francis Kurkdjian Baccarat Rouge 540", "$350", R.drawable.perfume10));
        product.add(new Product("Parfums de Marly Layton", "$280", R.drawable.perfume11));




        ProductAdapter adapter = new ProductAdapter(product);

        recyclerView.setAdapter(adapter);

    }




    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for(int i = 0; i < childCount; i++){
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if(i == position){
                imageView.setImageResource(R.drawable.indicator_active);
            }else{
                imageView.setImageResource(R.drawable.indicator_inactive);
            }
        }
    }

    private void setupIndicators(int count) {
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );


        params.setMargins(8,0,8,0);
        for(int i = 0; i < count; i++){
            indicators[i] = new ImageView(this);
            indicators[i].setImageResource(R.drawable.indicator_inactive);
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
    }



    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            int nextItem = bannerSlider.getCurrentItem() + 1;
            if(nextItem >= bannerSlider.getAdapter().getItemCount()){
                nextItem = 0;
            }
            bannerSlider.setCurrentItem(nextItem);
        }
    };
}



