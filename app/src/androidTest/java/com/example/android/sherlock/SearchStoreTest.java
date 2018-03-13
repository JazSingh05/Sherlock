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

public class SearchStoreTest {

    @Test
    public void TestSearchstore() {
        Database db;
        db = new Database(getTargetContext());
        String COSTCO = "Costco";
        Store costco = new Store(COSTCO, "1111 LOVR, San Luis Obispo, 93401");
        List<Store> list = db.searchStores(COSTCO);
        Log.i("TESTFUNCTION1", String.valueOf(list));
        assertEquals(true, list.get(0).equals(costco));
    }
    @Test
    public void TestSearchstore1() {
        Database db;
        db = new Database(getTargetContext());
        String RALPHS = "Ralphs";
        Store ralphs = new Store(RALPHS,"201 Madonna Rd, San Luis Obispo, CA 93405");
        List<Store> list = db.searchStores(RALPHS);
        Log.i("TESTFUNCTION1", String.valueOf(list));
        assertEquals(true,list.get(0).equals(ralphs));
    }
}
