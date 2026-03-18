package com.example.luxuryperfume;

import java.util.ArrayList;
import java.util.List;

public class FavoriteManager {
    private static List<Product> favoriteList = new ArrayList<>();

    public static void toggleFavorite(Product product) {
        if (isFavorite(product)) {
            removeFavorite(product);
        } else {
            addFavorite(product);
        }
    }

    public static boolean isFavorite(Product product) {
        for (Product p : favoriteList) {
            if (p.getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }

    private static void addFavorite(Product product) {
        if (!isFavorite(product)) {
            product.setFavorite(true);
            favoriteList.add(product);
        }
    }

    private static void removeFavorite(Product product) {
        favoriteList.removeIf(p -> p.getName().equals(product.getName()));
        product.setFavorite(false);
    }

    public static List<Product> getFavoriteList() {
        return favoriteList;
    }
}
