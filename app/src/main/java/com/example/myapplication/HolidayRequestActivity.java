// HolidayRequestActivity.java
package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class HolidayRequestActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private Button submitRequestButton;

    private Date startDate;
    private Date endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.holiday_request);

        calendarView = findViewById(R.id.calendarView);
        startDateTextView = findViewById(R.id.startDateTextView);
        endDateTextView = findViewById(R.id.endDateTextView);
        submitRequestButton = findViewById(R.id.submitRequestButton);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (startDate == null) {
                    startDate = new GregorianCalendar(year, month, dayOfMonth).getTime();
                    startDateTextView.setText("Start Date: " + formatDate(startDate));
                } else {
                    endDate = new GregorianCalendar(year, month, dayOfMonth).getTime();
                    endDateTextView.setText("End Date: " + formatDate(endDate));
                }
            }
        });

        submitRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Validate and submit the holiday request
                if (startDate != null && endDate != null) {
                    Intent intent = new Intent(HolidayRequestActivity.this, ManageAnnualLeaveActivity.class);
                    intent.putExtra("startDate", formatDate(startDate));
                    intent.putExtra("endDate", formatDate(endDate));
                    startActivity(intent);
                } else {
                    // Handle case where start or end date is not selected
                }
            }
        });
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        return sdf.format(date);
    }
}