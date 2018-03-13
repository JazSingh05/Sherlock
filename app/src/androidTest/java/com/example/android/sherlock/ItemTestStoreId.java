package com.example.android.sherlock;

import com.example.android.sherlock.model.Item;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ${Abbinav} on 3/12/2018.
 */

public class ItemTestStoreId {
    @Test
    public void TestItemStoreId1() {
        Item myItem = new Item();
        myItem.setStoreId(1);
        assertEquals(1, myItem.getStoreId());
    }
    @Test
    public void TestItemStoreId2() {
        Item myItem = new Item();
        myItem.setStoreId(2);
        assertEquals(2, myItem.getStoreId());
    }
}
