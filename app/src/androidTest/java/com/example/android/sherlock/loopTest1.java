package com.example.android.sherlock;
import com.example.android.sherlock.database.Database;
import org.junit.Test;
import static android.support.test.InstrumentationRegistry.getTargetContext;
import static junit.framework.Assert.assertEquals;
/**
 * Jaskaran Buttar.
 */
public class loopTest1 {
    @Test
    public void loop_test1() {
        Database db = new Database(getTargetContext());
        int total = db.getItemsCount();
        assertEquals(925, total);
    }
}
