package com.example.android.sherlock;

import com.example.android.sherlock.database.Database;
import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.model.Store;

import org.junit.Test;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertEquals;

/**
 * Created by ${Abbinav} on 3/13/2018.
 */

public class IntegrationTest2 {
    @Test
    public void TestSearch() {
        Database db;
        db = new Database(getTargetContext());
        int storeId = 4;
        String LMD = "Lincoln Market & Deli";
        Store costco = new Store(LMD, "496 Broad St, San Luis Obispo, CA 93405");
        List<Store> list1 = db.searchStores(LMD);
        String LEMON = "Lemon";
        Item lemon = new Item(LEMON, "Sour yellow fruit used for beverages", 0.79, list1.get(0).getId(), "https://i.imgur.com/sk2Q0Se.jpg" );
        List<Item> list = db.searchItems(LEMON);
        System.out.println(list);
        assertEquals(true, list.get(3).getStoreId() == list1.get(0).getId());
    }
}
