package com.example.employeeportal.services;

import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

public interface UserService {

    ResponseEntity<Object> login(LoginUserDto loginUserDto) throws Exception;

    ResponseEntity<Object> register(RegisterUserDto registerUserDto) throws Exception;
}
