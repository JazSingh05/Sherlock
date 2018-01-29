package com.example.android.sherlock;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Abbinav, Jaz on 1/28/2018.
 */

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "items";
    private static final String TABLE_ITEMS = "store";

    private static final String ID = "id";
    private static final String NAME = "storeName";
    private static final String PRODUCTID = "productID";
    private static final String PRODUCTNAME = "productNAME";
    private static final String PRODUCTPICTURE = "productPicture";
    private static final String PRICE = "price";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_STORE_TABLE = "CREATE TABLE" + TABLE_ITEMS + "(" + ID + "INTEGER PRIMARY KEY," +
                                    NAME + "VARCHAR" + PRODUCTID + "INTEGER PRIMARY KEY" + PRODUCTNAME + "VARCHAR"
                                    + PRODUCTPICTURE + "BLOB" + PRICE + "DOUBLE" + ")";

        sqLiteDatabase.execSQL(CREATE_STORE_TABLE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_ITEMS);
        onCreate(sqLiteDatabase);

    }

    public void addStore(Store store){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ID, store.getId());
        values.put(NAME, store.getStoreName());
        values.put(PRODUCTID, store.getProductId());
        values.put(PRODUCTNAME, store.getProductName());
      //  values.put(PRODUCTPICTURE, String.valueOf(store.getProductPicture()));
        values.put(PRICE, store.getPrice());

        db.insert(TABLE_ITEMS, null, values);
        db.close();
    }
}
