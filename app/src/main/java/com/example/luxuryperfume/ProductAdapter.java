package com.example.luxuryperfume;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product> productList;

    public ProductAdapter(List<Product> productList) {
        this.productList = productList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);

        holder.txtName.setText(product.getName());
        holder.txtPrice.setText(product.getPrice());
        holder.imgProduct.setImageResource(product.getImage());

        // Cập nhật icon trái tim dựa trên FavoriteManager
        if (FavoriteManager.isFavorite(product)) {
            holder.btnFavoriteItem.setImageResource(R.drawable.ic_favoritedam);
        } else {
            holder.btnFavoriteItem.setImageResource(R.drawable.ic_favorite);
        }

        // Click trái tim ở màn hình danh sách
        holder.btnFavoriteItem.setOnClickListener(v -> {
            FavoriteManager.toggleFavorite(product);
            notifyItemChanged(position);
        });

        // Click vào item để xem chi tiết
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ProductDetail.class);
            // Gửi toàn bộ đối tượng Product
            intent.putExtra("product_obj", product);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnFavoriteItem;
        TextView txtName, txtPrice;
        ImageView imgProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Lưu ý: Đảm bảo ID này khớp với file item_product.xml của bạn
            btnFavoriteItem = itemView.findViewById(R.id.btnFavoriteItem);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
}
