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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        });

        TextView edtman = findViewById(R.id.c2);
        edtman.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoriesMan.class);
            startActivity(intent);
        });

        TextView edtluxury = findViewById(R.id.c3);
        edtluxury.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoriesLuxury.class);
            startActivity(intent);
        });

        TextView edtsale = findViewById(R.id.c4);
        edtsale.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoriesSale.class);
            startActivity(intent);
        });


        TextView edtnew = findViewById(R.id.c5);
        edtnew.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CategoriesNew.class);
            startActivity(intent);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerProducts);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        List<Product> product = new ArrayList<>();
        product.add(new Product(
                "Dior Sauvage",
                "$120",
                R.drawable.perfume1,
                "Dior Sauvage là dòng nước hoa nam mang phong cách mạnh mẽ và nam tính. Hương cam bergamot tươi mát kết hợp với tiêu Tứ Xuyên và ambroxan tạo nên mùi hương hiện đại, cuốn hút và rất dễ dùng hằng ngày."
        ));

        product.add(new Product(
                "Versace",
                "$135",
                R.drawable.perfume2,
                "Versace Eros mang phong cách quyến rũ và tràn đầy năng lượng. Hương bạc hà tươi mát kết hợp với táo xanh và vanilla ngọt nhẹ tạo nên mùi hương nổi bật, thích hợp cho những buổi tiệc và hẹn hò."
        ));

        product.add(new Product(
                "YSL Y Eau de Parfum",
                "$210",
                R.drawable.perfume3,
                "YSL Y Eau de Parfum là biểu tượng của người đàn ông hiện đại, tự tin và đầy tham vọng. Hương táo xanh, gừng và gỗ tuyết tùng tạo nên sự cân bằng hoàn hảo giữa tươi mát và ấm áp."
        ));

        product.add(new Product(
                "Bleu de Chanel",
                "$325",
                R.drawable.perfume4,
                "Bleu de Chanel mang phong cách thanh lịch và tinh tế. Sự kết hợp giữa cam chanh tươi mát, gừng cay nhẹ và gỗ đàn hương tạo nên mùi hương sang trọng, phù hợp cho cả công việc và những dịp đặc biệt."
        ));

        product.add(new Product(
                "Tom Ford Oud Wood",
                "$145",
                R.drawable.perfume5,
                "Tom Ford Oud Wood là dòng nước hoa cao cấp với mùi hương trầm ấm và bí ẩn. Hương gỗ oud quý hiếm kết hợp với gỗ đàn hương và vanilla tạo nên cảm giác sang trọng và đẳng cấp."
        ));

        product.add(new Product(
                "Acqua di Gio Profumo",
                "$150",
                R.drawable.perfume6,
                "Acqua di Gio Profumo mang hơi thở của đại dương kết hợp với sự mạnh mẽ của hương gỗ và nhang trầm. Mùi hương tươi mát nhưng sâu lắng, rất phù hợp với những người đàn ông trưởng thành."
        ));

        product.add(new Product(
                "Creed Aventus",
                "$110",
                R.drawable.perfume7,
                "Creed Aventus là một trong những dòng nước hoa nam nổi tiếng nhất thế giới. Hương dứa tươi mát kết hợp với gỗ bạch dương và xạ hương tạo nên mùi hương mạnh mẽ, quyền lực và đầy cuốn hút."
        ));

        product.add(new Product(
                "Paco Rabanne 1 Million",
                "$115",
                R.drawable.perfume8,
                "Paco Rabanne 1 Million nổi bật với phong cách táo bạo và quyến rũ. Hương quế, da thuộc và hổ phách tạo nên mùi hương ấm áp, thu hút và rất thích hợp cho những buổi tối đặc biệt."
        ));

        product.add(new Product(
                "Jean Paul Gaultier Le Male",
                "$125",
                R.drawable.perfume9,
                "Jean Paul Gaultier Le Male là dòng nước hoa cổ điển nhưng vẫn rất hiện đại. Hương bạc hà, lavender và vanilla tạo nên mùi hương ngọt nhẹ, nam tính và dễ gây ấn tượng."
        ));

        product.add(new Product(
                "Maison Francis Kurkdjian Baccarat Rouge 540",
                "$350",
                R.drawable.perfume10,
                "Baccarat Rouge 540 là dòng nước hoa niche cực kỳ nổi tiếng với mùi hương sang trọng và độc đáo. Sự kết hợp giữa nghệ tây, hổ phách và gỗ tuyết tùng tạo nên mùi hương ấm áp, tinh tế và đầy mê hoặc."
        ));

        product.add(new Product(
                "Parfums de Marly Layton",
                "$280",
                R.drawable.perfume11,
                "Layton của Parfums de Marly mang phong cách quý tộc và sang trọng. Hương táo, vanilla và gỗ tạo nên sự hòa quyện hoàn hảo giữa ngọt ngào, ấm áp và nam tính."
        ));




        ProductAdapter adapter = new ProductAdapter(product);

        recyclerView.setAdapter(adapter);

        LinearLayout product1 = findViewById(R.id.l1);
        LinearLayout product2 = findViewById(R.id.l2);
        LinearLayout product3 = findViewById(R.id.l3);
        LinearLayout product4 = findViewById(R.id.l4);
        LinearLayout product5 = findViewById(R.id.l5);

        product1.setOnClickListener(v -> {

            Intent intent = new Intent(HomeActivity.this, ProductDetail.class);

            intent.putExtra("name", "Dior Sauvage");
            intent.putExtra("price", "$120");
            intent.putExtra("image", R.drawable.perfume1);
            intent.putExtra("description",
                    "Dior Sauvage là dòng nước hoa nam mạnh mẽ và nam tính.");

            startActivity(intent);
        });

        product2.setOnClickListener(v -> {

            Intent intent = new Intent(HomeActivity.this, ProductDetail.class);

            intent.putExtra("name", "Versace");
            intent.putExtra("price", "$135");
            intent.putExtra("image", R.drawable.perfume2);
            intent.putExtra("description",
                    "Versace Eros mang phong cách quyến rũ và tràn đầy năng lượng. Hương bạc hà tươi mát kết hợp với táo xanh và vanilla ngọt nhẹ tạo nên mùi hương nổi bật, thích hợp cho những buổi tiệc và hẹn hò.");

            startActivity(intent);
        });

        product3.setOnClickListener(v -> {

            Intent intent = new Intent(HomeActivity.this, ProductDetail.class);

            intent.putExtra("name", "YSL Y Eau de Parfum");
            intent.putExtra("price", "$210");
            intent.putExtra("image", R.drawable.perfume3);
            intent.putExtra("description",
                    "YSL Y Eau de Parfum là biểu tượng của người đàn ông hiện đại, tự tin và đầy tham vọng. Hương táo xanh, gừng và gỗ tuyết tùng tạo nên sự cân bằng hoàn hảo giữa tươi mát và ấm áp.");

            startActivity(intent);
        });


        product4.setOnClickListener(v -> {

            Intent intent = new Intent(HomeActivity.this, ProductDetail.class);

            intent.putExtra("name", "Bleu de Chanel");
            intent.putExtra("price", "$325");
            intent.putExtra("image", R.drawable.perfume4);
            intent.putExtra("description",
                    "Bleu de Chanel mang phong cách thanh lịch và tinh tế. Sự kết hợp giữa cam chanh tươi mát, gừng cay nhẹ và gỗ đàn hương tạo nên mùi hương sang trọng, phù hợp cho cả công việc và những dịp đặc biệt.");

            startActivity(intent);
        });


        product5.setOnClickListener(v -> {

            Intent intent = new Intent(HomeActivity.this, ProductDetail.class);

            intent.putExtra("name", "Tom Ford Oud Wood");
            intent.putExtra("price", "$145");
            intent.putExtra("image", R.drawable.perfume5);
            intent.putExtra("description",
                    "Tom Ford Oud Wood là dòng nước hoa cao cấp với mùi hương trầm ấm và bí ẩn. Hương gỗ oud quý hiếm kết hợp với gỗ đàn hương và vanilla tạo nên cảm giác sang trọng và đẳng cấp.");

            startActivity(intent);
        });


        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        bottomNav.setOnItemSelectedListener(item -> {

            Fragment fragment = null;

            if(item.getItemId() == R.id.home){
                fragment = new HomeFragment();
            }

            if(item.getItemId() == R.id.favorite){
                fragment = new FavoriteFragment();
            }

            if(item.getItemId() == R.id.cart){
                fragment = new CartFragment();
            }

            if(item.getItemId() == R.id.person){
                fragment = new ProfileFragment();
            }

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_container, fragment)
                    .commit();

            return true;
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



