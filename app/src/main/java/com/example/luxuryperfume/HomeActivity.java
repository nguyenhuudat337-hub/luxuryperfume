package com.example.luxuryperfume;
import android.media.Image;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.os.Handler;
import android.os.Looper;
import android.widget.LinearLayout;
import android.widget.ImageView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

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



