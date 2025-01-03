package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

public class ViewMyEmployeeDetailsActivity extends Activity {

    private UserDAO userDAO;
    private TextView firstNameTextView;
    private TextView lastNameTextView;
    private TextView emailTextView;
    private TextView departmentTextView;
    private TextView salaryTextView;
    private TextView leavesTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_my_details);

        firstNameTextView = findViewById(R.id.firstNameTextView);
        lastNameTextView = findViewById(R.id.lastNameTextView);
        emailTextView = findViewById(R.id.emailTextView);
        departmentTextView = findViewById(R.id.departmentTextView);
        salaryTextView = findViewById(R.id.salaryTextView);
        leavesTextView = findViewById(R.id.leavesTextView);

        userDAO = new UserDAO(this);
        userDAO.open();

        String username = getIntent().getStringExtra("username");
        Cursor cursor = userDAO.getUserDetails(username);

        if (cursor.moveToFirst()) {
            String firstName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_FIRSTNAME));
            String lastName = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_LASTNAME));
            String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_EMAIL));
            String department = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_DEPARTMENT));
            String salary = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_SALARY));
            int leaves = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_LEAVES));

            firstNameTextView.setText(firstName);
            lastNameTextView.setText(lastName);
            emailTextView.setText(email);
            departmentTextView.setText(department);
            salaryTextView.setText(salary);
            leavesTextView.setText(String.valueOf(leaves));
        }

        cursor.close();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDAO.close();
    }
}