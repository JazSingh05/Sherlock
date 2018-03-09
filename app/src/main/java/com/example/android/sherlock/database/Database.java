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
        String createStoreTable = String.format(Locale.US, "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s VARCHAR, %s VARCHAR)", TABLE_STORES, ID, NAME, ADDRESS);

        String createProductTable = String.format(Locale.US, "CREATE TABLE %s (" +
                "%s INTEGER PRIMARY KEY AUTOINCREMENT" +
                VARCHAR +
                VARCHAR +
                DOUBLE+
                INTEGER +
                VARCHAR +")", TABLE_ITEMS, ID, PRODUCT_NAME, DESCRIPTION, PRICE, STORE_ID, IMAGE_URL);

        Log.d(TAG, "executing query: " + createStoreTable);
        sqLiteDatabase.execSQL(createStoreTable);
        Log.d(TAG, "executing query: " + createProductTable);
        sqLiteDatabase.execSQL(createProductTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STORES);
        onCreate(sqLiteDatabase);
    }

    public void addTestData() {
        Log.d("DATABASE", "adding test data to the database");
        //example below
        Store costco = new Store("Costco", "1111 LOVR, San Luis Obispo, 93401");
        Store ralphs = new Store("Ralphs","201 Madonna Rd, San Luis Obispo, CA 93405");
        Store caliFresh = new Store("California Fresh Market SLO", "771 Foothill BLVD, San Luis Obispo, CA 93405");
        Store lincolnMarketDeli = new Store("Lincoln Market & Deli", "496 Broad St, San Luis Obispo, CA 93405");
        Store sloOrientalMarket = new Store("San Luis Oriental Market", "1255 Monterey St # C, San Luis Obispo, CA 93401");
        Store vons = new Store("Vons","117 Marsh St, San Luis Obispo, CA 93401");
        Store smartAndFinal = new Store("Smart & Final","1255 Chorro St, San Luis Obispo, CA 93401");
        Store wholeFoods = new Store("Whole Foods Market","1255 Prado St #B, San Luis Obispo, CA 93401");
        Store traderJoes = new Store("Trader Joe's","145 Tank Farm Road, San Luis Obispo, CA 93401");
        Store food4Less = new Store("Food 4 Less","345 Los Osos Valley Road, San Luis Obispo, CA 93401");
        //All the store IDs for our database
        long costcoId =  addStore(costco); //call to add store returns the id of the newly added item, but as a long
        long ralphsId = addStore(ralphs);
        long caliFreshId = addStore(caliFresh);
        long lmdId = addStore(lincolnMarketDeli);
        long slomId = addStore(sloOrientalMarket);
        long vonsId = addStore(vons);
        long sfId = addStore(smartAndFinal);
        long wfId = addStore(wholeFoods);
        long tjsId = addStore(traderJoes);
        long f4lId = addStore(food4Less);

        //adding items to the database using the store IDs
        //stephen
        final String BANANA = "Banana";
        final String B_URL = "https://i.imgur.com/WWxI0Pq.jpg";
        Item banana = new Item(BANANA, "Tasty yellow fruit that monkeys like", 1.59, costcoId, B_URL);
        Item banana2 = new Item(BANANA, "Tropical fruit, soft good for smoothies", 1.29, vonsId, B_URL);
        Item banana3 = new Item(BANANA, "Fruit High in potassium", 1.00, f4lId, B_URL);
        addItem(banana); //1.1
        addItem(banana2); //1.2
        addItem(banana3); //1.3
        final String LEMON = "Lemon";
        final String L_URL = "https://i.imgur.com/sk2Q0Se.jpg";
        Item lemon = new Item(LEMON, "Sour yellow fruit used for beverages", 0.79, lmdId, L_URL);
        Item lemon2 = new Item(LEMON, "Delicious citrus fruit, tart", 0.60, ralphsId, L_URL);
        Item lemon3 = new Item(LEMON, "Zesty yellow citrus fruit", 0.89, caliFreshId, L_URL);
        addItem(lemon); //2.1
        addItem(lemon2); //2.2
        addItem(lemon3); //2.3
        final String YOGURT = "Greek Yogurt";
        final String Y_URL = "https://i.imgur.com/FXyZH3P.jpg";
        Item yogurt = new Item(YOGURT, "Probiotic dairy snack, can be sour", 4.89, tjsId, Y_URL);
        Item yogurt2 = new Item(YOGURT, "Organic dairy yogurt, live cultures", 5.79, wfId, Y_URL);
        Item yogurt3 = new Item(YOGURT, "Probiotic dairy snack, can be sour", 3.99, ralphsId, Y_URL);
        addItem(yogurt); //3.1
        addItem(yogurt2); //3.2
        addItem(yogurt3); //3.3
        final String BACON = "Bacon";
        final String BCN_URL = "https://i.imgur.com/k40L3Yj.jpg";
        Item bacon = new Item(BACON, "Delicious salty pork breakfast meat", 6.85, ralphsId, BCN_URL);
        Item bacon2 = new Item(BACON, "Meat- applewood smoked bacon", 5.99, vonsId, BCN_URL);
        Item bacon3 = new Item(BACON, "Uncured pork belly bacon, thick cut meat", 4.19, f4lId, BCN_URL);
        addItem(bacon); //4.1
        addItem(bacon2); //4.2
        addItem(bacon3); //4.3
        final String WINGS = "Chicken Wings";
        final String W_URL = "https://i.imgur.com/yrYepED.png";
        Item wings = new Item(WINGS, "Zesty and spicy chicken snack, great for games", 10.75, caliFreshId, W_URL);
        Item wings2 = new Item(WINGS, "Spicy chicen snack, white mean", 10.99, wfId, W_URL);
        Item wings3 = new Item(WINGS, "Cripsy chicken meat snack", 9.75, caliFreshId, W_URL);
        addItem(wings); //5.1
        addItem(wings2); //5.2
        addItem(wings3); //5.3
        final String TOFU = "Tofu";
        final String T_URL = "https://i.imgur.com/rAnJrmc.jpg";
        Item tofu = new Item(TOFU, "Not so healthy protein alternative, messes with hormonal levels", 4.20, slomId, T_URL);
        Item tofu2 = new Item(TOFU, "Vegetable basede protein", 3.00, wfId, T_URL);
        Item tofu3 = new Item(TOFU, "Asian food, high protein, vegetable based", 4.50, lmdId, T_URL);
        addItem(tofu); //6.1
        addItem(tofu2); //6.2
        addItem(tofu3); //6.3
        final String STEAK = "Steak";
        final String S_URL = "https://i.imgur.com/zUpzG0K.png";
        Item steak = new Item(STEAK, "Nutritious protein source, delicious meat", 14.88, wfId, S_URL);
        Item steak2 = new Item(STEAK, "USDA ribeye", 15.99, tjsId, S_URL);
        Item steak3 = new Item(STEAK, "Flank steak, ideal for fajitas", 10.75, f4lId, S_URL);
        addItem(steak); //7.1
        addItem(steak2); //7.2
        addItem(steak3); //7.3
        final String BROC = "Broccoli";
        final String BR_URL = "https://i.imgur.com/g4SOZjO.png";
        Item broccoli = new Item(BROC, "Green vegetable rich in vitamins and minerals", 2.65, vonsId, BR_URL);
        Item broccoli2 = new Item(BROC, "Vegetable rich in protein and vitamin c", 1.65, ralphsId, BR_URL);
        Item broccoli3 = new Item(BROC, "Heart healthy green vegetable", 2.89, caliFreshId, BR_URL);
        addItem(broccoli); //8.1
        addItem(broccoli2); //8.2
        addItem(broccoli3); //8.3
        final String GARLIC = "Garlic";
        final String G_URL = "https://i.imgur.com/TbPx5Iq.jpg";
        Item garlic = new Item(GARLIC, "Pungent vegetable used for flavoring foods", .99, f4lId, G_URL );
        Item garlic2 = new Item(GARLIC, "Strong herb used for flavoring dishes", 1.09, wfId, G_URL);
        Item garlic3 = new Item(GARLIC, "Fresh gilroy garlic", .89, ralphsId, G_URL);
        addItem(garlic); //9.1
        addItem(garlic2); //9.2
        addItem(garlic3); //9.3
        final String BEER = "Beer - Barrelhouse IPA";
        final String BEER_URL = "https://i.imgur.com/7pHUBBt.jpg";
        Item beer = new Item(BEER, "Flagship IPA from barrelhouse brewery, citrusy", 5.99, sfId, BEER_URL);
        Item beer2 = new Item(BEER, "Delicious crisp IPA from local brewery", 3.99, tjsId, BEER_URL);
        Item beer3 = new Item(BEER, "Strong IPA with lots of hops", 3.49, sfId, BEER_URL);
        addItem(beer); //10.1
        addItem(beer2); //10.2
        addItem(beer3); //10.3

        //Jordan
        Item chickenBreasts = new Item("Chicken Breasts", "Protein packed poultry", 2.99, ralphsId, "https://i.imgur.com/bb1c1Iq.jpg");
        Item chickenBreasts2 = new Item("Chicken Breasts", "Tasty and healthy bird meat", 3.15, caliFreshId, "https://i.imgur.com/bb1c1Iq.jpg");
        Item chickenBreasts3 = new Item("Chicken Breasts", "Inexpensive and healthy protein", 2.99, vonsId, "https://i.imgur.com/bb1c1Iq.jpg");
        addItem(chickenBreasts);
        addItem(chickenBreasts2);
        addItem(chickenBreasts3);
        Item coke12Cans = new Item("Coke 12 cans", "Delicious and refreshing soft drink", 4.99, ralphsId, "https://i.imgur.com/4D55jln.jpg");
        Item coke12Cans2 = new Item("Coke 12 cans", "Refreshing and popular cola", 4.99, vonsId, "https://i.imgur.com/4D55jln.jpg");
        Item coke12Cans3 = new Item("Coke 12 cans", "Delicious soda beverage", 4.99, sfId, "https://i.imgur.com/4D55jln.jpg");
        addItem(coke12Cans);
        addItem(coke12Cans2);
        addItem(coke12Cans3);
        Item condoms = new Item("Condoms", "36 of the most popular penile-contraceptive product", 27.99, ralphsId, "https://i.imgur.com/hFPgUX8.jpg");
        Item condoms2 = new Item("Condoms", "Softest and most durable condoms on the market", 27.99, vonsId, "https://i.imgur.com/hFPgUX8.jpg");
        Item condoms3 = new Item("Condoms", "Cozier than socks knitted by your grandma", 31.99, sfId, "https://i.imgur.com/hFPgUX8.jpg");
        addItem(condoms);
        addItem(condoms2);
        addItem(condoms3);
        Item asparagus = new Item("Asparagus", "Delicious and nutritious vegetable", .67, ralphsId, "https://i.imgur.com/Djqk9Vz.jpg");
        Item asparagus2 = new Item("Asparagus", "Tasty vegetable that is good for you", .69, vonsId, "https://i.imgur.com/Djqk9Vz.jpg");
        Item asparagus3 = new Item("Asparagus", "Green and delicious vegetable", .77, sfId, "https://i.imgur.com/Djqk9Vz.jpg");
        addItem(asparagus);
        addItem(asparagus2);
        addItem(asparagus3);
        Item lettuce = new Item("Lettuce", "Round and nutritious vegetable", 1.69, ralphsId, "https://i.imgur.com/mtVdQbR.jpg");
        Item lettuce2 = new Item("Lettuce", "Healthy and tasty green vegetable", 1.79, vonsId, "https://i.imgur.com/mtVdQbR.jpg");
        Item lettuce3 = new Item("Lettuce", "Green, round vegetable", 1.99, sfId, "https://i.imgur.com/mtVdQbR.jpg");
        addItem(lettuce);
        addItem(lettuce2);
        addItem(lettuce3);
        Item milkGallon = new Item("Milk Gallon", "1 Gallon of delicious cow's milk", 2.75, ralphsId, "https://i.imgur.com/XDdGN1P.jpg");
        Item milkGallon2 = new Item("Milk Gallon", "Tasty cow milk", 2.95, vonsId, "https://i.imgur.com/XDdGN1P.jpg");
        Item milkGallon3 = new Item("Milk Gallon", "Delicious milk straight from the cow's teet", 3.05, sfId, "https://i.imgur.com/XDdGN1P.jpg");
        addItem(milkGallon);
        addItem(milkGallon2);
        addItem(milkGallon3);
        Item cashews = new Item("Cashews", "Package of top-quality nuts", 7.99, sfId, "https://i.imgur.com/OhtJLCx.jpg");
        Item cashews2 = new Item("Cashews", "Salty and Tasty Cashews", 6.99, ralphsId, "https://i.imgur.com/OhtJLCx.jpg");
        Item cashews3 = new Item("Cashews", "High quality nuts", 7.59, vonsId, "https://i.imgur.com/OhtJLCx.jpg");
        addItem(cashews);
        addItem(cashews2);
        addItem(cashews3);
        Item pear = new Item("Pear", "Tasty, green/yellow fruit", .59, ralphsId, "https://i.imgur.com/gG4xaCe.png");
        Item pear2 = new Item("Pear", "Healthy fruit", .59, sfId, "https://i.imgur.com/gG4xaCe.png");
        Item pear3 = new Item("Pear", "Fruit shaped like an oblong-apple", .59, vonsId, "https://i.imgur.com/gG4xaCe.png");
        addItem(pear);
        addItem(pear2);
        addItem(pear3);
        Item skittles = new Item("Skittles", "Fruit flavored candy", 1.09, ralphsId, "https://i.imgur.com/tpdhopo.jpg");
        Item skittles2 = new Item("Skittles", "Candy flavored like the rainbow", .99, sfId, "https://i.imgur.com/tpdhopo.jpg");
        Item skittles3 = new Item("Skittles", "Delicious fruity candy", 1.07, vonsId, "https://i.imgur.com/tpdhopo.jpg");
        addItem(skittles);
        addItem(skittles2);
        addItem(skittles3);
        Item starburst = new Item("Starburst", "Bite-sized fruity candy", 1.09, ralphsId, "https://i.imgur.com/WCB3jbI.jpg");
        Item starburst2 = new Item("Starburst", "Fruit flavored candy", .99, sfId, "https://i.imgur.com/WCB3jbI.jpg");
        Item starburst3 = new Item("Starburst", "Individually packaged candy treats", 1.07, vonsId, "https://i.imgur.com/WCB3jbI.jpg");
        addItem(starburst);
        addItem(starburst2);
        addItem(starburst3);
        Item carrot = new Item("Carrot", "Healthy and nutritious orange vegetable", .60, vonsId, "https://i.imgur.com/JtqRsdU.jpg");
        Item carrot2 = new Item("Carrot", "Nutrition packed vegetable", .65, ralphsId, "https://i.imgur.com/JtqRsdU.jpg");
        Item carrot3 = new Item("Carrot", "Equally enjoy from farm animals to humans", .77, caliFreshId, "https://i.imgur.com/JtqRsdU.jpg");
        addItem(carrot);
        addItem(carrot2);
        addItem(carrot3);


        //Abbinav
        final String APPLE = "Apple";
        final String A_URL = "https://i.imgur.com/B3Y8cVf.jpg";
        Item apple = new Item(APPLE, "Round Red and Sweet Fruit", 2.99, ralphsId, A_URL);
        Item apple2 = new Item(APPLE, "Red,Sweet and Healthy Fruit", 3.49, vonsId, A_URL);
        Item apple3 = new Item(APPLE, "The edible one", 3.99, wfId, A_URL);
        addItem(apple);
        addItem(apple2);
        addItem(apple3);
        final String CHEETOS = "Hot Cheetos";
        final String CHEETOS_URL = "https://i.imgur.com/TUiAMwm.jpg";
        Item cheetos = new Item(CHEETOS, "Crunchy Cheese Snack", 1.99, ralphsId, CHEETOS_URL);
        Item cheetos2 = new Item(CHEETOS, "Crunchy Spicy Snack", 1.99, sfId, CHEETOS_URL);
        Item cheetos3 = new Item(CHEETOS, "Chips that turn everything Red", 2.99, vonsId, CHEETOS_URL);
        addItem(cheetos);
        addItem(cheetos2);
        addItem(cheetos3);
        final String SPRITE = "Sprite Soda";
        final String SPRITE_URL = "https://i.imgur.com/lOFpLeO.jpg";
        Item sprite = new Item(SPRITE, "Lime Flavored Soft Drink", 1.69, sfId, SPRITE_URL);
        Item sprite2 = new Item(SPRITE, "Caffeine Free Soft Drink", 1.99, tjsId, SPRITE_URL);
        Item sprite3 = new Item(SPRITE, "Colorless Lemon Drink", 2.09, f4lId, SPRITE_URL);
        addItem(sprite);
        addItem(sprite2);
        addItem(sprite3);
        final String ONION = "Onion";
        final String ONION_URL = "https://i.imgur.com/lu8nJVP.jpg";
        Item onion = new Item(ONION, "Used for flavoring foods", 2.99, wfId,ONION_URL);
        Item onion2 = new Item(ONION, "Vegetable for adding flavor to dishes", 2.19, caliFreshId, ONION_URL);
        Item onion3 = new Item(ONION, "Sweet Yellow Onion", 2.99, vonsId, ONION_URL);
        addItem(onion);
        addItem(onion2);
        addItem(onion3);
        final String GRAPES = "Grapes";
        final String GREEN_URL = "https://i.imgur.com/QoklVyp.jpg";
        final String BLACK_URL = "https://imgur.com/a/DiLlX";
        Item grapes = new Item(GRAPES, "Green seedless Grapes", 2.99, ralphsId,GREEN_URL);
        Item grapes2 = new Item(GRAPES, "Pulpy seedless Grapes", 3.49, caliFreshId, GREEN_URL);
        Item grapes3 = new Item(GRAPES, "Black seedless Grapes", 3.99, wfId,BLACK_URL);
        addItem(grapes);
        addItem(grapes2);
        addItem(grapes3);
        final String M_MS = "M&M's Candy Single Size";
        final String M_MS_URL = "https://i.imgur.com/WqDs9AG.jpg";
        Item m_ms = new Item(M_MS,"Peanut Chocolate", 1.29, sfId, M_MS_URL);
        Item m_ms2 = new Item(M_MS,"Milk Chocolate Candy", 1.49, tjsId, M_MS_URL);
        Item m_ms3 = new Item(M_MS,"Chocolate that melts in your mouth", 1.49, ralphsId, M_MS_URL);
        addItem(m_ms);
        addItem(m_ms2);
        addItem(m_ms3);
        final String GATORADE = "Gatorade";
        final String GATORADE_URL = "https://i.imgur.com/J8S3X8o.jpg";
        Item gatorade = new Item(GATORADE, "Gives you energy to perform", 1.29, wfId, GATORADE_URL);
        Item gatorade2 = new Item(GATORADE, "Brings out your best", 1.49, sfId, GATORADE_URL);
        Item gatorade3 = new Item(GATORADE, "No excuses to not try", 0.99, ralphsId,GATORADE_URL );
        addItem(gatorade);
        addItem(gatorade2);
        addItem(gatorade3);
        final String WATER = "Crystal Geyser Spring Water";
        final String WATER_URL = "https://i.imgur.com/gestSH3.jpg";
        Item water = new Item(WATER, "Best Tasting water out there", 0.99,costcoId,WATER_URL);
        Item water2 = new Item(WATER, "Capturing natural springs", 1.29,tjsId,WATER_URL);
        Item water3 = new Item(WATER, "Natural Tasting water", 0.89,sfId,WATER_URL);
        addItem(water);
        addItem(water2);
        addItem(water3);
        final String RITZ = "Ritz Crackers";
        final String RITZ_URL = "https://i.imgur.com/od9a1XW.jpg";
        Item ritz = new Item(RITZ, "Crackers in fun flavors", 2.59, ralphsId, RITZ_URL);
        Item ritz2 = new Item(RITZ, "Salted Crackers", 2.99, sfId, RITZ_URL);
        Item ritz3 = new Item(RITZ, "Salted and Buttery Snacks", 2.19, vonsId, RITZ_URL);
        addItem(ritz);
        addItem(ritz2);
        addItem(ritz3);
        final String ORANGE_JUICE = "Tropicana Orange Juice";
        final String ORANGE_JUICE_URL = "https://i.imgur.com/09yDPsO.jpg";
        Item orangeJuice = new Item(ORANGE_JUICE, "100% Natural Juice", 2.69, vonsId, ORANGE_JUICE_URL);
        Item orangeJuice2 = new Item(ORANGE_JUICE, "Squeezed from Fresh Oranges", 2.99, wfId, ORANGE_JUICE_URL);
        Item orangeJuice3 = new Item(ORANGE_JUICE, "Pure Premium Orange Pulp", 3.49, tjsId, ORANGE_JUICE_URL);
        addItem(orangeJuice);
        addItem(orangeJuice2);
        addItem(orangeJuice3);

        //TODO: jaz add 30 products (10 unique), 3 per store, comment your name above so we know whos is whos
        //TODO: kyle add 30 products (10 unique), 3 per store, comment your name above so we know whos is whos
        //TODO: mitchell add 30 products (10 unique), 3 per store, comment your name above so we know whos is whos

    }

    private long addStore(Store store){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME, store.getStoreName());
        cv.put(ADDRESS, store.getAddress());
        long result = db.insert(TABLE_STORES, null, cv);
        Log.d(TAG, String.format("inserting store data: Added %s to the db", store.toString()));
        db.close();
        return result;
    }

    private void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(PRODUCT_NAME, item.getName());
        cv.put(DESCRIPTION, item.getDescription());
        cv.put(PRICE, item.getPrice());
        cv.put(STORE_ID, item.getStoreId());
        cv.put(IMAGE_URL, item.getImageUrl());
        db.insert(TABLE_ITEMS, null, cv);
        Log.d(TAG, String.format("inserting item data: Added %s to db", item.toString()));
        db.close();
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

    private List<Item> getAllItems() {
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

    private Map<String, Store> getStoresAsMapByName() {
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
