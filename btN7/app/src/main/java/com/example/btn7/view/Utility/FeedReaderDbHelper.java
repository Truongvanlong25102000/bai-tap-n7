package com.example.btn7.view.Utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FeedReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME
            + " (" + FeedReaderContract.FeedEntry.COLUMN_ID + " INTEGER PRIMARY KEY,"
            + FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " TEXT,"
            + FeedReaderContract.FeedEntry.COLUMN_PHONE_TITLE + " TEXT,"
            + FeedReaderContract.FeedEntry.COLUMN_STREET_TITLE + " TEXT,"
            + FeedReaderContract.FeedEntry.COLUMN_EMAIL_TITLE + " TEXT,"
            + FeedReaderContract.FeedEntry.COLUMN_ADDRESS_TITLE + " TEXT)";

    public FeedReaderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //tao moi database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    //update version database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_CREATE_ENTRIES);
        onCreate(db);
    }

    //tro ve version database cu hon
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        onUpgrade(db, oldVersion, newVersion);
    }
}
