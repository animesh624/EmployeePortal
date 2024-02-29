package com.example.employeeportal.controller;


import com.example.employeeportal.dto.EmployeeDto;
import com.example.employeeportal.dto.SearchResultDto;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/getByUserName")
    public EmployeeData getByUserName(@RequestParam String userName) throws Exception{
        return employeeService.getByUserName(userName);
    }

    @PostMapping("/edit/employee")
    public EmployeeData editEmployee(@RequestParam EmployeeDto employeeDto) throws Exception{
        return employeeService.editEmployee(employeeDto);
    }

    @PostMapping("/search")
    public SearchResultDto searchEmployee(@RequestParam String keyword) throws Exception{
        return employeeService.searchEmployee(keyword);
    }
}
