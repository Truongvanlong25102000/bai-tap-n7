package com.example.btn7.view.Utility;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StudentReaderDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Student.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + StudentReaderContract.StudentEntry.TABLE_NAME
            + " (" + StudentReaderContract.StudentEntry.COLUMN_ID + " INTEGER PRIMARY KEY,"
            + StudentReaderContract.StudentEntry.COLUMN_NAME_TITLE + " TEXT,"
            + StudentReaderContract.StudentEntry.COLUMN_CLASS_TITLE + " TEXT)";

    public StudentReaderDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_CREATE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        onUpgrade(db, oldVersion, newVersion);
    }
}
