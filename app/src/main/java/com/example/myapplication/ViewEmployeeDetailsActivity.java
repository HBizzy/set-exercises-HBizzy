package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.ApiClient;
import com.example.myapplication.ApiService;
import com.example.myapplication.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewEmployeeDetailsActivity extends Activity {

    private ListView employeeListView;
    private EmployeeAdapter employeeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_employee_details);

        employeeListView = findViewById(R.id.employeeListView);

        fetchEmployeeDetails();
    }

    private void fetchEmployeeDetails() {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<List<Employee>> call = apiService.getAllEmployees();

        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Employee> employeeList = response.body();
                    employeeAdapter = new EmployeeAdapter(ViewEmployeeDetailsActivity.this, employeeList);
                    employeeListView.setAdapter(employeeAdapter);
                } else {
                    Toast.makeText(ViewEmployeeDetailsActivity.this, "Failed to fetch employee details", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Toast.makeText(ViewEmployeeDetailsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}