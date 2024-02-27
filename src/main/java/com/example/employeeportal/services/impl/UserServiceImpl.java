package com.example.employeeportal.services.impl;

import com.example.employeeportal.dto.EmployeeDto;
import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserServiceImpl implements UserService {

    @Override
    public ResponseEntity<Object> login(String userName, String password) throws Exception{

        EmployeeDto employeeDto =
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> register(RegisterUserDto registerUserDto) throws Exception{


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
