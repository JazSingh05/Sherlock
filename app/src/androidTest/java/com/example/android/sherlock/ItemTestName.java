package com.example.android.sherlock;

import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.model.Store;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ${Abbinav} on 3/7/2018.
 */

public class ItemTestName {

    @Test
    public void TestItemName1() {


        Item myItem = new Item();

        myItem.setName("Banana");
        assertEquals("Banana", myItem.getName());



    }

    @Test
    public void TestItemName2() {


        Item myItem = new Item();

        myItem.setName("Lemon");
        assertEquals("Lemon", myItem.getName());



    }
}
