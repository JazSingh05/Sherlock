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
        Item grapes = new Item(GRAPES, "Green seedless Grapes", 2.99, ralphsId,GREEN_URL);
        Item grapes2 = new Item(GRAPES, "Pulpy seedless Grapes", 3.49, caliFreshId, GREEN_URL);
        Item grapes3 = new Item(GRAPES, "Black seedless Grapes", 3.99, wfId,"https://imgur.com/a/DiLlX.jpg");
        addItem(grapes);
        addItem(grapes2);
        addItem(grapes3);
        final String M_MS = "M&M's Candy Single Size";
        final String M_MS_URL = "https://i.imgur.com/WqDs9AG.jpg";
        Item mms = new Item(M_MS,"Peanut Chocolate", 1.29, sfId, M_MS_URL);
        Item mms2 = new Item(M_MS,"Milk Chocolate Candy", 1.49, tjsId, M_MS_URL);
        Item mms3 = new Item(M_MS,"Chocolate that melts in your mouth", 1.49, ralphsId, M_MS_URL);
        addItem(mms);
        addItem(mms2);
        addItem(mms3);
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
        
        //Jaz
        final String GROUND_BEEF = "Ground Beef";
        final String GB_URL = "https://i.imgur.com/a7BSjna.jpg";
        Item groundbeef = new Item(GROUND_BEEF, "filled with protein", 3.49, ralphsId, GB_URL);
        Item groundbeef2 = new Item(GROUND_BEEF, "Great for burgers", 3.49, vonsId, GB_URL);
        Item groundbeef3 = new Item(GROUND_BEEF, "great with ketchup and mustard or a cookout", 4.99, f4lId, GB_URL);
        addItem(groundbeef);
        addItem(groundbeef2);
        addItem(groundbeef3);
        final String GUM = "Eclipse Gum (60 piece)";
        final String GUM_URL = "https://i.imgur.com/jAhakQj.jpg";
        Item gum = new Item(GUM, "white, chewy, and sticky pieces", 2.96, ralphsId, GUM_URL);
        Item gum2 = new Item(GUM, "Minty fresh breath", 2.95, sfId, GUM_URL);
        Item gum3 = new Item(GUM, "take a piece before you speak to your crush", 2.99, caliFreshId, GUM_URL);
        addItem(gum);
        addItem(gum2);
        addItem(gum3);
        final String STRAWBERRY = "Strawberry";
        final String STRAW_URL = "https://i.imgur.com/1zV7MWo.jpg";
        Item straw = new Item(STRAWBERRY, "nice red delicate fruit", 2.50, sfId, STRAW_URL);
        Item straw2 = new Item(STRAWBERRY, "great for making smoothies", 3.23, tjsId, STRAW_URL);
        Item straw3 = new Item(STRAWBERRY, "has little tiny seeds on it",  2.45, f4lId, STRAW_URL);
        addItem(straw);
        addItem(straw2);
        addItem(straw3);
        final String HALLS = "HALLS";
        final String HALLS_URL = "https://i.imgur.com/5v0JOX8.jpg";
        Item halls = new Item(HALLS, "great for coughs", 4.59, wfId,HALLS_URL);
        Item halls2 = new Item(HALLS, "helps with sore throats", 5.23, caliFreshId, HALLS_URL);
        Item halls3 = new Item(HALLS, "clears up nasal passages", 4.25, vonsId, HALLS_URL);
        addItem(halls);
        addItem(halls2);
        addItem(halls3);
        final String ALMOND_MILK = "Almond Milk";
        final String AM_URL = "https://i.imgur.com/BZXKpLG.jpg";
        Item almond_milk = new Item(ALMOND_MILK, "white liquid juice", 4.49, ralphsId,AM_URL);
        Item almond_milk2 = new Item(ALMOND_MILK, "great milk substitute", 3.99, caliFreshId, AM_URL);
        Item almond_milk3 = new Item(ALMOND_MILK, "nutritional beverage", 4.99, wfId,AM_URL);
        addItem(almond_milk);
        addItem(almond_milk2);
        addItem(almond_milk3);
        final String TOOTHBRUSH = "Colgate Toothbrush";
        final String TOOTHBRUSH_URL = "https://i.imgur.com/DBNvhfJ.jpg";
        Item toothbrush = new Item(TOOTHBRUSH,"Cleans your teeth", 4.29, sfId, TOOTHBRUSH_URL);
        Item toothbrush2 = new Item(TOOTHBRUSH,"nice oral hygiene", 5.29, tjsId, TOOTHBRUSH_URL);
        Item toothbrush3 = new Item(TOOTHBRUSH,"great for plaque", 4.52, ralphsId, TOOTHBRUSH_URL);
        addItem(toothbrush);
        addItem(toothbrush2);
        addItem(toothbrush3);
        final String EGGS = "Eggs(Dozen)";
        final String EGGS_URL = "https://i.imgur.com/jf87dZa.jpg";
        Item eggs = new Item(EGGS, "nice yellow yolk", 2.39, wfId, EGGS_URL);
        Item eggs2 = new Item(EGGS, "great for making omelets", 2.29, sfId, EGGS_URL);
        Item eggs3 = new Item(EGGS, "great for scrambled eggs", 2.29, ralphsId,EGGS_URL );
        addItem(eggs);
        addItem(eggs2);
        addItem(eggs3);
        final String AVOCADO = "Avocado";
        final String AVOCADO_URL = "https://i.imgur.com/Xp2qVhg.jpg";
        Item avocado = new Item(AVOCADO, "Green fruit", 1.59,costcoId,AVOCADO_URL);
        Item avocado2 = new Item(AVOCADO, "has a big fat seed", 2.29,ralphsId,AVOCADO_URL);
        Item avocado3 = new Item(AVOCADO, "great for guacamole", 2.39,sfId,AVOCADO_URL);
        addItem(avocado);
        addItem(avocado2);
        addItem(avocado3);
        final String SOAP = "DOVE SOAP";
        final String SOAP_URL = "https://i.imgur.com/970zlri.jpg";
        Item soap = new Item(SOAP, "take nice soapy baths with it", 8.59, tjsId, SOAP_URL);
        Item soap2 = new Item(SOAP, "disinfects body", 8.59, sfId, SOAP_URL);
        Item soap3 = new Item(SOAP, "cleans body of oils and dirt", 8.59, vonsId, SOAP_URL);
        addItem(soap);
        addItem(soap2);
        addItem(soap3);
        final String CANDLE = "Candle-lite Scented Candle";
        final String CANDLE_URL = "https://i.imgur.com/AgBNm3A.jpg";
        Item candle = new Item(CANDLE, "nice scent", 5.29, vonsId, CANDLE_URL);
        Item candle2 = new Item(CANDLE, "freshens up the room", 5.39, wfId, CANDLE_URL);
        Item candle3 = new Item(CANDLE, "candle has a beautiful glow", 5.29, tjsId, CANDLE_URL);
        addItem(candle);
        addItem(candle2);
        addItem(candle3);

        //Mitchell
        final String TURKEY = "Turkey";
        final String TK_URL = "https://i.imgur.com/yO8uR6I.jpg";
        Item turkey = new Item(TURKEY, "lunch meat", 2.99, ralphsId, TK_URL);
        Item turkey2 = new Item(TURKEY, "Sandwich meat", 4.99, costcoId, TK_URL);
        Item turkey3 = new Item(TURKEY, "specialty lunch meat", 1.99, f4lId, TK_URL);
        addItem(turkey);
        addItem(turkey2);
        addItem(turkey3);
        final String SPINACH = "Spinach";
        final String SNCH_URL = "https://i.imgur.com/ffpxG8F.png";
        Item spinach = new Item(SPINACH, "Fresh green vegetable", 2.99, f4lId, SNCH_URL);
        Item spinach2 = new Item(SPINACH, "Healthy choice", 4.50, wfId, SNCH_URL);
        Item spinach3 = new Item(SPINACH, "Great for dieting", 5.00, tjsId, SNCH_URL);
        addItem(spinach);
        addItem(spinach2);
        addItem(spinach3);
        final String COOKIES = "Cookies";
        final String CKE_URL = "https://i.imgur.com/8QzDCge.jpg";
        Item cookies = new Item(COOKIES, "Delicious chocolate chip", 2.99, vonsId, CKE_URL);
        Item cookies2 = new Item(COOKIES, "Yummy oatmeal", 4.99, costcoId, CKE_URL);
        Item cookies3 = new Item(COOKIES, "Great mint chocolate", 4.50, tjsId, CKE_URL);
        addItem(cookies);
        addItem(cookies2);
        addItem(cookies3);
        final String SOUP = "Soup";
        final String SP_URL = "https://i.imgur.com/gI2o94G.jpg";
        Item soup = new Item(SOUP, "Warm and delicious chicken soup", 0.99,f4lId, SP_URL);
        Item soup2 = new Item(SOUP, "Hearty and filling enchilda soup", 1.99, caliFreshId, SP_URL);
        Item soup3 = new Item(SOUP, "Great for the soul", 2.00, ralphsId, SP_URL);
        addItem(soup);
        addItem(soup2);
        addItem(soup3);
        final String TAPATIO = "Tapatio";
        final String TPO_URL = "https://i.imgur.com/JpR47E8.jpg";
        Item tapatio = new Item(TAPATIO, "Spicy!", 0.99, f4lId, TPO_URL);
        Item tapatio2 = new Item(TAPATIO, "Gives a kick", 1.99, ralphsId, TPO_URL);
        Item tapatio3 = new Item(TAPATIO, "BAM", 3.50, costcoId, TPO_URL);
        addItem(tapatio);
        addItem(tapatio2);
        addItem(tapatio3);
        final String BEANS = "Beans";
        final String BNS_URL = "https://i.imgur.com/wcbNDFo.jpg";
        Item beans = new Item(BEANS, "Raw pinto beans", 1.50, ralphsId, BNS_URL);
        Item beans2 = new Item(BEANS, "Canned beans", 2.99, tjsId, BNS_URL);
        Item beans3 = new Item(BEANS, "Raw black beans", 1.50, ralphsId, BNS_URL);
        addItem(beans);
        addItem(beans2);
        addItem(beans3);
        final String POTATOES = "Potatoes";
        final String PTO_URL = "https://i.imgur.com/9wnWuxH.jpg";
        Item potatoes = new Item(POTATOES, "Bag of russets", 4.99, costcoId, PTO_URL);
        Item potatoes2 = new Item(POTATOES, "Bag of reds", 3.99, vonsId, PTO_URL);
        Item potatoes3 = new Item(POTATOES, "Sweet potato", 1.00, ralphsId, PTO_URL);
        addItem(potatoes);
        addItem(potatoes2);
        addItem(potatoes3);
        final String RICE = "Rice";
        final String R_URL = "https://i.imgur.com/LHSldqH.jpg";
        Item rice = new Item(RICE, "White rice", 1.00, ralphsId, R_URL);
        Item rice2 = new Item(RICE, "Large bag of brown rice", 3.50, costcoId, R_URL);
        Item rice3 = new Item(RICE, "Large bag of white rice", 4.00, costcoId, R_URL);
        addItem(rice);
        addItem(rice2);
        addItem(rice3);
        final String QUINOA = "Quinoa";
        final String QOA_URL = "https://i.imgur.com/2OGujrQ.jpg";
        Item quinoa = new Item(QUINOA, "Fiber heavy", 4.50, costcoId, QOA_URL);
        Item quinoa2 = new Item(QUINOA, "Lots of nutrition", 6.50, wfId, QOA_URL);
        Item quinoa3 = new Item(QUINOA, "Your colon thanks you", 5.00, tjsId, QOA_URL);
        addItem(quinoa);
        addItem(quinoa2);
        addItem(quinoa3);
      
        //KYLE
        final String PRINGLE = "Pringles";
        final String P_URL = "https://i.imgur.com/UofDW2g.jpg";
        Item pringle = new Item(PRINGLE, "Crispy potato crisps with a satisfying crunch.", 1.50, ralphsId, P_URL);
        Item pringle2 = new Item(PRINGLE, "Salty potato tasting chip", 2.00, vonsId, P_URL);
        Item pringle3 = new Item(PRINGLE, "Chip in which you can just eat one.", 2.99, wfId, P_URL);
        addItem(pringle);
        addItem(pringle2);
        addItem(pringle3);

        final String MONSTER = "Monster Energy Drink";
        final String MON_URL = "https://i.imgur.com/Cq0LLzQ.jpg";
        Item monster = new Item(MONSTER, "Pure caffeine to unleash the beast", 1.99, lmdId, MON_URL);
        Item monster2 = new Item(MONSTER, "Tastes like monster", 2.49, ralphsId, MON_URL);
        Item monster3 = new Item(MONSTER, "Carbonated energy drink", 2.50, f4lId, MON_URL);
        addItem(monster);
        addItem(monster2);
        addItem(monster3);

        final String TUNA = "4 Pack Chicken of the Sea Canned Tuna";
        final String TUNA_URL = "https://i.imgur.com/qVJdwtY.jpg";
        Item tuna = new Item(TUNA, "Fresh canned tuna", 4.00, f4lId, TUNA_URL);
        Item tuna2 = new Item(TUNA, "Albacore Tuna in water", 5.00, ralphsId, TUNA_URL);
        Item tuna3 = new Item(TUNA, "Savory chicken of the sea", 3.56, vonsId, TUNA_URL);
        addItem(tuna);
        addItem(tuna2);
        addItem(tuna3);

        final String NUTELLA = "Nutella Chocolate Spread";
        final String NUTELLA_URL = "https://i.imgur.com/jrTV34j.jpg";
        Item nutella = new Item(NUTELLA, "Chocolate hazelnut spread", 3.48, f4lId, NUTELLA_URL);
        Item nutella2 = new Item(NUTELLA, "Loved by kids and adults", 2.49, ralphsId, NUTELLA_URL);
        Item nutella3 = new Item(NUTELLA, "Like peanut butter but chocolate", 3.99, vonsId, NUTELLA_URL);
        addItem(nutella);
        addItem(nutella2);
        addItem(nutella3);

        final String GINGER = "1.52 Ounce Ground Ginger";
        final String GINGER_URL = "https://i.imgur.com/snXdXQc.jpg";
        Item ginger = new Item(GINGER, "Ground ginger root", 4.99, tjsId, GINGER_URL);
        Item ginger2 = new Item(GINGER, "Organic ground ginger spice", 3.49, wfId, GINGER_URL);
        Item ginger3 = new Item(GINGER, "Not ground redheads.", 3.99, caliFreshId, GINGER_URL);
        addItem(ginger);
        addItem(ginger2);
        addItem(ginger3);

        final String KETCHUP = "32 Ounce Heinz Tomato Ketchup";
        final String KETCHUP_URL = "https://i.imgur.com/o6BZP2C.jpg";
        Item ketchup = new Item(KETCHUP, "Tomato Ketchup", 3.38, slomId, KETCHUP_URL);
        Item ketchup2 = new Item(KETCHUP, "Heinz Ketchup with crisp tomato taster", 2.99, sfId, KETCHUP_URL);
        Item ketchup3 = new Item(KETCHUP, "Perfect for fries and hamburgers", 3.25, wfId, KETCHUP_URL);
        addItem(ketchup);
        addItem(ketchup2);
        addItem(ketchup3);

        final String GOLDFISH = "Goldfish Crackers 30 Oz Carton";
        final String GOLDFISH_URL = "https://i.imgur.com/Cru94OW.jpg";
        Item goldfish = new Item(GOLDFISH, "The snack cracker smiles back", 6.26, ralphsId, GOLDFISH_URL);
        Item goldfish2 = new Item(GOLDFISH, "Cheesy crackers shaped like fish", 7.50, vonsId, GOLDFISH_URL);
        Item goldfish3 = new Item(GOLDFISH, "You can't eat just one.", 4.99, costcoId, GOLDFISH_URL);
        addItem(goldfish);
        addItem(goldfish2);
        addItem(goldfish3);

        final String PRETZEL = "Rold Gold Tiny Twist Pretzels";
        final String PRETZEL_URL = "https://i.imgur.com/QcleCD4.jpg";
        Item pretzel = new Item(PRETZEL, "Simple salty snack", 2.98, caliFreshId, PRETZEL_URL);
        Item pretzel2 = new Item(PRETZEL, "Baked, not fried", 3.99, sfId, PRETZEL_URL);
        Item pretzel3 = new Item(PRETZEL, "Pair with dips, peanut butter, or hummus", 2.50, lmdId, PRETZEL_URL);
        addItem(pretzel);
        addItem(pretzel2);
        addItem(pretzel3);

        final String LEMONJUICE = "RealLemon 100% Lemon Juice 8 fl oz";
        final String LEMONJUICE_URL = "https://i.imgur.com/55uaytq.jpg";
        Item lemonjuice = new Item(LEMONJUICE, "Fresh Lemon Juice", 1.46, wfId, LEMONJUICE_URL);
        Item lemonjuice2 = new Item(LEMONJUICE, "Made from only lemons", 1.49, tjsId, LEMONJUICE_URL);
        Item lemonjuice3 = new Item(LEMONJUICE, "Perfect for mixers and cakes", 1.99, ralphsId, LEMONJUICE_URL);
        addItem(lemonjuice);
        addItem(lemonjuice2);
        addItem(lemonjuice3);

        final String RANCH = "Hidden Valley Ranch Easy Squeeze 20 Ounce Bottle";
        final String RANCH_URL = "https://i.imgur.com/HwfWXWT.jpg";
        Item ranch = new Item(RANCH, "DIP, DRIZZLE, DUNK", 3.84, caliFreshId, RANCH_URL);
        Item ranch2 = new Item(RANCH, "Gluten Free salad dressing", 4.99, vonsId, RANCH_URL);
        Item ranch3 = new Item(RANCH, "Top anything with some hidden valley ranch.", 2.49, f4lId, RANCH_URL);
        addItem(ranch);
        addItem(ranch2);
        addItem(ranch3);

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
