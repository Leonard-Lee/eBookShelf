package com.example.leonardlee.ebookshelf;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leonardlee on 04/04/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "BookDB.db";
    public static final String BOOK_TABLE_NAME = "books";

    public static final String BOOK_COLUMN_ID = "id";
    public static final String BOOK_COLUMN_NAME = "name";
    public static final String BOOK_COLUMN_PATH = "path";
    public static final String BOOK_COLUMN_SIZE = "size";
    public static final String BOOK_COLUMN_LONGITUDE = "longitude";
    public static final String BOOK_COLUMN_LATITUDE = "latitude";
    public static final String BOOK_COLUMN_SAVE_TIME = "save_time";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOOK_TABLE_SQL = "CREATE TABLE " + BOOK_TABLE_NAME + ""
                + BOOK_COLUMN_ID + " INTEGER PRIMARY KEY,"
                + BOOK_COLUMN_NAME + " TEXT,"
                + BOOK_COLUMN_PATH + " TEXT,"
                + BOOK_COLUMN_SIZE + " INTEGER,"
                + BOOK_COLUMN_LONGITUDE + " REAL,"
                + BOOK_COLUMN_LATITUDE + " REAL,"
                + BOOK_COLUMN_SAVE_TIME + " TEXT,"
                + ")";
        db.execSQL(CREATE_BOOK_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOOK_TABLE_NAME);
        onCreate(db);
    }

    public void addBook (Book book) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(BOOK_COLUMN_NAME, book.getBookname());
        contentValues.put(BOOK_COLUMN_PATH, book.getPath());
        contentValues.put(BOOK_COLUMN_SIZE, book.getSize());
        contentValues.put(BOOK_COLUMN_LONGITUDE, book.getLongitude());
        contentValues.put(BOOK_COLUMN_LATITUDE, book.getLatitude());
        contentValues.put(BOOK_COLUMN_SAVE_TIME, book.formatDateTime2Str());

        db.insert(BOOK_TABLE_NAME, null, contentValues);
        db.close();
    }

    // query one book by id
    public Book getBook(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String strQuerySQL = "SELECT " + BOOK_TABLE_NAME+ ", " + BOOK_COLUMN_PATH
                            + ", " + BOOK_COLUMN_SIZE + ", " + BOOK_COLUMN_LONGITUDE
                            + ", " + BOOK_COLUMN_LATITUDE + ", " + BOOK_COLUMN_SAVE_TIME + " "
                            + "FROM " + BOOK_TABLE_NAME + " "
                            + "WHERE " + BOOK_COLUMN_ID + " = " + String.valueOf(id);

        Cursor cursor =  db.rawQuery( strQuerySQL, null);
        if (cursor != null)
            cursor.moveToFirst();

        Book book = new Book(cursor.getString(0),
                cursor.getString(1),
                Integer.parseInt(cursor.getString(2)),
                Float.parseFloat(cursor.getString(3)),
                Float.parseFloat(cursor.getString(4)),
                cursor.getString(5)
        );
        return book;
    }

    // query all books
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String strQuerySQL = "SELECT " + BOOK_TABLE_NAME+ ", "
                + BOOK_COLUMN_PATH + ", "
                + BOOK_COLUMN_SIZE + ", "
                + BOOK_COLUMN_LONGITUDE + ", "
                + BOOK_COLUMN_LATITUDE + ", "
                + BOOK_COLUMN_SAVE_TIME + " "
                + "FROM " + BOOK_TABLE_NAME + " ";

        Cursor cursor = db.rawQuery(strQuerySQL, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Book book = new Book(cursor.getString(0),
                        cursor.getString(1),
                        Integer.parseInt(cursor.getString(2)),
                        Float.parseFloat(cursor.getString(3)),
                        Float.parseFloat(cursor.getString(4)),
                        cursor.getString(5)
                );
                // Adding contact to list
                books.add(book);
            } while (cursor.moveToNext());
        }
        // return contact list
        return books;
    }

    // deleting a book
    public void deleteBook(Book book) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(BOOK_TABLE_NAME, BOOK_COLUMN_ID + " = ?",
                new String[] {
                        String.valueOf(book.getID())
                }
        );
        db.close();
    }



}
