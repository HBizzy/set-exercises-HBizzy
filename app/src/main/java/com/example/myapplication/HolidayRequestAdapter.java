package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class HolidayRequestAdapter extends BaseAdapter {
    private final Context context;
    private final List<HolidayRequest> holidayRequests;

    public HolidayRequestAdapter(Context context, List<HolidayRequest> holidayRequests) {
        this.context = context;
        this.holidayRequests = holidayRequests;
    }

    @Override
    public int getCount() {
        return holidayRequests.size();
    }

    @Override
    public Object getItem(int position) {
        return holidayRequests.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.holiday_request_item, parent, false);
        }

        HolidayRequest request = holidayRequests.get(position);

        TextView employeeNameTextView = view.findViewById(R.id.employee_name);
        TextView numberOfDaysTextView = view.findViewById(R.id.number_of_days);
        TextView datesTextView = view.findViewById(R.id.dates);
        Button approveButton = view.findViewById(R.id.approve_button);
        Button rejectButton = view.findViewById(R.id.reject_button);

        employeeNameTextView.setText(request.getEmployeeName());
        numberOfDaysTextView.setText(request.getNumberOfDays() + " days");
        datesTextView.setText(request.getStartDate() + " - " + request.getEndDate());

        // Implement logic for approval/rejection buttons
        approveButton.setOnClickListener(v -> {
            // Update the request status in your data source
            request.setApproved(true);
            notifyDataSetChanged();
            // Send a notification to the user (implement notification logic)
        });

        rejectButton.setOnClickListener(v -> {
            // Update the request status in your data source
            request.setApproved(false);
            notifyDataSetChanged();
            // Send a notification to the user (implement notification logic)
        });

        return view;
    }
}
