package com.example.luxuryperfume;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProductDetail extends AppCompatActivity {
    ImageView imgProduct;
    TextView txtName, txtPrice, txtDescription;
    RatingBar ratingBar;
    ImageButton btnFavoriteDetail, btnAddToCart;

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
        btnFavoriteDetail = findViewById(R.id.imageButton2);
        btnAddToCart = findViewById(R.id.imageButton);

        // Nhận đối tượng Product từ Intent
        Product product = (Product) getIntent().getSerializableExtra("product_obj");

        if (product != null) {
            txtName.setText(product.getName());
            txtPrice.setText(product.getPrice());
            txtDescription.setText(product.getDescription());
            imgProduct.setImageResource(product.getImage());
            
            // Cập nhật trạng thái icon ban đầu
            updateFavoriteIcon(product);
            updateCartIcon(product);
        }

        ratingBar.setRating(4.5f);

        findViewById(R.id.Button3).setOnClickListener(v -> finish());

        // Xử lý click nút yêu thích trong màn hình chi tiết
        btnFavoriteDetail.setOnClickListener(v -> {
            if (product != null) {
                FavoriteManager.toggleFavorite(product);
                updateFavoriteIcon(product);
            }
        });

        // Xử lý click nút thêm vào giỏ hàng
        btnAddToCart.setOnClickListener(v -> {
            if (product != null) {
                boolean wasInCart = CartManager.isInCart(product);
                CartManager.toggleCart(product);
                updateCartIcon(product);
            }
        });
    }

    private void updateFavoriteIcon(Product product) {
        if (FavoriteManager.isFavorite(product)) {
            btnFavoriteDetail.setImageResource(R.drawable.ic_favoritedam);
        } else {
            btnFavoriteDetail.setImageResource(R.drawable.ic_favorite);
        }
    }

    private void updateCartIcon(Product product) {
        if (CartManager.isInCart(product)) {
            // Giả sử bạn có icon ic_cart_filled hoặc tương tự để biểu thị đã trong giỏ
            // Nếu không có, tôi sẽ dùng ic_cart bình thường nhưng logic vẫn thêm vào CartManager
            btnAddToCart.setImageResource(R.drawable.ic_cartdam);
        } else {
            btnAddToCart.setImageResource(R.drawable.ic_cart);
        }
    }
}
