package com.example.luxuryperfume;

import java.util.ArrayList;
import java.util.List;

public class FavoriteManager {
    private static List<Product> favoriteList = new ArrayList<>();
    private static  List<Product> getFavoriteList(){
        return favoriteList;
    }

    public static void addToFavorite(Product product) {
        if(!favoriteList.contains(product)){
            product.setFavorite(true);
            favoriteList.add(product);
        }
    }
    public static void removeFromFavorite(Product product) {
        product.setFavorite(false);
        favoriteList.remove(product);
    }


}
