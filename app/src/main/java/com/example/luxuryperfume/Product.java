package com.example.luxuryperfume;

public class Product {
    private String name;
    private String price;
    private int image;

    private String description;

    private boolean isFavorite ;




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
}
