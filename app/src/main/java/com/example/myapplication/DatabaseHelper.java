package com.example.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userDatabase.db";
    private static final int DATABASE_VERSION = 2; // Increment the database version

    // User table
    public static final String TABLE_USER = "user";
    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_USERNAME = "username";
    public static final String COLUMN_USER_PASSWORD = "password";
    public static final String COLUMN_USER_FIRSTNAME = "firstname";
    public static final String COLUMN_USER_LASTNAME = "lastname";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_DEPARTMENT = "department";
    public static final String COLUMN_USER_SALARY = "salary";
    public static final String COLUMN_USER_LEAVES = "leaves";

    // Admin table
    public static final String TABLE_ADMIN = "admin";
    public static final String COLUMN_ADMIN_ID = "id";
    public static final String COLUMN_ADMIN_USERNAME = "username";
    public static final String COLUMN_ADMIN_PASSWORD = "password";

    // Create table SQL queries
    private static final String TABLE_USER_CREATE =
            "CREATE TABLE " + TABLE_USER + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_USERNAME + " TEXT, " +
                    COLUMN_USER_PASSWORD + " TEXT, " +
                    COLUMN_USER_FIRSTNAME + " TEXT, " +
                    COLUMN_USER_LASTNAME + " TEXT, " +
                    COLUMN_USER_EMAIL + " TEXT, " +
                    COLUMN_USER_DEPARTMENT + " TEXT, " +
                    COLUMN_USER_SALARY + " TEXT, " +
                    COLUMN_USER_LEAVES + " INTEGER);";

    private static final String TABLE_ADMIN_CREATE =
            "CREATE TABLE " + TABLE_ADMIN + " (" +
                    COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ADMIN_USERNAME + " TEXT, " +
                    COLUMN_ADMIN_PASSWORD + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USER_CREATE);
        db.execSQL(TABLE_ADMIN_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        onCreate(db);
    }
}