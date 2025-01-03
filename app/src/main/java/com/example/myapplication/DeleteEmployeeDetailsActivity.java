//package com.example.myapplication;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class DeleteEmployeeDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//
//    private Spinner employeeSpinner;
//    private TextView selectedEmployeeNameTextView;
//    private Button deleteEmployeeButton;
//    private List<String> employeeNames;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.delete_employee); // Assuming your layout is named delete_employee_layout
//
//        // Initialize views
//        employeeSpinner = findViewById(R.id.employeeSpinner);
//        selectedEmployeeNameTextView = findViewById(R.id.selectedEmployeeName);
//        deleteEmployeeButton = findViewById(R.id.deleteEmployeeButton);
//
//        // Sample employee names (replace with actual data)
//        employeeNames = Arrays.asList("Bob", "Dave", "Daphne");
//
//        // Create ArrayAdapter for the Spinner
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, employeeNames);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        employeeSpinner.setAdapter(adapter);
//        employeeSpinner.setOnItemSelectedListener(this);
//
//        // Disable the Delete button initially
//        deleteEmployeeButton.setEnabled(false);
//
//        deleteEmployeeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Get the selected employee name
//                String selectedEmployee = employeeSpinner.getSelectedItem().toString();
//
//                // Implement the logic to delete the employee from your data source
//                // ... (e.g., Database interaction)(API endpoint 1 and 5)
//
//                // Display a success message
//                Toast.makeText(DeleteEmployeeDetailsActivity.this, "Employee " + selectedEmployee + " deleted successfully!", Toast.LENGTH_SHORT).show();
//
//                // Clear the selection and disable the Delete button
//                employeeSpinner.setSelection(0); // Select the first item (or clear selection)
//                selectedEmployeeNameTextView.setText("");
//                deleteEmployeeButton.setEnabled(false);
//            }
//        });
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String selectedEmployee = employeeNames.get(position);
//        selectedEmployeeNameTextView.setText(selectedEmployee);
//        deleteEmployeeButton.setEnabled(true); // Enable the Delete button after an employee is selected
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//        selectedEmployeeNameTextView.setText("");
//        deleteEmployeeButton.setEnabled(false);
//    }
//}
package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.ApiClient;
import com.example.myapplication.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteEmployeeDetailsActivity extends AppCompatActivity {

    private EditText employeeIdEditText;
    private Button deleteEmployeeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_employee);

        employeeIdEditText = findViewById(R.id.employeeIdEditText);
        deleteEmployeeButton = findViewById(R.id.deleteEmployeeButton);

        deleteEmployeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String employeeId = employeeIdEditText.getText().toString();
                if (!employeeId.isEmpty()) {
                    deleteEmployee(employeeId);
                } else {
                    Toast.makeText(DeleteEmployeeDetailsActivity.this, "Please enter an Employee ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteEmployee(String employeeId) {
        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.deleteEmployee(Integer.parseInt(employeeId));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DeleteEmployeeDetailsActivity.this, "Employee deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DeleteEmployeeDetailsActivity.this, "Failed to delete employee", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(DeleteEmployeeDetailsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}