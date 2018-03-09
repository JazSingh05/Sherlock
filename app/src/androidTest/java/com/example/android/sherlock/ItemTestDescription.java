package com.example.android.sherlock;

import com.example.android.sherlock.model.Item;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ${Abbinav} on 3/7/2018.
 */
public class ItemTestDescription {
    @Test
    public void TestItemDescription1() {
        Item myItem = new Item();
        myItem.setDescription("tasty yellow fruit");
        assertEquals("tasty yellow fruit", myItem.getDescription());
    }
    @Test
    public void TestItemDescription2() {
        Item myItem = new Item();
        myItem.setDescription("sour yellow fruit");
        assertEquals("sour yellow fruit", myItem.getDescription());
    }
}