package com.example.android.sherlock;

import android.util.Log;

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

public class IntegrationTest1 {
    @Test
    public void TestSearch() {
        Database db;
        db = new Database(getTargetContext());
        String BANANA = "Banana";
        int storeId = 1;
        String COSTCO = "Costco";
        Store costco = new Store(COSTCO, "1111 LOVR, San Luis Obispo, 93401");
        List<Store> list1 = db.searchStores(COSTCO);
        Item banana = new Item(BANANA, "Tasty yellow fruit that monkeys like", 1.59, list1.get(0).getId(), "https://i.imgur.com/WWxI0Pq.jpg");
        List<Item> list = db.searchItems(BANANA);
        assertEquals(true, list.get(0).getStoreId() == list1.get(0).getId());
    }
}
