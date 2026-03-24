package com.example.luxuryperfume;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 bannerSlider;
    private LinearLayout layoutIndicator;
    private Handler sliderHandler = new Handler(Looper.getMainLooper());
    private ProductAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bannerSlider = view.findViewById(R.id.bannerSlider);
        layoutIndicator = view.findViewById(R.id.layoutIndicator);

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

        bannerSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        // Categories click listeners
        view.findViewById(R.id.c1).setOnClickListener(v -> startActivity(new Intent(getContext(), CategoriesWomen.class)));
        view.findViewById(R.id.c2).setOnClickListener(v -> startActivity(new Intent(getContext(), CategoriesMan.class)));
        view.findViewById(R.id.c3).setOnClickListener(v -> startActivity(new Intent(getContext(), CategoriesLuxury.class)));
        view.findViewById(R.id.c4).setOnClickListener(v -> startActivity(new Intent(getContext(), CategoriesSale.class)));
        view.findViewById(R.id.c5).setOnClickListener(v -> startActivity(new Intent(getContext(), CategoriesNew.class)));

        // RecyclerView setup
        RecyclerView recyclerView = view.findViewById(R.id.recyclerProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        List<Product> productList = getProducts();
        // Trong HomeFragment.java, đoạn khởi tạo RecyclerView
        adapter = new ProductAdapter(productList, new ProductAdapter.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged() {
                // Khi tim ở RecyclerView thay đổi, ta gọi hàm cập nhật BestSellers
                updateBestSellerIcons();
            }
        });
        recyclerView.setAdapter(adapter);

        // Khởi tạo các sự kiện click cho BestSellers
        setupBestSellers(view);


        view.findViewById(R.id.imageButton6).setOnClickListener(v -> startActivity(new Intent(getContext(), NotificationActivity.class)));
    }

    private List<Product> getProducts() {
        List<Product> product = new ArrayList<>();
        product.add(new Product("Dior Sauvage", "$120", R.drawable.perfume1, "Dior Sauvage là dòng nước hoa nam mang phong cách mạnh mẽ và nam tính..."));
        product.add(new Product("Versace", "$135", R.drawable.perfume2, "Versace Eros mang phong cách quyễ quyến rũ và tràn đầy năng lượng..."));
        product.add(new Product("YSL Y Eau de Parfum", "$210", R.drawable.perfume3, "YSL Y Eau de Parfum là biểu tượng của người đàn ông hiện đại..."));
        product.add(new Product("Bleu de Chanel", "$325", R.drawable.perfume4, "Bleu de Chanel mang phong cách thanh lịch và tinh tế..."));
        product.add(new Product("Tom Ford Oud Wood", "$145", R.drawable.perfume5, "Tom Ford Oud Wood là dòng nước hoa cao cấp với mùi hương trầm ấm..."));
        product.add(new Product("Acqua di Gio Profumo", "$150", R.drawable.perfume6, "Acqua di Gio Profumo mang hơi thở của đại dương..."));
        product.add(new Product("Creed Aventus", "$110", R.drawable.perfume7, "Creed Aventus là một trong những dòng nước hoa nam nổi tiếng..."));
        product.add(new Product("Paco Rabanne 1 Million", "$115", R.drawable.perfume8, "Paco Rabanne 1 Million nổi bật với phong cách táo bạo..."));
        product.add(new Product("Jean Paul Gaultier Le Male", "$125", R.drawable.perfume9, "Jean Paul Gaultier Le Male là dòng nước hoa cổ điển..."));
        product.add(new Product("Maison Francis Kurkdjian Baccarat Rouge 540", "$350", R.drawable.perfume10, "Baccarat Rouge 540 là dòng nước hoa niche cực kỳ nổi tiếng..."));
        product.add(new Product("Parfums de Marly Layton", "$280", R.drawable.perfume11, "Layton của Parfums de Marly mang phong cách quý tộc..."));
        return product;
    }

    private void setupBestSellers(View view) {
        view.findViewById(R.id.l1).setOnClickListener(v -> openDetail("Dior Sauvage", "$120", R.drawable.perfume1, "Dior Sauvage là dòng nước hoa nam mạnh mẽ và nam tính"));
        view.findViewById(R.id.l2).setOnClickListener(v -> openDetail("Versace", "$135", R.drawable.perfume2, "Versace Eros mang phong cách quyến rũ và tràn đầy năng lượng..."));
        view.findViewById(R.id.l3).setOnClickListener(v -> openDetail("YSL Y Eau de Parfum", "$210", R.drawable.perfume3, "YSL Y Eau de Parfum là biểu tượng của người đàn ông hiện đại..."));
        view.findViewById(R.id.l4).setOnClickListener(v -> openDetail("Bleu de Chanel", "$325", R.drawable.perfume4, "Bleu de Chanel mang phong cách thanh lịch và tinh tế..."));
        view.findViewById(R.id.l5).setOnClickListener(v -> openDetail("Tom Ford Oud Wood", "$145", R.drawable.perfume5, "Tom Ford Oud Wood là dòng nước hoa cao cấp với mùi hương trầm ấm..."));

        view.findViewById(R.id.btnFavoriteItem1).setOnClickListener(v -> toggleFavorite("Dior Sauvage", "$120", R.drawable.perfume1));
        view.findViewById(R.id.btnFavoriteItem2).setOnClickListener(v -> toggleFavorite("Versace", "$135", R.drawable.perfume2));
        view.findViewById(R.id.btnFavoriteItem3).setOnClickListener(v -> toggleFavorite("YSL Y Eau de Parfum", "$210", R.drawable.perfume3));
        view.findViewById(R.id.btnFavoriteItem4).setOnClickListener(v -> toggleFavorite("Bleu de Chanel", "$325", R.drawable.perfume4));
        view.findViewById(R.id.btnFavoriteItem5).setOnClickListener(v -> toggleFavorite("Tom Ford Oud Wood", "$145", R.drawable.perfume5));
    }

    private void openDetail(String name, String price, int img, String desc) {
        Intent intent = new Intent(getContext(), ProductDetail.class);
        intent.putExtra("product_obj", new Product(name, price, img, desc));
        startActivity(intent);
    }

    private void toggleFavorite(String name, String price, int img) {
        FavoriteManager.toggleFavorite(new Product(name, price, img, ""));
        updateBestSellerIcons();
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateBestSellerIcons();
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    private void updateBestSellerIcons() {
        View view = getView();
        if (view == null) return;
        updateIcon(view.findViewById(R.id.btnFavoriteItem1), "Dior Sauvage");
        updateIcon(view.findViewById(R.id.btnFavoriteItem2), "Versace");
        updateIcon(view.findViewById(R.id.btnFavoriteItem3), "YSL Y Eau de Parfum");
        updateIcon(view.findViewById(R.id.btnFavoriteItem4), "Bleu de Chanel");
        updateIcon(view.findViewById(R.id.btnFavoriteItem5), "Tom Ford Oud Wood");
    }

    private void updateIcon(ImageButton btn, String productName) {
        if (btn == null) return;
        Product temp = new Product(productName, "", 0, "");
        if (FavoriteManager.isFavorite(temp)) {
            btn.setImageResource(R.drawable.ic_favoritedam);
        } else {
            btn.setImageResource(R.drawable.ic_favorite);
        }
    }

    private void setCurrentIndicator(int position) {
        int childCount = layoutIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageResource(R.drawable.indicator_active);
            } else {
                imageView.setImageResource(R.drawable.indicator_inactive);
            }
        }
    }

    private void setupIndicators(int count) {
        layoutIndicator.removeAllViews();
        ImageView[] indicators = new ImageView[count];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(8, 0, 8, 0);
        for (int i = 0; i < count; i++) {
            indicators[i] = new ImageView(getContext());
            indicators[i].setImageResource(R.drawable.indicator_inactive);
            indicators[i].setLayoutParams(params);
            layoutIndicator.addView(indicators[i]);
        }
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (bannerSlider != null && bannerSlider.getAdapter() != null) {
                int nextItem = bannerSlider.getCurrentItem() + 1;
                if (nextItem >= bannerSlider.getAdapter().getItemCount()) {
                    nextItem = 0;
                }
                bannerSlider.setCurrentItem(nextItem);
            }
        }
    };
}
