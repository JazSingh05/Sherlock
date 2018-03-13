package com.example.android.sherlock;

/**
 * Jaskaran Buttar.
 */
import com.example.android.sherlock.database.Database;

import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertEquals;

public class loopTest1 {
    @Test
    public void loop_test1() {

        Database db = new Database(getTargetContext());
        int total = db.getItemsCount();
        assertEquals(183, total);
    }
}
