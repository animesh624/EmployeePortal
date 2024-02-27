package com.example.employeeportal.services;

import com.example.employeeportal.dto.RegisterUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    ResponseEntity<Object> login(String userName, String password) throws Exception;

    ResponseEntity<Object> register(RegisterUserDto registerUserDto) throws Exception;
}
