package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    private EditText username_edit_text;
    private EditText password_edit_text;
    private Button login_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        username_edit_text = findViewById(R.id.username_edit_text);
        password_edit_text = findViewById(R.id.password_edit_text);
        login_button = findViewById(R.id.login_button);

        login_button.setOnClickListener(v -> {
            String username = username_edit_text.getText().toString();
            String password = password_edit_text.getText().toString();

            // Check for admin login
            if (username.equals("admin") && password.equals("admin")) {
                Intent intent = new Intent(LoginActivity.this, AdminHomepageActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Check for user login
                SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
                String savedUsername = sharedPreferences.getString("username", "");
                String savedPassword = sharedPreferences.getString("password", "");

                if (username.equals(savedUsername) && password.equals(savedPassword)) {
                    Intent intent = new Intent(LoginActivity.this, UserHomepageActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserCredentials(String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }
}