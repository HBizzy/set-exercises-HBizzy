package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient;
import com.example.myapplication.ApiService;
import com.example.myapplication.Employee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddEmployeeDetailsActivity extends AppCompatActivity {

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText departmentEditText;
    private EditText salaryEditText;
    private Button addEmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_employee_details);

        firstNameEditText = findViewById(R.id.firstNameEditText);
        lastNameEditText = findViewById(R.id.lastNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        departmentEditText = findViewById(R.id.departmentEditText);
        salaryEditText = findViewById(R.id.salaryEditText);
        addEmployeeButton = findViewById(R.id.addEmployeeButton);

        addEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEmployee();
            }
        });
    }

    private void addEmployee() {
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String department = departmentEditText.getText().toString();
        String salary = salaryEditText.getText().toString();

        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()  ||
                department.isEmpty() || salary.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String joiningDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        int leaves = 30;

        Employee employee = new Employee();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setEmail(email);
        employee.setDepartment(department);
        employee.setSalary(salary);
        employee.setJoiningDate(joiningDate);
        employee.setLeaves(Integer.parseInt(String.valueOf(leaves)));

        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Employee> call = apiService.addEmployee(employee);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(AddEmployeeDetailsActivity.this, "Employee added successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddEmployeeDetailsActivity.this, "Failed to add employee", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(AddEmployeeDetailsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}