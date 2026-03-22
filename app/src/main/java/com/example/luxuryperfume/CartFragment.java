package com.example.luxuryperfume;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager; // Nên dùng LinearLayoutManager cho danh sách dọc
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView; // Nếu bạn có TextView hiển thị tổng tiền

import java.util.Iterator;
import java.util.List;

public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private CartAdapter adapter; // Đổi từ ProductAdapter sang CartAdapter
    private TextView tvTotalPrice; // Giả sử bạn có TextView này trong fragment_cart.xml
    private CheckBox cbSelectAll;

    private TextView btnDeleteSelected;
    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.recyclerProducts1);
        // Thay vì GridLayoutManager(1), dùng LinearLayoutManager sẽ chuẩn cho danh sách giỏ hàng hơn
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Ánh xạ TextView tổng tiền nếu có
        tvTotalPrice = view.findViewById(R.id.textView9);
        btnDeleteSelected = view.findViewById(R.id.btnDelete);
        cbSelectAll = view.findViewById(R.id.checkBoxAll);



        cbSelectAll.setOnClickListener(v -> {
            boolean isChecked = cbSelectAll.isChecked();
            // Duyệt qua toàn bộ danh sách giỏ hàng để set check/uncheck
            for (Product p : CartManager.getCartList()) {
                p.setSelected(isChecked);
            }
            adapter.notifyDataSetChanged(); // Cập nhật lại toàn bộ danh sách hiển thị
            updateTotal(); // Tính lại tiền
        });


        btnDeleteSelected.setOnClickListener(v -> {
            deleteSelectedProducts();
        });
        return view;
    }
    private void deleteSelectedProducts() {
        List<Product> cartList = CartManager.getCartList();

        // Sử dụng Iterator để xoá an toàn khi đang duyệt List (tránh lỗi ConcurrentModificationException)
        Iterator<Product> iterator = cartList.iterator();
        boolean hasDeleted = false;

        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.isSelected()) {
                iterator.remove(); // Xoá sản phẩm nếu đang được tích
                hasDeleted = true;
            }
        }

        if (hasDeleted) {
            adapter.notifyDataSetChanged(); // Cập nhật lại danh sách hiển thị
            updateTotal(); // Tính lại tiền và cập nhật trạng thái checkbox All
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateCartList(); // Cập nhật lại danh sách mỗi khi quay lại màn hình giỏ hàng
    }

    private void updateCartList() {
        List<Product> cartList = CartManager.getCartList();

        // SỬA LỖI TẠI ĐÂY: Thêm getContext() vào tham số thứ 2
        adapter = new CartAdapter(cartList, getContext(), new CartAdapter.OnCartChangeListener() {
            @Override
            public void onQuantityChanged() {
                // Mỗi khi xóa hoặc đổi số lượng, tính lại tổng tiền
                updateTotal();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void updateTotal() {
        double total = 0;
        boolean allSelected = true;
        List<Product> list = CartManager.getCartList();

        if (list.isEmpty()) {
            allSelected = false;
        } else {
            for (Product p : list) {
                if (p.isSelected()) {
                    try {
                        String priceStr = p.getPrice().replaceAll("[^0-9.]", "");
                        double price = Double.parseDouble(priceStr);
                        total += price * p.getQuantity();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    allSelected = false;
                }
            }
        }

        tvTotalPrice.setText(String.format("%.2f $", total));
        cbSelectAll.setChecked(allSelected);
    }
}