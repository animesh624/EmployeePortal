package com.example.employeeportal.controller;


import com.example.employeeportal.dto.EmployeeDto;
import com.example.employeeportal.dto.GetEmployeeDto;
import com.example.employeeportal.dto.SearchEmployeeDto;
import com.example.employeeportal.dto.SearchResultDto;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @CrossOrigin(origins = "*")
    @PostMapping("/getByUserName")
    public ResponseEntity<Object> getByUserName(@RequestBody GetEmployeeDto getEmployeeDto, @RequestHeader String token) throws Exception{
        return employeeService.getByUserName(getEmployeeDto,token);
    }


    //this will use when bandwidth.
    @PostMapping("/edit/employee")
    public EmployeeData editEmployee(@RequestParam EmployeeDto employeeDto) throws Exception{
        return employeeService.editEmployee(employeeDto);
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/search")
    public ResponseEntity<Object> searchEmployee(@RequestParam SearchEmployeeDto searchEmployeeDto, @RequestHeader String token) throws Exception{
        return employeeService.searchEmployee(searchEmployeeDto,token);
    }
}
