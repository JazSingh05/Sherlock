package com.example.android.sherlock;

import com.example.android.sherlock.model.Store;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ${Abbinav} on 3/7/2018.
 */
public class StoreTestAdress {
    @Test
    public void TestStoreAddress1() {
        Store mystore = new Store();
        mystore.setAddress("1111 LOVR, San Luis Obispo, 93401");
        assertEquals("1111 LOVR, San Luis Obispo, 93401", mystore.getAddress());
    }
    @Test
    public void TestStoreAddress2(){
        Store mystore = new Store();
        mystore.setAddress("123 Fake St, San Luis Obispo, 93401");
        assertEquals("123 Fake St, San Luis Obispo, 93401", mystore.getAddress());
    }
}