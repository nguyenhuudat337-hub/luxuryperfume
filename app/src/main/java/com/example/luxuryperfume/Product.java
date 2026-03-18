package com.example.luxuryperfume;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String price;
    private int image;
    private String description;
    private boolean isFavorite;

    public Product(String name, String price, int image,String description) {
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.isFavorite = false;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getImage() {
        return image;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name != null ? name.equals(product.name) : product.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
