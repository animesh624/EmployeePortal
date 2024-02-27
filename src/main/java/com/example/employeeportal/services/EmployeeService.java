package com.example.employeeportal.services;

import com.example.employeeportal.model.EmployeeData;

public interface EmployeeService {

    public EmployeeData getByUserName(String userName) throws Exception;
}
