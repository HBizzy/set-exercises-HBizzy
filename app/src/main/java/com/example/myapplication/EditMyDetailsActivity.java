package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMyDetailsActivity extends Activity {

    private UserDAO userDAO;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText departmentEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_my_details);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        departmentEditText = findViewById(R.id.departmentEditText);
        saveButton = findViewById(R.id.saveButton);

        userDAO = new UserDAO(this);
        userDAO.open();

        String username = getIntent().getStringExtra("username");

        if (username != null) {
            loadUserDetails(username);
        } else {
            // Handle the case where username is null
        }

        saveButton.setOnClickListener(v -> {
            if (username != null) {
                saveUserDetails(username);
            }
        });
    }

    private void loadUserDetails(String username) {
        String[] columns = {
                DatabaseHelper.COLUMN_USER_FIRSTNAME,
                DatabaseHelper.COLUMN_USER_LASTNAME,
                DatabaseHelper.COLUMN_USER_EMAIL,
                DatabaseHelper.COLUMN_USER_DEPARTMENT,

        };
        String selection = DatabaseHelper.COLUMN_USER_USERNAME + " = ?";
        String[] selectionArgs = {username};

        Cursor cursor = userDAO.getReadableDatabase().query(
                DatabaseHelper.TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") String firstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_FIRSTNAME));
            @SuppressLint("Range") String lastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_LASTNAME));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_EMAIL));
            @SuppressLint("Range") String department = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_DEPARTMENT));


            firstNameEditText.setText(firstName);
            lastNameEditText.setText(lastName);
            emailEditText.setText(email);
            departmentEditText.setText(department);


            cursor.close();
        } else {
            // Handle the case where the user is not found
        }
    }

    private void saveUserDetails(String username) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USER_FIRSTNAME, firstNameEditText.getText().toString());
        values.put(DatabaseHelper.COLUMN_USER_LASTNAME, lastNameEditText.getText().toString());
        values.put(DatabaseHelper.COLUMN_USER_EMAIL, emailEditText.getText().toString());
        values.put(DatabaseHelper.COLUMN_USER_DEPARTMENT, departmentEditText.getText().toString());


        String selection = DatabaseHelper.COLUMN_USER_USERNAME + " = ?";
        String[] selectionArgs = {username};

        int count = userDAO.getWritableDatabase().update(
                DatabaseHelper.TABLE_USER,
                values,
                selection,
                selectionArgs
        );

        if (count > 0) {
            Toast.makeText(this, "Details updated successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to update details", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDAO.close();
    }
}