package com.example.android.sherlock;

import com.example.android.sherlock.database.Database;
import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.model.Store;

import org.junit.Test;

import java.util.List;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertEquals;

/**
 * Created by ${Jaskaran}.
 */
public class loopTest5  {


    @Test
    public void loop_test5() {

        Database db = new Database(getTargetContext());
        List banana = db.searchItems("banana");
        Item b = new Item("Banana", "Tasty yellow fruit that monkeys like", 1.59, 1, "https://i.imgur.com/WWxI0Pq.jpg");
        assertEquals(true,b.equals(banana.get(0)));
    }
}