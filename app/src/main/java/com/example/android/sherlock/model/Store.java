package com.example.android.sherlock.model;

import java.util.Locale;

/**
 * Created by Abbinav,Jaskaran on 1/28/2018.
 */

public class Store {

    private long id;
    private String storeName;
    private String address;


    public Store(long id, String name, String address) {
        this(name, address);
        this.id = id;
    }

    public Store(String name, String address) {
        this.storeName = name;
        this.address = address;
    }

    public Store() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    @Override
    public String toString() {
        return String.format(Locale.US, "Store %d: %s %s", id, storeName, address);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Store)) return false;
        Store s = (Store) o;
        return s.getStoreName().equals(s.getStoreName()) && s.getAddress().equals(s.getAddress());
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }
}


