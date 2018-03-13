package com.example.android.sherlock;
import com.example.android.sherlock.database.Database;
import org.junit.Test;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertEquals;
/**
 * Jaskaran Buttar.
 */
public class loopTest2 {
    @Test
    public void loop_test2() {
        Database db = new Database(getTargetContext());
        int total = db.storeCount();
        assertEquals(12, total);
    }
}
