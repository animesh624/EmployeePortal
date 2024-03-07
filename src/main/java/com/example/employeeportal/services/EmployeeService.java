package com.example.employeeportal.services;

import com.example.employeeportal.dto.*;
import com.example.employeeportal.model.EmployeeData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface EmployeeService {

    ResponseEntity<Object> getByUserEmail(GetEmployeeDto getEmployeeDto, String token) throws Exception;

    ResponseEntity<Object> editEmployee(EditEmployeeDto editEmployeeDto,String token) throws Exception;

    ResponseEntity<Object> searchEmployee(String name, String designation, String expertise ,String userEmail, String token) throws Exception;

    ResponseEntity<Object> getNeighbours(GetNeighboursDto getNeighboursDto, String token) throws Exception;
    ResponseEntity<Object> uploadFile(MultipartFile file) throws Exception;

    ResponseEntity<Object> downloadFile(String fileName) throws Exception;


}
