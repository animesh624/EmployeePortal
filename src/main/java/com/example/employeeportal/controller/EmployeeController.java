package com.example.employeeportal.controller;


import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public EmployeeData getByUserName(@RequestParam String userName) throws Exception{
        return employeeService.getByUserName(userName);
    }
}
