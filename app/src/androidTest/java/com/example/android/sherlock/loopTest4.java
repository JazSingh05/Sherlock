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
public class loopTest4  {
    @Test
    public void loop_test4() {
        Database db = new Database(getTargetContext());
        List s = db.searchStores("costco");
        Store costco = new Store("Costco", "1111 LOVR, San Luis Obispo, 93401");
        assertEquals(true, costco.equals(s.get(0)));
    }
}