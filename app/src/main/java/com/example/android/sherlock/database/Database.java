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
        Item banana2 = new Item("Banana", "Tropical fruit, soft good for smoothies", 1.29, vons_id, "https://i.imgur.com/WWxI0Pq.jpg");
        Item banana3 = new Item("Banana", "Fruit High in potassium", 1.00, f4l_id, "https://i.imgur.com/WWxI0Pq.jpg");
        addItem(banana); //1.1
        addItem(banana2); //1.2
        addItem(banana3); //1.3
        Item lemon = new Item("Lemon", "Sour yellow fruit used for beverages", 0.79, lmd_id, "https://i.imgur.com/sk2Q0Se.jpg");
        Item lemon2 = new Item("Lemon", "Delicious citrus fruit, tart", 0.60, ralphs_id, "https://i.imgur.com/sk2Q0Se.jpg");
        Item lemon3 = new Item("Lemon", "Zesty yellow citrus fruit", 0.89, cf_id, "https://i.imgur.com/sk2Q0Se.jpg");
        addItem(lemon); //2.1
        addItem(lemon2); //2.2
        addItem(lemon3); //2.3
        Item yogurt = new Item("Greek Yogurt", "Probiotic dairy snack, can be sour", 4.89, tjs_id,"https://i.imgur.com/FXyZH3P.jpg");
        Item yogurt2 = new Item("Greek Yogurt", "Organic dairy yogurt, live cultures", 5.79, wf_id,"https://i.imgur.com/FXyZH3P.jpg");
        Item yogurt3 = new Item("Greek Yogurt", "Probiotic dairy snack, can be sour", 3.99, ralphs_id,"https://i.imgur.com/FXyZH3P.jpg");
        addItem(yogurt); //3.1
        addItem(yogurt2); //3.2
        addItem(yogurt3); //3.3
        Item bacon = new Item("Bacon", "Delicious salty pork breakfast meat", 6.85, ralphs_id, "https://i.imgur.com/k40L3Yj.jpg");
        Item bacon2 = new Item("Bacon", "Meat- applewood smoked bacon", 5.99, vons_id, "https://i.imgur.com/k40L3Yj.jpg");
        Item bacon3 = new Item("Bacon", "Uncured pork belly bacon, thick cut meat", 4.19, f4l_id, "https://i.imgur.com/k40L3Yj.jpg");
        addItem(bacon); //4.1
        addItem(bacon2); //4.2
        addItem(bacon3); //4.3
        Item wings = new Item("Chicken Wings", "Zesty and spicy chicken snack, great for games", 10.75, cf_id, "https://i.imgur.com/yrYepED.png");
        Item wings2 = new Item("Chicken Wings", "Spicy chicen snack, white mean", 10.99, wf_id, "https://i.imgur.com/yrYepED.png");
        Item wings3 = new Item("Chicken Wings", "Cripsy chicken meat snack", 9.75, cf_id, "https://i.imgur.com/yrYepED.png");
        addItem(wings); //5.1
        addItem(wings2); //5.2
        addItem(wings3); //5.3
        Item tofu = new Item("Tofu", "Not so healthy protein alternative, messes with hormonal levels", 4.20, slom_id, "https://i.imgur.com/rAnJrmc.jpg");
        Item tofu2 = new Item("Tofu", "Vegetable basede protein", 3.00, wf_id, "https://i.imgur.com/rAnJrmc.jpg");
        Item tofu3 = new Item("Tofu", "Asian food, high protein, vegetable based", 4.50, lmd_id, "https://i.imgur.com/rAnJrmc.jpg");
        addItem(tofu); //6.1
        addItem(tofu2); //6.2
        addItem(tofu3); //6.3
        Item steak = new Item("Steak", "Nutritious protein source, delicious meat", 14.88, wf_id, "https://i.imgur.com/zUpzG0K.png");
        Item steak2 = new Item("Steak", "USDA ribeye", 15.99, tjs_id, "https://i.imgur.com/zUpzG0K.png");
        Item steak3 = new Item("Steak", "Flank steak, ideal for fajitas", 10.75, f4l_id, "https://i.imgur.com/zUpzG0K.png");
        addItem(steak); //7.1
        addItem(steak2); //7.2
        addItem(steak3); //7.3
        Item broccoli = new Item("Broccoli", "Green vegetable rich in vitamins and minerals", 2.65, vons_id, "https://i.imgur.com/g4SOZjO.png");
        Item broccoli2 = new Item("Broccoli", "Vegetable rich in protein and vitamin c", 1.65, ralphs_id, "https://i.imgur.com/g4SOZjO.png");
        Item broccoli3 = new Item("Broccoli", "Heart healthy green vegetable", 2.89, cf_id, "https://i.imgur.com/g4SOZjO.png");
        addItem(broccoli); //8.1
        addItem(broccoli2); //8.2
        addItem(broccoli3); //8.3
        Item garlic = new Item("Garlic", "Pungent vegetable used for flavoring foods", .99, f4l_id, "https://i.imgur.com/TbPx5Iq.jpg");
        Item garlic2 = new Item("Garlic", "Strong herb used for flavoring dishes", 1.09, wf_id, "https://i.imgur.com/TbPx5Iq.jpg");
        Item garlic3 = new Item("Garlic", "Fresh gilroy garlic", .89, ralphs_id, "https://i.imgur.com/TbPx5Iq.jpg");
        addItem(garlic); //9.1
        addItem(garlic2); //9.2
        addItem(garlic3); //9.3
        Item beer = new Item("Beer - Barrelhouse IPA", "Flagship IPA from barrelhouse brewery, citrusy", 5.99, sfid, "https://i.imgur.com/7pHUBBt.jpg");
        Item beer2 = new Item("Beer - Barrelhouse IPA", "Delicious crisp IPA from local brewery", 3.99, tjs_id, "https://i.imgur.com/7pHUBBt.jpg");
        Item beer3 = new Item("Beer - Barrelhouse IPA", "Strong IPA with lots of hops", 3.49, sfid, "https://i.imgur.com/7pHUBBt.jpg");
        addItem(beer); //10.1
        addItem(beer2); //10.2
        addItem(beer3); //10.3

        //Jordan
        Item chickenBreasts = new Item("Chicken Breasts", "Protein packed poultry", 2.99, ralphs_id, "https://i.imgur.com/bb1c1Iq.jpg");
        Item chickenBreasts2 = new Item("Chicken Breasts", "Tasty and healthy bird meat", 3.15, cf_id, "https://i.imgur.com/bb1c1Iq.jpg");
        Item chickenBreasts3 = new Item("Chicken Breasts", "Inexpensive and healthy protein", 2.99, vons_id, "https://i.imgur.com/bb1c1Iq.jpg");
        addItem(chickenBreasts);
        addItem(chickenBreasts2);
        addItem(chickenBreasts3);
        Item coke12Cans = new Item("Coke 12 cans", "Delicious and refreshing soft drink", 4.99, ralphs_id, "https://i.imgur.com/4D55jln.jpg");
        Item coke12Cans2 = new Item("Coke 12 cans", "Refreshing and popular cola", 4.99, vons_id, "https://i.imgur.com/4D55jln.jpg");
        Item coke12Cans3 = new Item("Coke 12 cans", "Delicious soda beverage", 4.99, sfid, "https://i.imgur.com/4D55jln.jpg");
        addItem(coke12Cans);
        addItem(coke12Cans2);
        addItem(coke12Cans3);
        Item condoms = new Item("Condoms", "36 of the most popular penile-contraceptive product", 27.99, ralphs_id, "https://i.imgur.com/hFPgUX8.jpg");
        Item condoms2 = new Item("Condoms", "Softest and most durable condoms on the market", 27.99, vons_id, "https://i.imgur.com/hFPgUX8.jpg");
        Item condoms3 = new Item("Condoms", "Cozier than socks knitted by your grandma", 31.99, sfid, "https://i.imgur.com/hFPgUX8.jpg");
        addItem(condoms);
        addItem(condoms2);
        addItem(condoms3);
        Item asparagus = new Item("Asparagus", "Delicious and nutritious vegetable", .67, ralphs_id, "https://i.imgur.com/Djqk9Vz.jpg");
        Item asparagus2 = new Item("Asparagus", "Tasty vegetable that is good for you", .69, vons_id, "https://i.imgur.com/Djqk9Vz.jpg");
        Item asparagus3 = new Item("Asparagus", "Green and delicious vegetable", .77, sfid, "https://i.imgur.com/Djqk9Vz.jpg");
        addItem(asparagus);
        addItem(asparagus2);
        addItem(asparagus3);
        Item lettuce = new Item("Lettuce", "Round and nutritious vegetable", 1.69, ralphs_id, "https://i.imgur.com/mtVdQbR.jpg");
        Item lettuce2 = new Item("Lettuce", "Healthy and tasty green vegetable", 1.79, vons_id, "https://i.imgur.com/mtVdQbR.jpg");
        Item lettuce3 = new Item("Lettuce", "Green, round vegetable", 1.99, sfid, "https://i.imgur.com/mtVdQbR.jpg");
        addItem(lettuce);
        addItem(lettuce2);
        addItem(lettuce3);
        Item milkGallon = new Item("Milk Gallon", "1 Gallon of delicious cow's milk", 2.75, ralphs_id, "https://i.imgur.com/XDdGN1P.jpg");
        Item milkGallon2 = new Item("Milk Gallon", "Tasty cow milk", 2.95, vons_id, "https://i.imgur.com/XDdGN1P.jpg");
        Item milkGallon3 = new Item("Milk Gallon", "Delicious milk straight from the cow's teet", 3.05, sfid, "https://i.imgur.com/XDdGN1P.jpg");
        addItem(milkGallon);
        addItem(milkGallon2);
        addItem(milkGallon3);
        Item cashews = new Item("Cashews", "Package of top-quality nuts", 7.99, sfid, "https://i.imgur.com/OhtJLCx.jpg");
        Item cashews2 = new Item("Cashews", "Salty and Tasty Cashews", 6.99, ralphs_id, "https://i.imgur.com/OhtJLCx.jpg");
        Item cashews3 = new Item("Cashews", "High quality nuts", 7.59, vons_id, "https://i.imgur.com/OhtJLCx.jpg");
        addItem(cashews);
        addItem(cashews2);
        addItem(cashews3);
        Item pear = new Item("Pear", "Tasty, green/yellow fruit", .59, ralphs_id, "https://i.imgur.com/gG4xaCe.png");
        Item pear2 = new Item("Pear", "Healthy fruit", .59, sfid, "https://i.imgur.com/gG4xaCe.png");
        Item pear3 = new Item("Pear", "Fruit shaped like an oblong-apple", .59, vons_id, "https://i.imgur.com/gG4xaCe.png");
        addItem(pear);
        addItem(pear2);
        addItem(pear3);
        Item skittles = new Item("Skittles", "Fruit flavored candy", 1.09, ralphs_id, "https://i.imgur.com/tpdhopo.jpg");
        Item skittles2 = new Item("Skittles", "Candy flavored like the rainbow", .99, sfid, "https://i.imgur.com/tpdhopo.jpg");
        Item skittles3 = new Item("Skittles", "Delicious fruity candy", 1.07, vons_id, "https://i.imgur.com/tpdhopo.jpg");
        addItem(skittles);
        addItem(skittles2);
        addItem(skittles3);
        Item starburst = new Item("Starburst", "Bite-sized fruity candy", 1.09, ralphs_id, "https://i.imgur.com/WCB3jbI.jpg");
        Item starburst2 = new Item("Starburst", "Fruit flavored candy", .99, sfid, "https://i.imgur.com/WCB3jbI.jpg");
        Item starburst3 = new Item("Starburst", "Individually packaged candy treats", 1.07, vons_id, "https://i.imgur.com/WCB3jbI.jpg");
        addItem(starburst);
        addItem(starburst2);
        addItem(starburst3);
        Item carrot = new Item("Carrot", "Healthy and nutritious orange vegetable", .60, vons_id, "https://i.imgur.com/JtqRsdU.jpg");
        Item carrot2 = new Item("Carrot", "Nutrition packed vegetable", .65, ralphs_id, "https://i.imgur.com/JtqRsdU.jpg");
        Item carrot3 = new Item("Carrot", "Equally enjoy from farm animals to humans", .77, cf_id, "https://i.imgur.com/JtqRsdU.jpg");
        addItem(carrot);
        addItem(carrot2);
        addItem(carrot3);




        //Abbinav
        Item apple = new Item("Apple", "Round Red and Sweet Fruit", 2.99, ralphs_id, "https://i.imgur.com/B3Y8cVf.jpg");
        Item apple2 = new Item("Apple", "Red,Sweet and Healthy Fruit", 3.49, vons_id, "https://i.imgur.com/B3Y8cVf.jpg");
        Item apple3 = new Item("Apple", "The edible one", 3.99, wf_id, "https://i.imgur.com/B3Y8cVf.jpg");
        addItem(apple);
        addItem(apple2);
        addItem(apple3);
        Item cheetos = new Item("Hot Cheetos", "Crunchy Cheese Snack", 1.99, ralphs_id, "https://i.imgur.com/TUiAMwm.jpg");
        Item cheetos2 = new Item("Hot Cheetos", "Crunchy Spicy Snack", 1.99, sfid, "https://i.imgur.com/TUiAMwm.jpg");
        Item cheetos3 = new Item("Hot Cheetos", "Chips that turn everything Red", 2.99, vons_id, "https://i.imgur.com/TUiAMwm.jpg");
        addItem(cheetos);
        addItem(cheetos2);
        addItem(cheetos3);
        Item sprite = new Item("Sprite Soda", "Lime Flavored Soft Drink", 1.69, sfid, "https://i.imgur.com/lOFpLeO.jpg");
        Item sprite2 = new Item("Sprite Soda", "Caffeine Free Soft Drink", 1.99, tjs_id, "https://i.imgur.com/lOFpLeO.jpg");
        Item sprite3 = new Item("Sprite Soda", "Colorless Lemon Drink", 2.09, f4l_id, "https://i.imgur.com/lOFpLeO.jpg");
        addItem(sprite);
        addItem(sprite2);
        addItem(sprite3);
        Item onion = new Item("Onion", "Used for flavoring foods", 2.99, wf_id, "https://i.imgur.com/lu8nJVP.jpg");
        Item onion2 = new Item("Onion", "Vegetable for adding flavor to dishes", 2.19, cf_id, "https://i.imgur.com/lu8nJVP.jpg");
        Item onion3 = new Item("Onion", "Sweet Yellow Onion", 2.99, vons_id, "https://i.imgur.com/lu8nJVP.jpg");
        addItem(onion);
        addItem(onion2);
        addItem(onion3);
        Item grapes = new Item("Grapes", "Green seedless Grapes", 2.99, ralphs_id, "https://i.imgur.com/QoklVyp.jpg");
        Item grapes2 = new Item("Grapes", "Pulpy seedless Grapes", 3.49, cf_id, "https://i.imgur.com/QoklVyp.jpg");
        Item grapes3 = new Item("Grapes", "Black seedless Grapes", 3.99, wf_id, "https://imgur.com/a/DiLlX");
        addItem(grapes);
        addItem(grapes2);
        addItem(grapes3);
        Item m_ms = new Item("M&M's Candy Single Size","Peanut Chocolate", 1.29, sfid, "https://i.imgur.com/WqDs9AG.jpg");
        Item m_ms2 = new Item("M&M's Candy Single Size","Milk Chocolate Candy", 1.49, tjs_id, "https://i.imgur.com/WqDs9AG.jpg");
        Item m_ms3 = new Item("M&M's Candy Single Size","Chocolate that melts in your mouth", 1.49, ralphs_id, "https://i.imgur.com/WqDs9AG.jpg");
        addItem(m_ms);
        addItem(m_ms2);
        addItem(m_ms3);
        Item gatorade = new Item("Gatorade", "Gives you energy to perform", 1.29, wf_id, "https://i.imgur.com/J8S3X8o.jpg");
        Item gatorade2 = new Item("Gatorade", "Brings out your best", 1.49, sfid, "https://i.imgur.com/J8S3X8o.jpg");
        Item gatorade3 = new Item("Gatorade", "No excuses to not try", 0.99, ralphs_id, "https://i.imgur.com/J8S3X8o.jpg");
        addItem(gatorade);
        addItem(gatorade2);
        addItem(gatorade3);
        Item water = new Item("Crystal Geyser Spring Water", "Best Tasting water out there", 0.99,costco_id,"https://i.imgur.com/gestSH3.jpg" );
        Item water2 = new Item("Crystal Geyser Spring Water", "Capturing natural springs", 1.29,tjs_id,"https://i.imgur.com/gestSH3.jpg" );
        Item water3 = new Item("Crystal Geyser Spring Water", "Natural Tasting water", 0.89,sfid,"https://i.imgur.com/gestSH3.jpg" );
        addItem(water);
        addItem(water2);
        addItem(water3);
        Item ritz = new Item("Ritz Crackers", "Crackers in fun flavors", 2.59, ralphs_id, "https://i.imgur.com/od9a1XW.jpg");
        Item ritz2 = new Item("Ritz Crackers", "Salted Crackers", 2.99, sfid, "https://i.imgur.com/od9a1XW.jpg");
        Item ritz3 = new Item("Ritz Crackers", "Salted and Buttery Snacks", 2.19, vons_id, "https://i.imgur.com/od9a1XW.jpg");
        addItem(ritz);
        addItem(ritz2);
        addItem(ritz3);
        Item orangeJuice = new Item("Tropicana Orange Juice", "100% Natural Juice", 2.69, vons_id, "https://i.imgur.com/09yDPsO.jpg");
        Item orangeJuice2 = new Item("Tropicana Orange Juice", "Squeezed from Fresh Oranges", 2.99, wf_id, "https://i.imgur.com/09yDPsO.jpg");
        Item orangeJuice3 = new Item("Tropicana Orange Juice", "Pure Premium Orange Pulp", 3.49, tjs_id, "https://i.imgur.com/09yDPsO.jpg");
        addItem(orangeJuice);
        addItem(orangeJuice2);
        addItem(orangeJuice3);

        //TODO: jaz add 30 products (10 unique), 3 per store, comment your name above so we know whos is whos
        //TODO: kyle add 30 products (10 unique), 3 per store, comment your name above so we know whos is whos
        //TODO: jordan add 30 products (10 unique), 3 per store, comment your name above so we know whos is whos
        //TODO: mitchell add 30 products (10 unique), 3 per store, comment your name above so we know whos is whos

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

    public List<Item> getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ITEMS, I_PROJ, null, null, null, null, PRODUCT_NAME);
        ArrayList<Item> items = new ArrayList<>(cursor.getCount());
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            items.add( new Item(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3),
                    cursor.getInt(4), cursor.getString(5)));
            cursor.moveToNext();
        }
        cursor.close();
        return items;
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
        ArrayList<Item> results = new ArrayList<>();
        List<Item> items = getAllItems();

        for(Item i: items){
            String name = i.getName().toLowerCase();
            if(name.contains(sTerm) || sTerm.contains(name) || descContains(term, i.getDescription())){
                results.add(i);
            }
        }
        return results;
    }

    private boolean descContains(String term, String desc) {
        String[] descWords = desc.split(" ");
        for(String word: descWords) {
            if(term.equalsIgnoreCase(word))
                return true;
        }
        return false;
    }
}
