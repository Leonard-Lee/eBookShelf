package com.example.leonardlee.ebookshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by leonardlee on 04/04/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "ProductDB.db";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_TABLE_NAME = "products";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_DESCRIPTION = "description";
    public static final String CONTACTS_COLUMN_PRICE = "price";
    public static final String CONTACTS_COLUMN_REVIEW = "review";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
            "CREATE TABLE products " +
            "(id integer primary key, name text, description text, price text, review text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS products");
        onCreate(db);
    }

    public boolean insertContact (String name, String description, String price, String review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("description", description);
        contentValues.put("price", price);
        contentValues.put("review", review);
        db.insert("products", null, contentValues);
        return true;
    }

    public Cursor getData(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT name, description, price, review "
                                    + "FROM products "
                                    + "WHERE name LIKE '%"+ name +"%'",
                                    null);
        return res;
    }

}
