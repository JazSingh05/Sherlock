package com.example.android.sherlock;

import android.util.Log;

import com.example.android.sherlock.database.Database;

import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertEquals;
import com.example.android.sherlock.model.Item;

import java.util.List;

/**
 * Created by ${Abbinav} on 3/12/2018.
 */

public class DatabaseTest {
    @Test
    public void TestSearchitems() {
        Database db;
        db = new Database(getTargetContext());
        String BANANA = "Banana";
        Item banana = new Item(BANANA, "Tasty yellow fruit that monkeys like", 1.59, 1, "https://i.imgur.com/WWxI0Pq.jpg" );


        List<Item> list = db.searchItems(BANANA);
        Log.i("TESTFUNCTION1", String.valueOf(list));

        assertEquals(true, list.contains(banana));


    }

}
