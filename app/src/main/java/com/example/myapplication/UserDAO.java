package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserDAO {

    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    public UserDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public SQLiteDatabase getWritableDatabase() {
        return dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadableDatabase() {
        return dbHelper.getReadableDatabase();
    }

    public long addUser(String username, String password, String firstName, String lastName, String email, String department, String salary, int leaves) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USER_USERNAME, username);
        values.put(DatabaseHelper.COLUMN_USER_PASSWORD, password);
        values.put(DatabaseHelper.COLUMN_USER_FIRSTNAME, firstName);
        values.put(DatabaseHelper.COLUMN_USER_LASTNAME, lastName);
        values.put(DatabaseHelper.COLUMN_USER_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_USER_DEPARTMENT, department);
        values.put(DatabaseHelper.COLUMN_USER_SALARY, salary);
        values.put(DatabaseHelper.COLUMN_USER_LEAVES, leaves);
        return database.insert(DatabaseHelper.TABLE_USER, null, values);
    }

    public boolean checkUser(String username, String password) {
        String[] columns = {DatabaseHelper.COLUMN_USER_ID};
        String selection = DatabaseHelper.COLUMN_USER_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = database.query(DatabaseHelper.TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        return cursorCount > 0;
    }

    public Cursor getUserDetails(String username) {
        String[] columns = {
                DatabaseHelper.COLUMN_USER_FIRSTNAME,
                DatabaseHelper.COLUMN_USER_LASTNAME,
                DatabaseHelper.COLUMN_USER_EMAIL,
                DatabaseHelper.COLUMN_USER_DEPARTMENT,
                DatabaseHelper.COLUMN_USER_SALARY,
                DatabaseHelper.COLUMN_USER_LEAVES
        };
        String selection = DatabaseHelper.COLUMN_USER_USERNAME + " = ?";
        String[] selectionArgs = {username};
        return database.query(DatabaseHelper.TABLE_USER, columns, selection, selectionArgs, null, null, null);
    }

    public int updateUserDetails(String username, String firstName, String lastName, String email, String department, String salary, int leaves) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USER_FIRSTNAME, firstName);
        values.put(DatabaseHelper.COLUMN_USER_LASTNAME, lastName);
        values.put(DatabaseHelper.COLUMN_USER_EMAIL, email);
        values.put(DatabaseHelper.COLUMN_USER_DEPARTMENT, department);
        values.put(DatabaseHelper.COLUMN_USER_SALARY, salary);
        values.put(DatabaseHelper.COLUMN_USER_LEAVES, leaves);

        String selection = DatabaseHelper.COLUMN_USER_USERNAME + " = ?";
        String[] selectionArgs = {username};

        return database.update(DatabaseHelper.TABLE_USER, values, selection, selectionArgs);
    }
}