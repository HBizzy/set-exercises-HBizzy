package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHomepageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_homepage);

        Button holidayRequestsButton = findViewById(R.id.holiday_requests_button);
        Button editEmployeeDetailsButton = findViewById(R.id.Edit_employee_details_button);
        Button addEmployeeDetailsButton = findViewById(R.id.Add_employee_details_button);
        Button deleteEmployeeDetailsButton = findViewById(R.id.Delete_employee_details_button);
        Button lastUpdatedEmployeeDetailsButton = findViewById(R.id.last_updated__employee_details_button);

        holidayRequestsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepageActivity.this, HolidayRequestsActivity.class);
                startActivity(intent);
            }
        });

        editEmployeeDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepageActivity.this, EditEmployeeDetailsActivity.class);
                startActivity(intent);
            }
        });

        addEmployeeDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepageActivity.this, AddEmployeeDetailsActivity.class);
                startActivity(intent);
            }
        });

        deleteEmployeeDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepageActivity.this, DeleteEmployeeDetailsActivity.class);
                startActivity(intent);
            }
        });

        lastUpdatedEmployeeDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHomepageActivity.this, LastUpdatedEmployeeDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
