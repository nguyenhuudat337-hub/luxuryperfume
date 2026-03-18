package com.example.luxuryperfume;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    List<Product> productList;

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


        if (product.isFavorite()) {
            holder.btnFavoriteItem.setImageResource(R.drawable.ic_favoritedam); // Trái tim đậm
        } else {
            holder.btnFavoriteItem.setImageResource(R.drawable.ic_favorite); // Trái tim rỗng
        }


        holder.btnFavoriteItem.setOnClickListener(v -> {
            if (product.isFavorite()) {
                FavoriteManager.removeFromFavorite(product);
            } else {
                FavoriteManager.addToFavorite(product);
            }
            // Cập nhật lại giao diện của đúng item đó để đổi màu trái tim
            notifyItemChanged(position);
        });

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(v.getContext(), ProductDetail.class);

            intent.putExtra("name", product.getName());
            intent.putExtra("price", product.getPrice());
            intent.putExtra("image", product.getImage());
            intent.putExtra("description", product.getDescription());
            intent.putExtra("isFavorite", product.isFavorite());

            v.getContext().startActivity(intent);

        });






    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton btnFavoriteItem;
        TextView txtName, txtPrice;
        ImageView imgProduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnFavoriteItem = itemView.findViewById(R.id.btnFavoriteItem);
            txtName = itemView.findViewById(R.id.txtName);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }
    }
}
