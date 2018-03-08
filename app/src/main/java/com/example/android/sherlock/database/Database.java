package com.example.android.sherlock.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.sherlock.model.Item;
import com.example.android.sherlock.model.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Abbinav, Jaskaran Buttar on 1/28/2018. Rebuilt by Stephen on 2/28/2018
 */

public class Database extends SQLiteOpenHelper {
    private static final String TAG = "Database";

    private static final String VARCHAR = ",%s VARCHAR";
    private static final String INTEGER = ",%s INTEGER";
    private static final String DOUBLE = ",%s DOUBLE";

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "sherlock";
    private static final String TABLE_ITEMS = "item";
    private static final String TABLE_STORES = "store";

    //store table
    private static final String ID = "id";
    private static final String NAME = "storeName";
    private static final String ADDRESS = "address";
    private static final String[] S_PROJ = {ID, NAME, ADDRESS};

    //product table
    private static final String PRODUCT_NAME = "productName";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String STORE_ID = "storeId";
    private static final String IMAGE_URL = "pictureUrl";
    private static final String[] I_PROJ = {ID, PRODUCT_NAME, DESCRIPTION, PRICE, STORE_ID, IMAGE_URL};

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String CREATE_STORE_TABLE = String.format(Locale.US, "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR, %s VARCHAR)", TABLE_STORES, ID, NAME, ADDRESS);

        String CREATE_PRODUCT_TABLE = String.format(Locale.US, "CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT" +
                VARCHAR +
                VARCHAR +
                DOUBLE+
                INTEGER +
                VARCHAR +")", TABLE_ITEMS, ID, PRODUCT_NAME, DESCRIPTION, PRICE, STORE_ID, IMAGE_URL);

        Log.d(TAG, "executing query: " + CREATE_STORE_TABLE);
        sqLiteDatabase.execSQL(CREATE_STORE_TABLE);
        Log.d(TAG, "executing query: " + CREATE_PRODUCT_TABLE);
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STORES);
        onCreate(sqLiteDatabase);
    }

    public void addTestData() {
        Log.d("DATABASE", "adding test data to the database");
        //TODO: here you guys should make a bunch of stores and items and add them to the db using the addStore and addItem methods
        //example below
        Store costco = new Store("Costco", "1111 LOVR, San Luis Obispo, 93401");
        Store ralphs = new Store("Ralphs","201 Madonna Rd, San Luis Obispo, CA 93405");
        Store cali_fresh = new Store("California Fresh Market SLO", "771 Foothill BLVD, San Luis Obispo, CA 93405");
        Store lincoln_market_and_deli = new Store("Lincoln Market & Deli", "496 Broad St, San Luis Obispo, CA 93405");
        Store san_luis_oriental_market = new Store("San Luis Oriental Market", "1255 Monterey St # C, San Luis Obispo, CA 93401");
        Store vons = new Store("Vons","1255 Monterey St # C, San Luis Obispo, CA 93401");
        Store smart_and_final = new Store("Smart & Final","1255 Monterey St # C, San Luis Obispo, CA 93401");
        Store whole_foods = new Store("Whole Foods Market","1255 Monterey St # C, San Luis Obispo, CA 93401");
        Store trader_joes = new Store("Trader Joe's","1255 Monterey St # C, San Luis Obispo, CA 93401");
        Store food_4_less = new Store("Food 4 Less","1255 Monterey St # C, San Luis Obispo, CA 93401");
        //All the store IDs for our database
        long costco_id =  addStore(costco); //call to add store returns the id of the newly added item, but as a long
        long ralphs_id = addStore(ralphs);
        long cf_id = addStore(cali_fresh);
        long lmd_id = addStore(lincoln_market_and_deli);
        long slom_id = addStore(san_luis_oriental_market);
        long vons_id = addStore(vons);
        long sfid = addStore(smart_and_final);
        long wf_id = addStore(whole_foods);
        long tjs_id = addStore(trader_joes);
        long f4l_id = addStore(food_4_less);
        //adding items to the database using the store IDs
        //stephen
        Item banana = new Item("Banana", "Tasty yellow fruit that monkeys like", 1.59, costco_id, "https://i.imgur.com/WWxI0Pq.jpg");
        addItem(banana); //1
        Item lemon = new Item("Lemon", "Sour yellow fruit used for beverages", 0.79, lmd_id, "https://i.imgur.com/sk2Q0Se.jpg");
        addItem(lemon); //2
        Item yogurt = new Item("Greek Yogurt", "Probiotic dairy snack, can be sour", 4.89, tjs_id,"https://i.imgur.com/FXyZH3P.jpg");
        addItem(yogurt); //3
        Item bacon = new Item("Bacon", "Delicious salty pork breakfast food", 6.85, ralphs_id, "https://i.imgur.com/k40L3Yj.jpg");
        addItem(bacon); //4
        Item wings = new Item("Chicken Wings", "Zesty and spicy chicken snack, great for games", 10.75, cf_id, "https://i.imgur.com/yrYepED.png");
        addItem(wings); //5
        Item tofu = new Item("Tofu", "Not so healthy protein alternative, messes with hormonal levels", 4.20, slom_id, "https://i.imgur.com/rAnJrmc.jpg");
        addItem(tofu); //6
        Item steak = new Item("Steak", "Nutritious protein source, delicious meat", 14.88, wf_id, "https://i.imgur.com/zUpzG0K.png");
        addItem(steak); //7
        Item broccoli = new Item("Broccoli", "Green vegetable rich in vitamins and minerals", 2.65, vons_id, "https://i.imgur.com/g4SOZjO.png");
        addItem(broccoli); //8
        Item garlic = new Item("Garlic", "Pungent vegetable used for flavoring foods", .99, f4l_id, "https://i.imgur.com/TbPx5Iq.jpg");
        addItem(garlic); //9
        Item beer = new Item("Beer - Barrelhouse IPA", "Flagship IPA from barrelhouse brewery, citrusy", 5.99, sfid, "https://i.imgur.com/7pHUBBt.jpg");
        addItem(beer); //10


        //TODO: jaz add 10 products, 1 per store, comment your name above so we know whos is whos
        //TODO: abbinav add 10 products, 1 per store, comment your name above so we know whos is whos
        //TODO: kyle add 10 products, 1 per store, comment your name above so we know whos is whos
        //TODO: mitchell add 10 products, 1 per store, comment your name above so we know whos is whos
        //TODO: jordan add 10 products, 1 per store, comment your name above so we know whos is whos

    }

    public long addStore(Store store){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, store.getStoreName());
        cv.put(ADDRESS, store.getAddress());
        long result = db.insert(TABLE_STORES, null, cv);
        Log.d(TAG, String.format("inserting store data: Added %s to the db", store.toString()));
        db.close();
        return result;
    }

    public long addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_NAME, item.getName());
        cv.put(DESCRIPTION, item.getDescription());
        cv.put(PRICE, item.getPrice());
        cv.put(STORE_ID, item.getStoreId());
        cv.put(IMAGE_URL, item.getImageUrl());
        long result = db.insert(TABLE_ITEMS, null, cv);
        Log.d(TAG, String.format("inserting item data: Added %s to db", item.toString()));
        db.close();
        return result;
    }

    public boolean hasData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor scursor = db.query(TABLE_STORES, S_PROJ, null, null, null, null, null);
        Cursor icursor = db.query(TABLE_ITEMS, I_PROJ, null, null, null, null, null);
        int count = scursor.getCount() + icursor.getCount();
        scursor.close();
        icursor.close();
        return count > 0;
    }

    public Map<String, Store> getStoresAsMapByName() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STORES, S_PROJ, null, null, null, null, NAME);
        HashMap<String, Store> stores = new HashMap<>(cursor.getCount());
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String name = cursor.getString(1);
            stores.put(name, new Store(cursor.getLong(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return stores;
    }

    public Map<Long, Store> getStoresAsMapById() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STORES, S_PROJ, null, null, null, null, NAME);
        HashMap<Long, Store> stores = new HashMap<>(cursor.getCount());
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Long id = cursor.getLong(0);
            stores.put(id, new Store(cursor.getLong(0), cursor.getString(1), cursor.getString(2)));
            cursor.moveToNext();
        }
        cursor.close();
        return stores;
    }

    public Map<String, Item> getItemsAsMapByName() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEMS, I_PROJ, null, null, null, null, PRODUCT_NAME);
        HashMap<String, Item> items = new HashMap<>(cursor.getCount());
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            String name = cursor.getString(1);
            items.put(name, new Item(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3),
                    cursor.getInt(4), cursor.getString(5)));
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }
    public Map<Long, Item> getItemsAsMapById() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEMS, I_PROJ, null, null, null, null, PRODUCT_NAME);
        HashMap<Long, Item> items = new HashMap<>(cursor.getCount());
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            Long id = cursor.getLong(0);
            items.put(id, new Item(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3),
                    cursor.getInt(4), cursor.getString(5)));
            cursor.moveToNext();
        }
        cursor.close();
        return items;
    }

    public List<Store> searchStores(String term) {
        String sTerm = term.toLowerCase();
        ArrayList<Store> stores = new ArrayList<>();
        Map<String, Store> storesMap = getStoresAsMapByName();
        for(String key: storesMap.keySet()){
            String lkey = key.toLowerCase();
            if(lkey.contains(sTerm) || sTerm.contains(key)){
                stores.add(storesMap.get(key));
            }
        }
        return stores;
    }

    public List<Item> searchItems(String term) {
        String sTerm = term.toLowerCase();
        ArrayList<Item> items = new ArrayList<>();
        Map<String, Item> itemMap = getItemsAsMapByName();
        for(String key: itemMap.keySet()){
            String lkey = key.toLowerCase();
            String ldesc = itemMap.get(key).getDescription().toLowerCase();
            if(lkey.contains(sTerm) || sTerm.contains(key) || ldesc.contains(sTerm)){
                items.add(itemMap.get(key));
            }
        }
        return items;
    }
}
