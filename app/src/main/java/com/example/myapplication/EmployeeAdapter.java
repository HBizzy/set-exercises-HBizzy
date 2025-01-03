package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.Employee;

import java.util.List;

public class EmployeeAdapter extends BaseAdapter {

    private Context context;
    private List<Employee> employeeList;

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        }

        Employee employee = employeeList.get(position);

        TextView firstNameTextView = convertView.findViewById(R.id.firstNameTextView);
        TextView lastNameTextView = convertView.findViewById(R.id.lastNameTextView);
        TextView emailTextView = convertView.findViewById(R.id.emailTextView);
        TextView departmentTextView = convertView.findViewById(R.id.departmentTextView);
        TextView salaryTextView = convertView.findViewById(R.id.salaryTextView);
        TextView leavesTextView = convertView.findViewById(R.id.leavesTextView);

        firstNameTextView.setText(employee.getFirstName());
        lastNameTextView.setText(employee.getLastName());
        emailTextView.setText(employee.getEmail());
        departmentTextView.setText(employee.getDepartment());
        salaryTextView.setText(employee.getSalary());
        leavesTextView.setText(String.valueOf(employee.getLeaves()));

        return convertView;
    }
}
