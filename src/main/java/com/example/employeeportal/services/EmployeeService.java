package com.example.employeeportal.services;

import com.example.employeeportal.dto.*;
import com.example.employeeportal.model.EmployeeData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeService {

    ResponseEntity<Object> getByUserName(GetEmployeeDto getEmployeeDto, String token) throws Exception;

    EmployeeData editEmployee(EmployeeDto employeeDto) throws Exception;

    ResponseEntity<Object> searchEmployee(SearchEmployeeDto searchEmployeeDto, String token) throws Exception;

    ResponseEntity<Object> getNeighbours(GetNeighboursDto getNeighboursDto, String token) throws Exception;
}
