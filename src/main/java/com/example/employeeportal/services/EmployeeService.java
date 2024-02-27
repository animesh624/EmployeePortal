package com.example.employeeportal.services;

import com.example.employeeportal.model.EmployeeData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeService {

    public EmployeeData getByUserName(String userName) throws Exception;
}
