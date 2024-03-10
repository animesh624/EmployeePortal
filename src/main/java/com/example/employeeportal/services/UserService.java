package com.example.employeeportal.services;

import com.example.employeeportal.dto.GetEmailDto;
import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

public interface UserService {

    ResponseEntity<Object> login(LoginUserDto loginUserDto) throws Exception;

    ResponseEntity<Object> register(RegisterUserDto registerUserDto,String token) throws Exception;

    ResponseEntity<Object> isLoggedIn(String userEmail, String token) throws Exception;

    ResponseEntity<Object> forgotPassword(GetEmailDto getEmailDto, String token) throws Exception;

    ResponseEntity<Object> verifyOtp(String otp, GetEmailDto getEmailDto) throws Exception;

    ResponseEntity<Object> resetPassword(String otp, LoginUserDto loginUserDto) throws Exception;
}
