package com.example.android.sherlock;

import android.graphics.Bitmap;

/**
 * Created by Abbinav,Jaskaran on 1/28/2018.
 */

public class Store {

    private int id;
    private String storeName;
    private String address;
    private String productName;
    private Bitmap productPicture;
    private Double price;

    public Store(String s, String ralphs, String s1, String banana, String s3) {
    }

    public Store() {

    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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


