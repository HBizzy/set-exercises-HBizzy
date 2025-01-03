package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditEmployeeDetailsActivity extends AppCompatActivity {

    private EditText employeeIdEditText;
    private Button searchEmployeeButton;
    private PopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_employee_details);

        employeeIdEditText = findViewById(R.id.employeeIdEditText);
        searchEmployeeButton = findViewById(R.id.searchEmployeeButton);

        searchEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employeeId = employeeIdEditText.getText().toString();
                if (!employeeId.isEmpty()) {
                    searchEmployee(employeeId);
                } else {
                    Toast.makeText(EditEmployeeDetailsActivity.this, "Please enter an Employee ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void searchEmployee(String employeeId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Employee> call = apiService.getEmployeeById(employeeId);

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful() && response.body() != null) {
                    showEditPopup(response.body());
                } else {
                    String errorMessage = "Error fetching employee data.";
                    if (!response.isSuccessful()) {
                        try {
                            errorMessage = response.errorBody().string();
                        } catch (Exception e) {
                            errorMessage = "Network error occurred.";
                        }
                    }
                    showToast(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                showToast("Network error: " + t.getMessage());
            }
        });
    }

    private void showEditPopup(final Employee employee) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.edit_employee_popup, null);

        popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);

        final EditText firstNameEditText = popupView.findViewById(R.id.firstNameEditText);
        final EditText lastNameEditText = popupView.findViewById(R.id.lastNameEditText);
        final EditText emailEditText = popupView.findViewById(R.id.emailEditText);
        final EditText departmentEditText = popupView.findViewById(R.id.departmentEditText);
        final EditText salaryEditText = popupView.findViewById(R.id.salaryEditText);
        Button saveButton = popupView.findViewById(R.id.saveButton);

        firstNameEditText.setText(employee.getFirstName());
        lastNameEditText.setText(employee.getLastName());
        emailEditText.setText(employee.getEmail());
        departmentEditText.setText(employee.getDepartment());
        salaryEditText.setText(String.valueOf(employee.getSalary())); // Ensure salary is set as a String

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = firstNameEditText.getText().toString();
                String lastName = lastNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String department = departmentEditText.getText().toString();
                String salaryStr = salaryEditText.getText().toString();

                // Basic input validation
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || department.isEmpty() || salaryStr.isEmpty()) {
                    showToast("Please fill in all fields.");
                    return;
                }

                try {
                    double salary = Double.parseDouble(salaryStr);
                    employee.setFirstName(firstName);
                    employee.setLastName(lastName);
                    employee.setEmail(email);
                    employee.setDepartment(department);
                    employee.setSalary(String.valueOf(salary));

                    updateEmployee(employee);
                } catch (NumberFormatException e) {
                    showToast("Invalid salary format.");
                }
            }
        });

        View rootView = findViewById(android.R.id.content).getRootView();
        popupWindow.showAtLocation(rootView, 1, 80, 80);
    }

    private void updateEmployee(Employee employee) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Employee> call = apiService.updateEmployee(employee.getId(), employee);

        // Log the request payload for debugging
        Log.d("UpdateEmployee", "Request Payload: " + new Gson().toJson(employee));

        call.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(EditEmployeeDetailsActivity.this, "Employee updated successfully", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                } else {
                    try {
                        String errorBody = response.errorBody().string();
                        Toast.makeText(EditEmployeeDetailsActivity.this, "Failed to update employee: " + errorBody, Toast.LENGTH_SHORT).show();
                        Log.e("UpdateEmployee", "Error: " + errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(EditEmployeeDetailsActivity.this, "Failed to update employee", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(EditEmployeeDetailsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("UpdateEmployee", "Failure: " + t.getMessage());
            }
        });
    }


    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}