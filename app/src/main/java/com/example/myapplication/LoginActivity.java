package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private UserDAO userDAO;
    private AdminDAO adminDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        usernameEditText = findViewById(R.id.username_edit_text);
        passwordEditText = findViewById(R.id.password_edit_text);
        loginButton = findViewById(R.id.login_button);

        userDAO = new UserDAO(this);
        adminDAO = new AdminDAO(this);
        userDAO.open();
        adminDAO.open();

        loginButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            // Check for admin login
            if (adminDAO.checkAdmin(username, password)) {
                Intent intent = new Intent(LoginActivity.this, AdminHomepageActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Check for user login
                if (userDAO.checkUser(username, password)) {
                    Intent intent = new Intent(LoginActivity.this, UserHomepageActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userDAO.close();
        adminDAO.close();
    }
}