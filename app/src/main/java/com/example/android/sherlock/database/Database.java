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
        Store target = new Store("Target","11990 Los Osos Valley Road, San Luis Obispo, CA 93405");
        Store bestbuy = new Store("Best Buy","225 Madonna Road, San Luis Obispo, CA 93405");
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
        long tarId = addStore(target);
        long bbId = addStore(bestbuy);

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
        final String CHICKEN = "Chicken Breasts";
        final String CHICKEN_URL = "https://i.imgur.com/bb1c1Iq.jpg";
        Item chickenBreasts = new Item(CHICKEN, "Protein packed poultry", 2.99, ralphsId, CHICKEN_URL);
        Item chickenBreasts2 = new Item(CHICKEN, "Tasty and healthy bird meat", 3.15, caliFreshId, CHICKEN_URL);
        Item chickenBreasts3 = new Item(CHICKEN, "Inexpensive and healthy protein", 2.99, vonsId, CHICKEN_URL);
        addItem(chickenBreasts);
        addItem(chickenBreasts2);
        addItem(chickenBreasts3);
        final String COKE = "Coke 12 cans";
        final String COKE_URL = "https://i.imgur.com/4D55jln.jpg";
        Item coke12Cans = new Item(COKE, "Delicious and refreshing soft drink", 4.99, ralphsId, COKE_URL);
        Item coke12Cans2 = new Item(COKE, "Refreshing and popular cola", 4.99, vonsId, COKE_URL);
        Item coke12Cans3 = new Item(COKE, "Delicious soda beverage", 4.99, sfId, COKE_URL);
        addItem(coke12Cans);
        addItem(coke12Cans2);
        addItem(coke12Cans3);
        final String CONDOMS = "Condoms";
        final String CONDOMS_URL = "https://i.imgur.com/hFPgUX8.jpg";
        Item condoms = new Item(CONDOMS, "36 of the most popular penile-contraceptive product", 27.99, ralphsId,CONDOMS_URL );
        Item condoms2 = new Item(CONDOMS, "Softest and most durable condoms on the market", 27.99, vonsId, CONDOMS_URL);
        Item condoms3 = new Item(CONDOMS, "Cozier than socks knitted by your grandma", 31.99, sfId, CONDOMS_URL);
        addItem(condoms);
        addItem(condoms2);
        addItem(condoms3);
        final String ASPARAGUS = "Asparagus";
        final String ASPARAGUS_URL = "https://i.imgur.com/Djqk9Vz.jpg";
        Item asparagus = new Item(ASPARAGUS, "Delicious and nutritious vegetable", .67, ralphsId, ASPARAGUS_URL);
        Item asparagus2 = new Item(ASPARAGUS, "Tasty vegetable that is good for you", .69, vonsId, ASPARAGUS_URL);
        Item asparagus3 = new Item(ASPARAGUS, "Green and delicious vegetable", .77, sfId, ASPARAGUS_URL);
        addItem(asparagus);
        addItem(asparagus2);
        addItem(asparagus3);
        final String LETTUCE = "Lettuce";
        final String LETTUCE_URL = "https://i.imgur.com/mtVdQbR.jpg";
        Item lettuce = new Item(LETTUCE, "Round and nutritious vegetable", 1.69, ralphsId, LETTUCE_URL);
        Item lettuce2 = new Item(LETTUCE, "Healthy and tasty green vegetable", 1.79, vonsId, LETTUCE_URL);
        Item lettuce3 = new Item(LETTUCE, "Green, round vegetable", 1.99, sfId, LETTUCE_URL);
        addItem(lettuce);
        addItem(lettuce2);
        addItem(lettuce3);
        final String MILK =  "Milk Gallon";
        final String MILK_URL = "https://i.imgur.com/XDdGN1P.jpg";
        Item milkGallon = new Item(MILK, "1 Gallon of delicious cow's milk", 2.75, ralphsId, MILK_URL);
        Item milkGallon2 = new Item(MILK, "Tasty cow milk", 2.95, vonsId, MILK_URL);
        Item milkGallon3 = new Item(MILK, "Delicious milk straight from the cow's teet", 3.05, sfId, MILK_URL);
        addItem(milkGallon);
        addItem(milkGallon2);
        addItem(milkGallon3);
        final String CASHEW = "Cashews";
        final String CASHEW_URL = "https://i.imgur.com/OhtJLCx.jpg";
        Item cashews = new Item(CASHEW, "Package of top-quality nuts", 7.99, sfId, CASHEW_URL);
        Item cashews2 = new Item(CASHEW, "Salty and Tasty Cashews", 6.99, ralphsId, CASHEW_URL);
        Item cashews3 = new Item(CASHEW, "High quality nuts", 7.59, vonsId, CASHEW_URL);
        addItem(cashews);
        addItem(cashews2);
        addItem(cashews3);
        final String PEAR = "Pear";
        final String PEAR_URL = "https://i.imgur.com/gG4xaCe.png";
        Item pear = new Item(PEAR, "Tasty, green/yellow fruit", .59, ralphsId, PEAR_URL);
        Item pear2 = new Item(PEAR, "Healthy fruit", .59, sfId, PEAR_URL);
        Item pear3 = new Item(PEAR, "Fruit shaped like an oblong-apple", .59, vonsId, PEAR_URL);
        addItem(pear);
        addItem(pear2);
        addItem(pear3);
        final String SKITTLES = "Skittles";
        final String SKITTLES_URL = "https://i.imgur.com/tpdhopo.jpg";
        Item skittles = new Item(SKITTLES, "Fruit flavored candy", 1.09, ralphsId, SKITTLES_URL);
        Item skittles2 = new Item(SKITTLES, "Candy flavored like the rainbow", .99, sfId, SKITTLES_URL);
        Item skittles3 = new Item(SKITTLES, "Delicious fruity candy", 1.07, vonsId, SKITTLES_URL);
        addItem(skittles);
        addItem(skittles2);
        addItem(skittles3);
        final String STARBUST = "Starbust";
        final String STARBUST_URL = "https://i.imgur.com/WCB3jbI.jpg";
        Item starburst = new Item(STARBUST, "Bite-sized fruity candy", 1.09, ralphsId, STARBUST_URL);
        Item starburst2 = new Item(STARBUST, "Fruit flavored candy", .99, sfId, STARBUST_URL);
        Item starburst3 = new Item(STARBUST, "Individually packaged candy treats", 1.07, vonsId, STARBUST_URL);
        addItem(starburst);
        addItem(starburst2);
        addItem(starburst3);
        final String CARROT = "Carrot";
        final String CARROT_URL = "https://i.imgur.com/JtqRsdU.jpg";
        Item carrot = new Item(CARROT, "Healthy and nutritious orange vegetable", .60, vonsId, CARROT_URL);
        Item carrot2 = new Item(CARROT, "Nutrition packed vegetable", .65, ralphsId, CARROT_URL);
        Item carrot3 = new Item(CARROT, "Equally enjoy from farm animals to humans", .77, caliFreshId, CARROT_URL);
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
        Item grapes3 = new Item(GRAPES, "Black seedless Grapes", 3.99, wfId,"https://i.imgur.com/v8Od6K9.jpg");
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
        Item almondMilk = new Item(ALMOND_MILK, "white liquid juice", 4.49, ralphsId,AM_URL);
        Item almondMilk2 = new Item(ALMOND_MILK, "great milk substitute", 3.99, caliFreshId, AM_URL);
        Item almondMilk3 = new Item(ALMOND_MILK, "nutritional beverage", 4.99, wfId,AM_URL);
        addItem(almondMilk);
        addItem(almondMilk2);
        addItem(almondMilk3);
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
        final String SOAP = "Dove Soap";
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
        
        //TVS
        final String BN_TV = "Brand new television";
        final String SD_TV = "Standard definition television";
        final String BN_STV = "Brand new smart television";
        final String SD_STV = "Standard definition smart television";
        final String WEST24 = "Westinghouse - 24 Inch 720p";
        final String WEST24_URL = "https://i.imgur.com/Ad9Cb5v.jpg";
        Item west24 = new Item(WEST24, "Standard def 24 Inch television", 89.99, tarId, WEST24_URL);
        Item west242 = new Item(WEST24, "Standard def television", 119.99, costcoId, WEST24_URL);
        Item west243 = new Item(WEST24, BN_TV, 85.99, bbId, WEST24_URL);
        addItem(west24);
        addItem(west242);
        addItem(west243);
        final String VIZIO24D = "Vizio 24 Inch 720p LED Television";
        final String VIZIO24D_URL = "https://i.imgur.com/n1XVKT9.jpg";
        Item vizio24d = new Item(VIZIO24D, "Stndrd. definition 24 Inch television", 89.99, tarId, VIZIO24D_URL);
        Item vizio24d2 = new Item(VIZIO24D, "Standard definition 24 \"television", 119.99, costcoId, VIZIO24D_URL);
        Item vizio24d3 = new Item(VIZIO24D, "Brand new TV", 85.99, bbId, VIZIO24D_URL);
        addItem(vizio24d);
        addItem(vizio24d2);
        addItem(vizio24d3);
        final String LG24LF = "LG 24 Inch 720p LED Television";
        final String LG24LF_URL = "https://i.imgur.com/sv14nyo.jpg";
        Item lg24lf = new Item(LG24LF, "Standard definition 24 Inch television", 99.99, tarId, LG24LF_URL);
        Item lg24lf2 = new Item(LG24LF, "Stndrd. definition television", 119.99, costcoId, LG24LF_URL);
        Item lg24lf3 = new Item(LG24LF, "Brand new Television Set", 89.99, bbId, LG24LF_URL);
        addItem(lg24lf);
        addItem(lg24lf2);
        addItem(lg24lf3);
        final String WEST32SM = "Westinghouse 32 Inch LED 720p Smart Television";
        final String WEST32SM_URL = "https://i.imgur.com/JCjINrd.jpg";
        Item west32sm = new Item(WEST32SM, "Standard definition 32 Inch smart television", 109.99, tarId, WEST32SM_URL);
        Item west32sm2 = new Item(WEST32SM, "Standard smart television", 119.99, costcoId, WEST32SM_URL);
        Item west32sm3 = new Item(WEST32SM, "Smart television", 119.99, bbId, WEST32SM_URL);
        addItem(west32sm);
        addItem(west32sm2);
        addItem(west32sm3);
        final String WEST32SMH = "Westinghouse 32 Inch LED 720p Smart Television";
        final String WEST32SMH_URL = "https://i.imgur.com/futEmGt.jpg";
        Item west32smh = new Item(WEST32SMH, "Standard 32 Inch television", 139.99, tarId, WEST32SMH_URL);
        Item west32smh2 = new Item(WEST32SMH, SD_STV, 129.99, costcoId, WEST32SMH_URL);
        Item west32smh3 = new Item(WEST32SMH, BN_STV, 119.99, bbId, WEST32SMH_URL);
        addItem(west32smh);
        addItem(west32smh2);
        addItem(west32smh3);
        final String SAM24 = "24 Inch Samsung Television";
        final String SAM24_URL = "https://i.imgur.com/EyRpoBs.jpg";
        Item sam24 = new Item(SAM24, "Standard definition 24 Inch television", 139.99, tarId, SAM24_URL);
        Item sam242 = new Item(SAM24, "Standard def TV", 135.99, costcoId, SAM24_URL);
        Item sam243 = new Item(SAM24, "New television set", 125.99, bbId, SAM24_URL);
        addItem(sam24);
        addItem(sam242);
        addItem(sam243);
        final String VIZ24F = "Vizio 24 Inch LED 1080p Smart Television";
        final String VIZ24F_URL = "https://i.imgur.com/A9wHob0.jpg";
        Item viz24f = new Item(VIZ24F, "High definition 24 Inch television", 139.99, tarId, VIZ24F_URL);
        Item viz24f2 = new Item(VIZ24F, "High definition television", 135.99, costcoId, VIZ24F_URL);
        Item viz24f3 = new Item(VIZ24F, BN_TV, 139.99, bbId, VIZ24F_URL);
        addItem(viz24f);
        addItem(viz24f2);
        addItem(viz24f3);
        final String SAM2445 = "Samsung 24 Inch 720p Smart TV";
        final String SAM2445_URL = "https://i.imgur.com/uWsLgCF.jpg";
        Item sam2445 = new Item(SAM2445, "Standard definition 24 Inch smart television", 149.99, tarId, SAM2445_URL);
        Item sam24452 = new Item(SAM2445, SD_STV, 145.99, costcoId, SAM2445_URL);
        Item sam24453 = new Item(SAM2445, BN_STV, 139.99, bbId, SAM2445_URL);
        addItem(sam2445);
        addItem(sam24452);
        addItem(sam24453);
        final String LG24WU = "LG 24 Inch 720p Smart TV";
        final String LG24WU_URL = "https://i.imgur.com/WnVwunq.jpg";
        Item lg24wu = new Item(LG24WU, "Standard definition 24 Inch smart television", 149.99, tarId, LG24WU_URL);
        Item lg24wu2 = new Item(LG24WU, SD_STV, 145.99, costcoId, LG24WU_URL);
        Item lg24wu3 = new Item(LG24WU, BN_STV, 139.99, bbId, LG24WU_URL);
        addItem(lg24wu);
        addItem(lg24wu2);
        addItem(lg24wu3);
        final String SAMS28 = "Samsung 28 Inch LED 720p Television";
        final String SAMS28_URL = "https://i.imgur.com/J1XhesA.jpg";
        Item sams28 = new Item(SAMS28, "Standard definition 28 Inch television", 149.99, tarId, SAMS28_URL);
        Item sams282 = new Item(SAMS28, "Standard Television", 185.99, costcoId, SAMS28_URL);
        Item sams283 = new Item(SAMS28, BN_TV, 189.99, bbId, SAMS28_URL);
        addItem(sams28);
        addItem(sams282);
        addItem(sams283);
        final String LG28 = "LG 28 Inch LED 720p Television";
        final String LG28_URL = "https://i.imgur.com/PyQM7JT.jpg";
        Item lg28 = new Item(LG28, "Standard definition 28 Inch television", 149.99, tarId, LG28_URL);
        Item lg282 = new Item(LG28, SD_TV, 185.99, costcoId, LG28_URL);
        Item lg283 = new Item(LG28, BN_TV, 179.99, bbId, LG28_URL);
        addItem(lg28);
        addItem(lg282);
        addItem(lg283);
        final String WH32 = "Westinghouse 32 Inch LED 720p Television with DVD";
        final String WH32_URL = "https://i.imgur.com/ch2RyIB.jpg";
        Item wh32 = new Item(WH32, "Standard definition 32 Inch television with DVD", 169.99, tarId, WH32_URL);
        Item wh322 = new Item(WH32, SD_TV, 165.99, costcoId, WH32_URL);
        Item wh323 = new Item(WH32, BN_TV, 179.99, bbId, WH32_URL);
        addItem(wh32);
        addItem(wh322);
        addItem(wh323);
        final String PS32 = "Proscan 32 Inch LED 720p Television with DVD";
        final String PS32_URL = "https://i.imgur.com/dxLEYA4.jpg";
        Item ps32 = new Item(PS32, "Standard definition 32 Inch television with DVD", 169.99, tarId, PS32_URL);
        Item ps322 = new Item(PS32, SD_TV, 165.99, costcoId, PS32_URL);
        Item ps323 = new Item(PS32, BN_TV, 189.99, bbId, PS32_URL);
        addItem(ps32);
        addItem(ps322);
        addItem(ps323);
        final String PS24 = "Proscan 24 Inch LED 720p Television with DVD";
        final String PS24_URL = "https://i.imgur.com/ARUiFeR.jpg";
        Item ps24 = new Item(PS24, "Standard definition 24 Inch television with DVD", 179.99, tarId, PS24_URL);
        Item ps242 = new Item(PS24, SD_TV, 175.99, costcoId, PS24_URL);
        Item ps243 = new Item(PS24, BN_TV, 189.99, bbId, PS24_URL);
        addItem(ps24);
        addItem(ps242);
        addItem(ps243);
        final String LG32M = "LG 32 Inch LED 720p Television";
        final String LG32M_URL = "https://i.imgur.com/DMIheL2.jpg";
        Item lg32m = new Item(LG32M, "Standard definition 32 Inch television", 179.99, tarId, LG32M_URL);
        Item lg32m2 = new Item(LG32M, SD_TV, 175.99, costcoId, LG32M_URL);
        Item lg32m3 = new Item(LG32M, BN_TV, 219.99, bbId, LG32M_URL);
        addItem(lg32m);
        addItem(lg32m2);
        addItem(lg32m3);
        final String SS32J = "Samsung 32 Inch LED 720p Television";
        final String SS32J_URL = "https://i.imgur.com/f90IkLg.jpg";
        Item ss32j = new Item(SS32J, "Standard definition 32 Inch television", 179.99, tarId, SS32J_URL);
        Item ss32j2 = new Item(SS32J, SD_TV, 175.99, costcoId, SS32J_URL);
        Item ss32j3 = new Item(SS32J, BN_TV, 189.99, bbId, SS32J_URL);
        addItem(ss32j);
        addItem(ss32j2);
        addItem(ss32j3);
        final String SS586070 = "Samsung 58 Inch 4K Television";
        final String SS586070_URL = "https://i.imgur.com/DThfWsU.jpg";
        Item ss586070 = new Item(SS586070, "Ultra High definition 58 Inch television", 529.99, tarId, SS586070_URL);
        Item ss5860702 = new Item(SS586070, "Ultra-High definition television", 695.99, costcoId, SS586070_URL);
        Item ss5860703 = new Item(SS586070, BN_TV, 750.99, bbId, SS586070_URL);
        addItem(ss586070);
        addItem(ss5860702);
        addItem(ss5860703);
        final String SS556070 = "Samsung 55 Inch Curved 4K Television";
        final String SS556070_URL = "https://i.imgur.com/XjJwUQr.jpg";
        Item ss556070 = new Item(SS556070, "Ultra High definition 55 Inch television", 529.99, tarId, SS556070_URL);
        Item ss5560702 = new Item(SS556070, "Ultra definition television", 695.99, costcoId, SS556070_URL);
        Item ss5560703 = new Item(SS556070, BN_TV, 750.99, bbId, SS556070_URL);
        addItem(ss556070);
        addItem(ss5560702);
        addItem(ss5560703);
        final String SS55607 = "Samsung 55 Inch 4K Television";
        final String SS55607_URL = "https://i.imgur.com/sSRFNVc.jpg";
        Item ss55607 = new Item(SS55607, "Ultra High definition 55 Inch television", 529.99, tarId, SS55607_URL);
        Item ss556072 = new Item(SS55607, "Ultra High definition television", 695.99, costcoId, SS55607_URL);
        Item ss556073 = new Item(SS55607, BN_TV, 750.99, bbId, SS55607_URL);
        addItem(ss55607);
        addItem(ss556072);
        addItem(ss556073);
        final String SS497000 = "Samsung 49 Inch 4K Television";
        final String SS497000_URL = "https://i.imgur.com/bukoiXw.jpg";
        Item ss497000 = new Item(SS497000, "Ultra High definition 49 Inch television", 549.99, tarId, SS497000_URL);
        Item ss4970002 = new Item(SS497000, "Ultra High definition television", 675.99, costcoId, SS497000_URL);
        Item ss4970003 = new Item(SS497000, BN_TV, 745.99, bbId, SS497000_URL);
        addItem(ss497000);
        addItem(ss4970002);
        addItem(ss4970003);
        final String BLARUN = "Blade Runner 2049 4K Blu-ray";
        final String BLARUN_URL = "https://i.imgur.com/6Yzs7T8.jpg";
        final String BLADES = "4K Ultra HD Blu-ray Blade Runner 2049";
        Item blarun = new Item(BLARUN, BLADES, 29.99, tarId, BLARUN_URL);
        Item blarun2 = new Item(BLARUN, BLADES, 29.99, costcoId, BLARUN_URL);
        Item blarun3 = new Item(BLARUN, BLADES, 29.89, bbId, BLARUN_URL);
        addItem(blarun);
        addItem(blarun2);
        addItem(blarun3);
        final String DUNKIRK = "Dunkirk 4K Blu-ray";
        final String DUNKIRK_URL = "https://i.imgur.com/Z68NUxF.jpg";
        final String DUNKIRKDES = "4K Ultra HD Blu-ray Dunkirk";
        Item dunkirk = new Item(DUNKIRK, DUNKIRKDES, 29.99, tarId, DUNKIRK_URL);
        Item dunkirk2 = new Item(DUNKIRK, DUNKIRKDES, 29.99, costcoId, DUNKIRK_URL);
        Item dunkirk3 = new Item(DUNKIRK, DUNKIRKDES, 29.89, bbId, DUNKIRK_URL);
        addItem(dunkirk);
        addItem(dunkirk2);
        addItem(dunkirk3);
        final String KNIGHT = "The Dark Knight 4K Blu-ray";
        final String KNIGHT_URL = "https://i.imgur.com/KYpkFje.jpg";
        final String KNIGHTDES = "4K Ultra HD Blu-ray The Dark Knight";
        Item knight = new Item(KNIGHT, KNIGHTDES, 24.99, tarId, KNIGHT_URL);
        Item knight2 = new Item(KNIGHT, KNIGHTDES, 27.99, costcoId, KNIGHT_URL);
        Item knight3 = new Item(KNIGHT, KNIGHTDES, 29.89, bbId, KNIGHT_URL);
        addItem(knight);
        addItem(knight2);
        addItem(knight3);
        final String THORS = "Thor: Ragnarok Steelbook 4K Blu-ray";
        final String THORS_URL = "https://i.imgur.com/rt6oTvV.jpg";
        final String THORSDES = "4K Ultra HD Blu-ray Thor: Ragnarok Steelbook";
        Item thors = new Item(THORS, THORSDES, 28.99, tarId, THORS_URL);
        Item thors2 = new Item(THORS, THORSDES, 27.99, costcoId, THORS_URL);
        Item thors3 = new Item(THORS, THORSDES, 29.89, bbId, THORS_URL);
        addItem(thors);
        addItem(thors2);
        addItem(thors3);
        final String THOR = "Thor: Ragnarok 4K Blu-ray";
        final String THOR_URL = "https://i.imgur.com/ZJDwgYn.jpg";
        final String THORDES = "4K Ultra HD Blu-ray Thor: Ragnarok";
        Item thor = new Item(THOR, THORDES, 28.99, tarId, THOR_URL);
        Item thor2 = new Item(THOR, THORDES, 27.99, costcoId, THOR_URL);
        Item thor3 = new Item(THOR, THORDES, 29.89, bbId, THOR_URL);
        addItem(thor);
        addItem(thor2);
        addItem(thor3);
        final String SWARS = "Star Wars: The Last Jedi 4K Blu-ray";
        final String SWARS_URL = "https://i.imgur.com/xsudCd5.jpg";
        final String SWARSDES = "4K Ultra HD Blu-ray Star Wars: The Last Jedi";
        Item swars = new Item(SWARS, SWARSDES, 28.99, tarId, SWARS_URL);
        Item swars2 = new Item(SWARS, SWARSDES, 27.99, costcoId, SWARS_URL);
        Item swars3 = new Item(SWARS, SWARSDES, 29.89, bbId, SWARS_URL);
        addItem(swars);
        addItem(swars2);
        addItem(swars3);
        final String DKRISES = "The Dark Knight Rises 4K Blu-ray";
        final String DKRISES_URL = "https://i.imgur.com/zI0h1ah.jpg";
        final String DKRISESDES = "4K Ultra HD Blu-ray The Dark Knight Rises";
        Item dkrises = new Item(DKRISES, DKRISESDES, 24.99, tarId, DKRISES_URL);
        Item dkrises2 = new Item(DKRISES, DKRISESDES, 23.99, costcoId, DKRISES_URL);
        Item dkrises3 = new Item(DKRISES, DKRISESDES, 29.89, bbId, DKRISES_URL);
        addItem(dkrises);
        addItem(dkrises2);
        addItem(dkrises3);
        final String COCO = "Coco 4K Blu-ray";
        final String COCO_URL = "https://i.imgur.com/5T2omd8.jpg";
        final String COCODES = "4K Ultra HD Blu-ray Coco";
        Item coco = new Item(COCO, COCODES, 28.99, tarId, COCO_URL);
        Item coco2 = new Item(COCO, COCODES, 27.99, costcoId, COCO_URL);
        Item coco3 = new Item(COCO, COCODES, 29.89, bbId, COCO_URL);
        addItem(coco);
        addItem(coco2);
        addItem(coco3);
        final String BRFC = "Blade Runner: The Final Cut 4K Blu-ray";
        final String BRFC_URL = "https://i.imgur.com/XBJEJvt.jpg";
        final String BRFCDES = "4K Ultra HD Blu-ray Blade Runner: The Final Cut";
        Item brfc = new Item(BRFC, BRFCDES, 24.99, tarId, BRFC_URL);
        Item brfc2 = new Item(BRFC, BRFCDES, 23.99, costcoId, BRFC_URL);
        Item brfc3 = new Item(BRFC, BRFCDES, 24.89, bbId, BRFC_URL);
        addItem(brfc);
        addItem(brfc2);
        addItem(brfc3);
        final String BATBEG = "Batman Begins 4K Blu-ray";
        final String BATBEG_URL = "https://i.imgur.com/veHlJqH.jpg";
        final String BATBEGDES = "4K Ultra HD Blu-ray Batman Begins";
        Item batbeg = new Item(BATBEG, BATBEGDES, 24.99, tarId, BATBEG_URL);
        Item batbeg2 = new Item(BATBEG, BATBEGDES, 23.99, costcoId, BATBEG_URL);
        Item batbeg3 = new Item(BATBEG, BATBEGDES, 27.89, bbId, BATBEG_URL);
        addItem(batbeg);
        addItem(batbeg2);
        addItem(batbeg3);
        final String JLS = "Justice League Steelbook 4K Blu-ray";
        final String JLS_URL = "https://i.imgur.com/MDT1dJH.jpg";
        final String JLSDES = "4K Ultra HD Blu-ray Justice League Steelbook";
        Item jls = new Item(JLS, JLSDES, 34.99, tarId, JLS_URL);
        Item jls2 = new Item(JLS, JLSDES, 33.99, costcoId, JLS_URL);
        Item jls3 = new Item(JLS, JLSDES, 37.89, bbId, JLS_URL);
        addItem(jls);
        addItem(jls2);
        addItem(jls3);
        final String MMAX = "Mad Max: Fury Road 4K Blu-ray";
        final String MMAX_URL = "https://i.imgur.com/ULxm3z5.jpg";
        final String MMAXDES = "4K Ultra HD Blu-ray Mad Max: Fury Road";
        Item mmax = new Item(MMAX, MMAXDES, 22.99, tarId, MMAX_URL);
        Item mmax2 = new Item(MMAX, MMAXDES, 23.99, costcoId, MMAX_URL);
        Item mmax3 = new Item(MMAX, MMAXDES, 27.89, bbId, MMAX_URL);
        addItem(mmax);
        addItem(mmax2);
        addItem(mmax3);
        final String INCEP = "Inception 4K Blu-ray";
        final String INCEP_URL = "https://i.imgur.com/zalKixs.jpg";
        final String INCEPDES = "4K Ultra HD Blu-ray Inception";
        Item incep = new Item(INCEP, INCEPDES, 24.99, tarId, INCEP_URL);
        Item incep2 = new Item(INCEP, INCEPDES, 23.99, costcoId, INCEP_URL);
        Item incep3 = new Item(INCEP, INCEPDES, 27.89, bbId, INCEP_URL);
        addItem(incep);
        addItem(incep2);
        addItem(incep3);
        final String WW = "Wonder Woman 4K Blu-ray";
        final String WW_URL = "https://i.imgur.com/kEUcOLN.jpg";
        final String WWDES = "4K Ultra HD Blu-ray Wonder Woman";
        Item ww = new Item(WW, WWDES, 22.99, tarId, WW_URL);
        Item ww2 = new Item(WW, WWDES, 23.99, costcoId, WW_URL);
        Item ww3 = new Item(WW, WWDES, 21.89, bbId, WW_URL);
        addItem(ww);
        addItem(ww2);
        addItem(ww3);
        final String PRIM = "Pacific Rim 4K Blu-ray";
        final String PRIM_URL = "https://i.imgur.com/YETTC2p.jpg";
        final String PRIMDES = "4K Ultra HD Blu-ray Pacific Rim";
        Item prim = new Item(PRIM, PRIMDES, 22.99, tarId, PRIM_URL);
        Item prim2 = new Item(PRIM, PRIMDES, 23.99, costcoId, PRIM_URL);
        Item prim3 = new Item(PRIM, PRIMDES, 21.89, bbId, PRIM_URL);
        addItem(prim);
        addItem(prim2);
        addItem(prim3);
        final String JUMANJI = "Jumanji: Welcome to the Jungle 4K Blu-ray";
        final String JUMANJI_URL = "https://i.imgur.com/RIXBoiS.jpg";
        final String JUMANJIDES = "4K Ultra HD Blu-ray Jumanji: Welcome to the Jungle";
        Item jumanji = new Item(JUMANJI, JUMANJIDES, 34.99, tarId, JUMANJI_URL);
        Item jumanji2 = new Item(JUMANJI, JUMANJIDES, 33.99, costcoId, JUMANJI_URL);
        Item jumanji3 = new Item(JUMANJI, JUMANJIDES, 31.89, bbId, JUMANJI_URL);
        addItem(jumanji);
        addItem(jumanji2);
        addItem(jumanji3);
        final String WATCHMEN = "Watchmen 4K Blu-ray";
        final String WATCHMEN_URL = "https://i.imgur.com/C1Uas8u.jpg";
        final String WATCHMENDES = "4K Ultra HD Blu-ray Watchmen";
        Item watchmen = new Item(WATCHMEN, WATCHMENDES, 24.99, tarId, WATCHMEN_URL);
        Item watchmen2 = new Item(WATCHMEN, WATCHMENDES, 23.99, costcoId, WATCHMEN_URL);
        Item watchmen3 = new Item(WATCHMEN, WATCHMENDES, 21.89, bbId, WATCHMEN_URL);
        addItem(watchmen);
        addItem(watchmen2);
        addItem(watchmen3);
        final String JUSTICE = "Justice League 4K Blu-ray";
        final String JUSTICE_URL = "https://i.imgur.com/2vf8jop.jpg";
        final String JUSTICEDES = "4K Ultra HD Blu-ray Justice League";
        Item justice = new Item(JUSTICE, JUSTICEDES, 29.99, tarId, JUSTICE_URL);
        Item justice2 = new Item(JUSTICE, JUSTICEDES, 28.99, costcoId, JUSTICE_URL);
        Item justice3 = new Item(JUSTICE, JUSTICEDES, 31.89, bbId, JUSTICE_URL);
        addItem(justice);
        addItem(justice2);
        addItem(justice3);
        final String HP = "Hewlet Packard Enterprises";
        final String HP_URL = "https://i.imgur.com/tkqNF6L.jpg";
        final String HPDES = "The Entire Corporation of HP";
        Item hp = new Item(HP, HPDES, 50000.00, tarId, HP_URL);
        Item hp2 = new Item(HP, HPDES, 100000.00, costcoId, HP_URL);
        Item hp3 = new Item(HP, HPDES, 49999.99, wfId, HP_URL);
        addItem(hp);
        addItem(hp2);
        addItem(hp3);
        final String KONG = "Kong: Skull Island 4K Blu-ray";
        final String KONG_URL = "https://i.imgur.com/mrEa8XA.jpg";
        final String KONGDES = "4K Ultra HD Blu-ray Kong: Skull Island";
        Item kong = new Item(KONG, KONGDES, 22.99, tarId, KONG_URL);
        Item kong2 = new Item(KONG, KONGDES, 18.99, costcoId, KONG_URL);
        Item kong3 = new Item(KONG, KONGDES, 21.89, bbId, KONG_URL);
        addItem(kong);
        addItem(kong2);
        addItem(kong3);
        final String REV = "The Revenant 4K Blu-ray";
        final String REV_URL = "https://i.imgur.com/i4jQeJq.jpg";
        final String REVDES = "4K Ultra HD Blu-ray The Revenant";
        Item rev = new Item(REV, REVDES, 22.99, tarId, REV_URL);
        Item rev2 = new Item(REV, REVDES, 18.99, costcoId, REV_URL);
        Item rev3 = new Item(REV, REVDES, 21.89, bbId, REV_URL);
        addItem(rev);
        addItem(rev2);
        addItem(rev3);
        final String PLANET = "Blue Planet II 4K Blu-ray";
        final String PLANET_URL = "https://i.imgur.com/jV6O8Y3.jpg";
        final String PLANETDES = "4K Ultra HD Blu-ray Blue Planet II";
        Item planet = new Item(PLANET, PLANETDES, 32.99, tarId, PLANET_URL);
        Item planet2 = new Item(PLANET, PLANETDES, 38.99, costcoId, PLANET_URL);
        Item planet3 = new Item(PLANET, PLANETDES, 31.89, bbId, PLANET_URL);
        addItem(planet);
        addItem(planet2);
        addItem(planet3);
        final String SPARKLE = "Sparkle Paper Towels";
        final String SPARKLE_URL = "https://i.imgur.com/XlOD3Ju.jpg";
        final String SPARKLEDES = "6 Big Rolls";
        Item sparkle = new Item(SPARKLE, SPARKLEDES, 5.99, tarId, SPARKLE_URL);
        Item sparkle2 = new Item(SPARKLE, SPARKLEDES, 4.99, costcoId, SPARKLE_URL);
        Item sparkle3 = new Item(SPARKLE, SPARKLEDES, 4.89, wfId, SPARKLE_URL);
        addItem(sparkle);
        addItem(sparkle2);
        addItem(sparkle3);
        final String BRAWNY = "Brawny Paper Towels";
        final String BRAWNY_URL = "https://i.imgur.com/ReYfeTE.jpg";
        final String BRAWNYDES = "6 Large Rolls";
        Item brawny = new Item(BRAWNY, BRAWNYDES, 7.99, tarId, BRAWNY_URL);
        Item brawny2 = new Item(BRAWNY, BRAWNYDES, 8.99, costcoId, BRAWNY_URL);
        Item brawny3 = new Item(BRAWNY, BRAWNYDES, 7.89, wfId, BRAWNY_URL);
        addItem(brawny);
        addItem(brawny2);
        addItem(brawny3);
        final String QUILTED = "Quilted Northern Ultra Soft Toilet Paper";
        final String QUILTED_URL = "https://i.imgur.com/nAsw1Ur.jpg";
        final String QUILTEDDES = "12 Pack Double Rolls";
        Item quilted = new Item(QUILTED, QUILTEDDES, 7.99, tarId, QUILTED_URL);
        Item quilted2 = new Item(QUILTED, QUILTEDDES, 6.99, costcoId, QUILTED_URL);
        Item quilted3 = new Item(QUILTED, QUILTEDDES, 6.89, wfId, QUILTED_URL);
        addItem(quilted);
        addItem(quilted2);
        addItem(quilted3);
        final String CLOROX = "Clorox Disinfecting Wipes";
        final String CLOROX_URL = "https://i.imgur.com/ReYfeTE.jpg";
        final String CLOROXDES = "2 Pack Crisp Lemon and Fresh Scent";
        Item clorox = new Item(CLOROX, CLOROXDES, 7.99, tarId, CLOROX_URL);
        Item clorox2 = new Item(CLOROX, CLOROXDES, 8.99, costcoId, CLOROX_URL);
        Item clorox3 = new Item(CLOROX, CLOROXDES, 8.89, wfId, CLOROX_URL);
        addItem(clorox);
        addItem(clorox2);
        addItem(clorox3);
        final String WINDEX = "Windex Original Cleaner";
        final String WINDEX_URL = "https://i.imgur.com/4eQV5gy.jpg";
        final String WINDEXDES = "Windex Glass Cleaner 23 fl oz.";
        Item windex = new Item(WINDEX, WINDEXDES, 2.63, tarId, WINDEX_URL);
        Item windex2 = new Item(WINDEX, WINDEXDES, 2.99, costcoId, WINDEX_URL);
        Item windex3 = new Item(WINDEX, WINDEXDES, 1.89, wfId, WINDEX_URL);
        addItem(windex);
        addItem(windex2);
        addItem(windex3);
        final String SCOTCHBRITE = "Scotch-Brite Scrub Sponge";
        final String SCOTCHBRITE_URL = "https://i.imgur.com/YL7zUfv.jpg";
        final String SCOTCHBRITEDES = "3 Sponges";
        Item scotchbrite = new Item(SCOTCHBRITE, SCOTCHBRITEDES, 2.63, tarId, SCOTCHBRITE_URL);
        Item scotchbrite2 = new Item(SCOTCHBRITE, SCOTCHBRITEDES, 2.99, costcoId, SCOTCHBRITE_URL);
        Item scotchbrite3 = new Item(SCOTCHBRITE, SCOTCHBRITEDES, 1.89, wfId, SCOTCHBRITE_URL);
        addItem(scotchbrite);
        addItem(scotchbrite2);
        addItem(scotchbrite3);
        final String CLOROXSP = "Clorox Disinfecting Bathroom Cleaner";
        final String CLOROXSP_URL = "https://i.imgur.com/gEhW377.jpg";
        final String CLOROXSPDES = "30 Ounce Spray Bottle";
        Item cloroxsp = new Item(CLOROXSP, CLOROXSPDES, 3.63, tarId, CLOROXSP_URL);
        Item cloroxsp2 = new Item(CLOROXSP, CLOROXSPDES, 3.99, costcoId, CLOROXSP_URL);
        Item cloroxsp3 = new Item(CLOROXSP, CLOROXSPDES, 2.89, wfId, CLOROXSP_URL);
        addItem(cloroxsp);
        addItem(cloroxsp2);
        addItem(cloroxsp3);
        final String SWIFFER = "Swiffer Sweeper Wet Mopping Pad";
        final String SWIFFER_URL = "https://i.imgur.com/9LhNBNP.jpg";
        final String SWIFFERDES = "24 Count Refills";
        Item swiffer = new Item(SWIFFER, SWIFFERDES, 7.63, tarId, SWIFFER_URL);
        Item swiffer2 = new Item(SWIFFER, SWIFFERDES, 7.99, costcoId, SWIFFER_URL);
        Item swiffer3 = new Item(SWIFFER, SWIFFERDES, 8.89, wfId, SWIFFER_URL);
        addItem(swiffer);
        addItem(swiffer2);
        addItem(swiffer3);
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
        for(Store s: storesMap.values()){
            String lkey = s.getStoreName().toLowerCase();
            if(lkey.contains(sTerm) || sTerm.contains(lkey)){
                stores.add(s);
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
            if(name.contains(sTerm) || sTerm.contains(name) || descContains(sTerm, i.getDescription())){
                results.add(i);
            }
        }
        return results;
    }

    private boolean descContains(String term, String desc) {
        String[] descWords = desc.split(" ");
        for(String word: descWords) {
            String w = word.toLowerCase();
            if(term.equals(w) || w.contains(term))
                return true;
        }
        return false;
    }
}
