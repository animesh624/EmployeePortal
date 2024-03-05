package com.example.employeeportal.services;

import com.example.employeeportal.dto.EmployeeDto;
import com.example.employeeportal.dto.GetEmployeeDto;
import com.example.employeeportal.dto.SearchEmployeeDto;
import com.example.employeeportal.model.EmployeeData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface EmployeeService {

    ResponseEntity<Object> getByUserName(GetEmployeeDto getEmployeeDto, String token) throws Exception;

    EmployeeData editEmployee(EmployeeDto employeeDto) throws Exception;

    ResponseEntity<Object> searchEmployee(SearchEmployeeDto searchEmployeeDto, String token) throws Exception;

    ResponseEntity<Object> uploadFile(MultipartFile file) throws Exception;

}
