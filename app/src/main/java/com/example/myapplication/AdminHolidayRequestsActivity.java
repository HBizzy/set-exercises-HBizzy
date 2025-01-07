package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class AdminHolidayRequestsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner startDateSpinner, endDateSpinner, firstNameSpinner, lastNameSpinner, approveDenySpinner;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_holiday_requests);

        // Initialize Spinners
        startDateSpinner = findViewById(R.id.startDateSpinner);
        endDateSpinner = findViewById(R.id.endDateSpinner);
        firstNameSpinner = findViewById(R.id.firstNameSpinner);
        lastNameSpinner = findViewById(R.id.lastNameSpinner);
        approveDenySpinner = findViewById(R.id.approveDenySpinner);

        // Sample data for Spinners (replace with actual data)
        List<String> startDates = Arrays.asList("01-01-2024", "02-01-2024", "03-01-2024");
        List<String> endDates = Arrays.asList("31-01-2024", "28-02-2024", "31-03-2024");
        List<String> firstNames = Arrays.asList("John", "Jane", "David");
        List<String> lastNames = Arrays.asList("Doe", "Smith", "Lee");
        List<String> approveDenyOptions = Arrays.asList("Approve", "Deny");

        // Create ArrayAdapter for each Spinner
        ArrayAdapter<String> startDateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, startDates);
        startDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startDateSpinner.setAdapter(startDateAdapter);
        startDateSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> endDateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, endDates);
        endDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        endDateSpinner.setAdapter(endDateAdapter);
        endDateSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> firstNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, firstNames);
        firstNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        firstNameSpinner.setAdapter(firstNameAdapter);
        firstNameSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> lastNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lastNames);
        lastNameAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lastNameSpinner.setAdapter(lastNameAdapter);
        lastNameSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> approveDenyAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, approveDenyOptions);
        approveDenyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        approveDenySpinner.setAdapter(approveDenyAdapter);
        approveDenySpinner.setOnItemSelectedListener(this);

        // Initialize Save Button
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(v -> {
            // Get selected values from Spinners
            String selectedStartDate = startDateSpinner.getSelectedItem().toString();
            String selectedEndDate = endDateSpinner.getSelectedItem().toString();
            String selectedFirstName = firstNameSpinner.getSelectedItem().toString();
            String selectedLastName = lastNameSpinner.getSelectedItem().toString();
            String selectedApprovalStatus = approveDenySpinner.getSelectedItem().toString();

            // Process the selected values (e.g., save to database, display in a dialog, etc.)
            Toast.makeText(this, "Saved: " + selectedStartDate + " - " + selectedEndDate + " - " + selectedFirstName + " " + selectedLastName + " - " + selectedApprovalStatus, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(this, "Holiday request updated", Toast.LENGTH_SHORT).show();
//        // Navigate back to Admin Home Page
//        Intent intent = new Intent(this, AdminHomepageActivity.class);
//        startActivity(intent);
//        finish();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Nothing selected", Toast.LENGTH_SHORT).show();
        // Navigate back to Admin Home Page
        Intent intent = new Intent(this, AdminHomepageActivity.class);
        startActivity(intent);
        finish();
    }
}