package com.example.myapplication;

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
        setContentView(R.layout.admin_holiday_requests); // Use the correct layout name

        // Initialize Spinners
        startDateSpinner = findViewById(R.id.startDateSpinner);
        endDateSpinner = findViewById(R.id.endDateSpinner);
        firstNameSpinner = findViewById(R.id.firstNameSpinner);
        lastNameSpinner = findViewById(R.id.lastNameSpinner);
        approveDenySpinner = findViewById(R.id.approveDenySpinner);

        // Sample data for Spinners (replace with actual data)
        List<String> startDates = Arrays.asList("01-01-2024", "02-01-2024", "03-01-2024"); // ...
        List<String> endDates = Arrays.asList("31-01-2024", "28-02-2024", "31-03-2024"); // ...
        List<String> firstNames = Arrays.asList("John", "Jane", "David"); // ...
        List<String> lastNames = Arrays.asList("Doe", "Smith", "Lee"); // ...
        List<String> approveDenyOptions = Arrays.asList("Approve", "Deny");

        // Create ArrayAdapter for each Spinner
        ArrayAdapter<String> startDateAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, startDates);
        startDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        startDateSpinner.setAdapter(startDateAdapter);
        startDateSpinner.setOnItemSelectedListener(this);

        // Similar code for endDateSpinner, firstNameSpinner, lastNameSpinner, approveDenySpinner

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
        // Handle Spinner selection changes (optional)
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Handle Spinner selection changes (optional)
    }
}