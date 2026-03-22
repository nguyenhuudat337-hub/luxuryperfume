package com.example.luxuryperfume;

import android.content.Context;import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<Product> cartList;
    private Context context;
    private OnCartChangeListener onChangeListener;

    // Interface để thông báo cho Fragment/Activity cập nhật lại tổng tiền khi xóa hoặc đổi số lượng
    public interface OnCartChangeListener {
        void onQuantityChanged();
    }

    public CartAdapter(List<Product> cartList, Context context, OnCartChangeListener listener) {
        this.cartList = cartList;
        this.context = context;
        this.onChangeListener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Sử dụng một layout riêng cho item giỏ hàng (ví dụ: item_cart.xml)
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartList.get(position);

        // Hiển thị dữ liệu
        holder.imgProduct.setImageResource(product.getImage());
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText(product.getPrice());
        holder.tvQuantity.setText(String.valueOf(product.getQuantity()));

        holder.btnPlus.setOnClickListener(v -> {
            int newQty = product.getQuantity() + 1;
            product.setQuantity(newQty); // Cập nhật vào đối tượng product
            holder.tvQuantity.setText(String.valueOf(newQty)); // Cập nhật giao diện tại chỗ

            // Thông báo cho Fragment biết để tính lại tổng tiền
            if (onChangeListener != null) {
                onChangeListener.onQuantityChanged();
            }
        });



        holder.btnMinus.setOnClickListener(v -> {
            int currentQty = product.getQuantity();
            if (currentQty > 1) { // Chỉ giảm nếu số lượng > 1
                int newQty = currentQty - 1;
                product.setQuantity(newQty);
                holder.tvQuantity.setText(String.valueOf(newQty));

                if (onChangeListener != null) {
                    onChangeListener.onQuantityChanged();
                }
            } else {
                // Nếu số lượng bằng 1, xóa sản phẩm khỏi giỏ hàng
                int adapterPosition = holder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    CartManager.removeFromCart(product);
                    notifyItemRemoved(adapterPosition);
                    notifyItemRangeChanged(adapterPosition, getItemCount());

                    if (onChangeListener != null) {
                        onChangeListener.onQuantityChanged();
                    }
                }
            }
        });

        holder.cbSelectedItem.setOnCheckedChangeListener(null); // Reset listener để tránh lỗi khi scroll
        holder.cbSelectedItem.setChecked(product.isSelected());

        holder.cbSelectedItem.setOnCheckedChangeListener((buttonView, isChecked) -> {
            product.setSelected(isChecked); // Cập nhật trạng thái vào model
            if (onChangeListener != null) {
                onChangeListener.onQuantityChanged(); // Tận dụng hàm này để tính lại tiền và check trạng thái "All"
            }
        });


    }

    @Override
    public int getItemCount() {
        return cartList != null ? cartList.size() : 0;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct, btnDelete;
        TextView tvName, tvPrice,btnPlus,btnMinus,tvQuantity;
        CheckBox cbSelectedItem;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.txtName1);
            tvPrice = itemView.findViewById(R.id.txtPrice1);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            tvQuantity = itemView.findViewById(R.id.txtQuantity);
            cbSelectedItem = itemView.findViewById(R.id.checkBox);
        }
    }
}