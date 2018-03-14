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

public class IntegrationTest4 {
    @Test
    public void TestSearch() {
        Database db;
        db = new Database(getTargetContext());
        int storeId = 4;
        String LMD = "Lincoln Market & Deli";
        Store costco = new Store(LMD, "496 Broad St, San Luis Obispo, CA 93405");
        List<Store> list1 = db.searchStores(LMD);
        String DRINK = "Monster Energy Drink";
        Item lemon = new Item(DRINK, "Pure caffeine to unleash the beast", 1.99, list1.get(0).getId(), "https://i.imgur.com/Cq0LLzQ.jpg");
        List<Item> list = db.searchItems(DRINK);
        System.out.println(list);
        assertEquals(true, list.get(0).getStoreId() == list1.get(0).getId());
    }
}
