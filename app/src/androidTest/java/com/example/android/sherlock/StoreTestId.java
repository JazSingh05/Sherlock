package com.example.android.sherlock;

import com.example.android.sherlock.model.Store;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by ${Abbinav} on 3/12/2018.
 */

public class StoreTestId {

    @Test
    public void TestStoreId1() {
        Store mystore = new Store();
        mystore.setId(1);
        assertEquals(1, mystore.getId());
    }

    @Test
    public void TestStoreId2() {
        Store mystore = new Store();
        mystore.setId(2);
        assertEquals(2, mystore.getId());
    }

}
