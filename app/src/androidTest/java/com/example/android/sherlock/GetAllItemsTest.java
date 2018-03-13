package com.example.android.sherlock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.android.sherlock.activity.Settings;
import com.example.android.sherlock.database.Database;
import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.model.Store;

import org.junit.Test;
import org.w3c.dom.Text;

import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;

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
