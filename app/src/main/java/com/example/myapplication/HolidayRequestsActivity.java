package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HolidayRequestsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_holiday_requests);

        //ListView listView = findViewById(R.id.holiday_requests_button);

        // Sample data
        List<Map<String, String>> data = new ArrayList<>();
        data.add(new HashMap<String, String>() {{
            put("employee_name", "Bob");
            put("days", "2 days");
            put("dates", "15/11 - 16/11");
        }});
        data.add(new HashMap<String, String>() {{
            put("employee_name", "Geoff");
            put("days", "8 days");
            put("dates", "31/12 - 7/1");
        }});

        // Create a SimpleAdapter to display the data
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[]{"employee_name", "days, dates"},
                new int[]{android.R.id.text1, android.R.id.text2});

        listView.setAdapter(adapter);
    }
}