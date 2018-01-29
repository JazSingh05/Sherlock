package com.example.android.sherlock;

import android.graphics.Bitmap;

/**
 * Created by Abbinav,Jaskaran on 1/28/2018.
 */

public class Store {

    private int id;
    private String storeName;
    private int productId;
    private String productName;
    private Bitmap productPicture;
    private Double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Bitmap getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(Bitmap productPicture) {
        this.productPicture = productPicture;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}


