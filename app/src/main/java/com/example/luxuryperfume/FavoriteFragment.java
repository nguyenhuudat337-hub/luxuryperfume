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

// Trong FavoriteFragment.java

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.recyclerProducts);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateFavoriteList();
    }

    private void updateFavoriteList() {
        // Lấy danh sách sản phẩm đã lưu trong FavoriteManager
        List<Product> favoriteList = FavoriteManager.getFavoriteList();
        adapter = new ProductAdapter(favoriteList);
        recyclerView.setAdapter(adapter);
    }
}
