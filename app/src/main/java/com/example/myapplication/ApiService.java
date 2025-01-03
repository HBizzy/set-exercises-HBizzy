package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import java.util.List;

public interface ApiService {
    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    @GET("employees/get/{id}")
    Call<Employee> getEmployeeById(@Path("id") String employeeId);

    @POST("employees/add")
    Call<Employee> addEmployee(@Body Employee employee);

    @PUT("employees/edit/{id}")
    Call<Employee> updateEmployee(@Path("id") String employeeId, @Body Employee employee);
    @DELETE("employees/delete/{id}")
    Call<Void> deleteEmployee(@Path("id") int id);

    @GET("health")
    Call<Void> healthCheck();
}