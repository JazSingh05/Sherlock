package com.example.android.sherlock;



import com.example.android.sherlock.model.Store;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
/**

 /**
 * Created by ${Abbinav} on 3/5/2018.
 */

public class StoreTestName {

    @Test
    public void TestStoreName1() {

        Store mystore = new Store();

        mystore.setStoreName("Ralphs");
        assertEquals("Ralphs", mystore.getStoreName());



    }
    @Test
    public void TestStoreName2(){
        Store myStore = new Store();
        myStore.setStoreName("Costco");
        assertEquals("Costco", myStore.getStoreName());
    }
}
