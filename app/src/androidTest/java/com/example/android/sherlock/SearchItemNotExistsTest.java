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
 * Created by ${Abbinav} on 3/12/2018.
 */

public class SearchItemNotExistsTest {
    @Test
    public void TestSearchItems1() {
        Database db;
        db = new Database(getTargetContext());
        String OLIVES = "Olives";
        Item olives = new Item(OLIVES, "Gross little black items", 2.99, 1, "https://i.imgur.com/bb1c1Iq.jpg");
        List<Item> list = db.searchItems(OLIVES);
        Log.i("TESTFUNCTION1", String.valueOf(list));
        assertEquals(true,list.isEmpty());
    }
    @Test
    public void TestSearchItems2() {
        Database db;
        db = new Database(getTargetContext());
        String HP = "HP";
        Item hp = new Item(HP, "Kyle's Dad", 4555.59, 1, "https://i.imgur.com/WWxI0Pq.jpg" );
        List<Item> list = db.searchItems(HP);
        Log.i("TESTFUNCTION1", String.valueOf(list));
        assertEquals(true, list.isEmpty());
    }
}
