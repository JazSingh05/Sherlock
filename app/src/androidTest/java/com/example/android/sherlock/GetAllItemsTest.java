package com.example.android.sherlock;

import android.content.Context;
import com.example.android.sherlock.activity.Settings;
import com.example.android.sherlock.database.Database;
import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.model.Store;

import org.junit.Test;
import java.util.Map;
import java.util.Set;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertEquals;

/**
 * Created by ${Abbinav} on 3/12/2018.
 */

public class GetAllItemsTest {
    @Test
    public void TestGetAllItems1() {
            Database db;
            db = new Database(getTargetContext());
            boolean h = db.hasData();
            assertEquals(true, h);
    }
    @Test
    public void TestGetAllItems2() {
        Database db;
        db = new Database(getTargetContext());
        Map<Long, Store> map  = db.getStoresAsMapById();
        assertEquals(true, map != null);
    }
}
