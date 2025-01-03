package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ManageAnnualLeaveActivity extends AppCompatActivity {

    private TextView approvedHolidayTextView, pendingHolidayTextView;
    private Button submitHolidayRequestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_holiday);

        approvedHolidayTextView = findViewById(R.id.approvedHolidayTextView);
        pendingHolidayTextView = findViewById(R.id.pendingHolidayTextView);
        submitHolidayRequestButton = findViewById(R.id.submitHolidayRequestButton);

        // Get approved and pending holidays from data source (e.g., database)
        String approvedHoliday = getApprovedHoliday();
        String pendingHoliday = getPendingHoliday();

        approvedHolidayTextView.setText(approvedHoliday);
        pendingHolidayTextView.setText(pendingHoliday);

        submitHolidayRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the HolidayRequestActivity
                Intent intent = new Intent(ManageAnnualLeaveActivity.this, HolidayRequestActivity.class);
                startActivity(intent);
            }
        });
    }

    // Methods to get approved and pending holidays from data source
    private String getApprovedHoliday() {
        // ... (Implement logic to fetch approved holiday from data source)
        return "2 days - 15/11 - 16/11"; // Example
    }

    private String getPendingHoliday() {
        // ... (Implement logic to fetch pending holiday from data source)
        return "No upcoming holiday"; // Example
    }
}
