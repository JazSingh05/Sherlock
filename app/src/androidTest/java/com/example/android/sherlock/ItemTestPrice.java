package com.example.android.sherlock;

import com.example.android.sherlock.model.Item;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ${Abbinav} on 3/7/2018.
 */

public class ItemTestPrice {

    @Test
    public void TestItemPrice1() {


        Item myItem = new Item();

        myItem.setPrice(1.99);
        assertEquals((double)1.99, myItem.getPrice());



    }
    @Test
    public void TestItemPrice2() {


        Item myItem = new Item();

        myItem.setPrice(2.99);
        assertEquals((double)2.99, myItem.getPrice());



    }

}
