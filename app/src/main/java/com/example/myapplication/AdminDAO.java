package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class AdminDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public AdminDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addAdmin(String username, String password) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ADMIN_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_ADMIN_PASSWORD, password);
        return database.insert(DatabaseHelper.TABLE_ADMIN, null, values);
    }

    public boolean checkAdmin(String username, String password) {
        String[] columns = {DatabaseHelper.COLUMN_ADMIN_ID};
        String selection = DatabaseHelper.COLUMN_ADMIN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_ADMIN_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query(DatabaseHelper.TABLE_ADMIN, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        return cursorCount > 0;
    }
}