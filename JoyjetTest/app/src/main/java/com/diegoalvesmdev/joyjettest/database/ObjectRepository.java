package com.diegoalvesmdev.joyjettest.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.diegoalvesmdev.joyjettest.model.Object;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegoalves on 03/02/2017.
 */

public class ObjectRepository {

    private ObjectSQLHelper helper;
    private SQLiteDatabase db;

    public ObjectRepository(Context context) {
        this.helper = new ObjectSQLHelper(context);

    }

    public void insert(Object object) {
        db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ObjectSQLHelper.NAME, object.getName());
        contentValues.put(ObjectSQLHelper.DESCRIPTION, object.getDescription());
        contentValues.put(ObjectSQLHelper.PHOTO, object.getPhoto());
        contentValues.put(ObjectSQLHelper.CATEGORY, object.getCategory());
        contentValues.put(ObjectSQLHelper.FAVORITE, object.getFavorite());

        db.insert(ObjectSQLHelper.TABLE, null, contentValues);
        db.close();
    }

    public List<Object> findByCategory(String categoryArg) {
        db = helper.getWritableDatabase();

        String args[] = new String[]{categoryArg};
        String sql = "SELECT * FROM " + ObjectSQLHelper.TABLE + " WHERE " + ObjectSQLHelper.CATEGORY + " LIKE ?";

        Cursor cursor = db.rawQuery(sql, args);

        List<Object> objects = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ObjectSQLHelper.ID));
            String name = cursor.getString(cursor.getColumnIndex(ObjectSQLHelper.NAME));
            String description = cursor.getString(cursor.getColumnIndex(ObjectSQLHelper.DESCRIPTION));
            String photo = cursor.getString(cursor.getColumnIndex(ObjectSQLHelper.PHOTO));
            String category = cursor.getString(cursor.getColumnIndex(ObjectSQLHelper.CATEGORY));
            int favorite = cursor.getInt(cursor.getColumnIndex(ObjectSQLHelper.FAVORITE));

            Object object = new Object(id, name, description, photo, category, favorite);

            objects.add(object);
        }
        cursor.close();
        db.close();

        return objects;
    }

    public List<Object> findFavorites() {
        db = helper.getWritableDatabase();

        String args[] = new String[]{String.valueOf(1)};
        String sql = "SELECT * FROM " + ObjectSQLHelper.TABLE + " WHERE " + ObjectSQLHelper.FAVORITE + " = ?";

        Cursor cursor = db.rawQuery(sql, args);

        List<Object> objects = new ArrayList<>();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(ObjectSQLHelper.ID));
            String name = cursor.getString(cursor.getColumnIndex(ObjectSQLHelper.NAME));
            String description = cursor.getString(cursor.getColumnIndex(ObjectSQLHelper.DESCRIPTION));
            String photo = cursor.getString(cursor.getColumnIndex(ObjectSQLHelper.PHOTO));
            String category = cursor.getString(cursor.getColumnIndex(ObjectSQLHelper.CATEGORY));
            int favorite = cursor.getInt(cursor.getColumnIndex(ObjectSQLHelper.FAVORITE));

            Object object = new Object(id, name, description, photo, category, favorite);

            objects.add(object);
        }
        cursor.close();
        db.close();

        return objects;
    }

    public void update(Object object) {
        db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ObjectSQLHelper.FAVORITE, object.getFavorite());

        db.update(ObjectSQLHelper.TABLE, contentValues, ObjectSQLHelper.ID + " = ?", new String[]{String.valueOf(object.getId())});
        db.close();
    }

}
