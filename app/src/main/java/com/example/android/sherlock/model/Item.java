package com.example.android.sherlock.model;

import java.util.Locale;

/**
 * Created by stephen on 2/28/18.
 */

public class Item {
    private long id;
    private String name;
    private String description;
    private Double price;
    private long storeId;
    private String imageUrl;

    public Item(long id, String name, String description, Double price, long storeId, String imageUrl) {
        this(name, description, price, storeId, imageUrl);
        this.id=id;
    }

    public Item(String name, String description, Double price, long storeId, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.storeId = storeId;
        this.imageUrl = imageUrl;
    }

    public Item(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Item %d:| %s | %s | $%f | %d | %s |", id, name, description, price, storeId, imageUrl);
    }
}
