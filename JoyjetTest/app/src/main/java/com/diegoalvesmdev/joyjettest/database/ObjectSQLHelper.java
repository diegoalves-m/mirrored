package com.diegoalvesmdev.joyjettest.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by diegoalves on 03/02/2017.
 */

public class ObjectSQLHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "database";
    private static final int VERSION = 1;

    public static final String TABLE = "object";

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String PHOTO = "photo";
    public static final String CATEGORY = "category";
    public static final String FAVORITE = "favorite";

    public ObjectSQLHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NAME + " TEXT," +
                DESCRIPTION + " TEXT," +
                PHOTO + " TEXT," +
                CATEGORY + " TEXT," +
                FAVORITE + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
