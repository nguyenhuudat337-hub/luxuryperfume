package com.example.luxuryperfume;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static List<Product> cartList = new ArrayList<>();

    public static void toggleCart(Product product) {
        if (isInCart(product)) {
            removeFromCart(product);
        } else {
            addToCart(product);
        }
    }

    public static void addToCart(Product product) {
        if (!isInCart(product)) {
            cartList.add(product);
        }
    }

    public static void removeFromCart(Product product) {
        cartList.removeIf(p -> p.getName().equals(product.getName()));
    }

    public static boolean isInCart(Product product) {
        for (Product p : cartList) {
            if (p.getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }

    public static List<Product> getCartList() {
        return cartList;
    }
}
