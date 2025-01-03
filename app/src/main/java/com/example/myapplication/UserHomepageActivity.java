package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserHomepageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_homepage);

        Button viewDetailsButton = findViewById(R.id.viewDetailsButton);
        Button editDetailsButton = findViewById(R.id.editDetailsButton);
        Button manageLeaveButton = findViewById(R.id.manageLeaveButton);

        viewDetailsButton.setOnClickListener(v -> {
            String username = getIntent().getStringExtra("username");
            if (username != null) {
                Intent intent = new Intent(UserHomepageActivity.this, ViewMyEmployeeDetailsActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });

        editDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomepageActivity.this, EditMyDetailsActivity.class);
                startActivity(intent);
            }
        });

        manageLeaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomepageActivity.this, ManageAnnualLeaveActivity.class);
                startActivity(intent);
            }
        });
    }
}