package com.example.android.sherlock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Abbinav, Jaz on 1/28/2018.
 */

public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "items";
    private static final String TABLE_ITEMS = "store";

    private static final String ID = "id";
    private static final String NAME = "storeName";
    private static final String ADDRESS = "address";
    private static final String PRODUCTNAME = "productNAME";
    private static final String PRODUCTPICTURE = "productPicture";
    private static final String PRICE = "price";


    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        String CREATE_STORE_TABLE = "CREATE TABLE " + TABLE_ITEMS + "(" + ID + " INTEGER," +
                                    NAME + " VARCHAR," + ADDRESS + " VARCHAR," + PRODUCTNAME + " VARCHAR,"
                                    + PRICE + " DOUBLE," + " PRIMARY KEY" + "(" + ID +"," + NAME +
                                    "," + ADDRESS + "," + PRODUCTNAME + "," + PRICE + ") " +
                                    ")";

        sqLiteDatabase.execSQL(CREATE_STORE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(sqLiteDatabase);
    }

    public void addTestData() {
        Log.d("DATABASE", "adding test data to the database");
        for(int i=0; i< 10; i++) {
            ContentValues values = new ContentValues();
            values.put(ID, i);
            values.put(NAME, "Costco" + i);
            values.put(ADDRESS, i + " Fake Street");
            values.put(PRODUCTNAME, "apple" + i);
            values.put(PRICE, i+".99");
            addStore(values);
        }
    }

    public void addStore(ContentValues data){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(TABLE_ITEMS, null, data);
        Log.d("STORAGE_CHECK", "insertdata: Added " +   " to " + "\nResult = " + result);
        db.close();
    }

    public boolean hasData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {ID, NAME, ADDRESS, PRODUCTNAME, PRICE};
        Cursor cursor = db.query(TABLE_ITEMS, projection, null, null, null, null, null);
        return cursor.getCount() > 0;
    }

    public ArrayList<String> searchQuery(String s)
    {

        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {ID, NAME, ADDRESS, PRODUCTNAME, PRICE};

        Cursor cursor = db.query(TABLE_ITEMS, projection, null, null, null, null, null);
        cursor.moveToLast();

        int index = 6;
        ArrayList<String> queries = new ArrayList<String>();

        for(int i = 0; i < 7; i++)
        {
            String temp = "";
            for(int j = 0; j < 5; j++)
            {
                temp = temp + " " +cursor.getString(j);
            }

            index--;
            cursor.moveToPosition(index);

            queries.add(temp);
        }

        for(int i = 0; i < 7; i++)
        {
            Log.d("RECIEVED: ", "TEST = " + queries.get(i));
        }

        return queries;
    }
}
