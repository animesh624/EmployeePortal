package com.example.employeeportal.services;

import com.example.employeeportal.dto.EmployeeDto;
import com.example.employeeportal.dto.SearchResultDto;
import com.example.employeeportal.model.EmployeeData;
import org.springframework.stereotype.Component;

@Component
public interface EmployeeService {

    public EmployeeData getByUserName(String userName) throws Exception;

    public EmployeeData editEmployee(EmployeeDto employeeDto) throws Exception;

    public SearchResultDto searchEmployee(String keyword) throws Exception;

}
