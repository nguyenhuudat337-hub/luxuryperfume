package com.example.luxuryperfume;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.recyclerProducts1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateCartList();
    }

    private void updateCartList() {
        List<Product> cartList = CartManager.getCartList();
        adapter = new ProductAdapter(cartList, new ProductAdapter.OnFavoriteChangeListener() {
            @Override
            public void onFavoriteChanged() {
                // Có thể cập nhật lại UI nếu cần khi trạng thái favorite thay đổi trong giỏ hàng
            }
        });
        recyclerView.setAdapter(adapter);
    }
}
