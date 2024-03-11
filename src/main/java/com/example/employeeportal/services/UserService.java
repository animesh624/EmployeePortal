package com.example.employeeportal.services;

import com.example.employeeportal.dto.GetEmailDto;
import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    ResponseEntity<Object> login(LoginUserDto loginUserDto) throws Exception;

    ResponseEntity<Object> register(MultipartFile file, String data, String token) throws Exception;

    ResponseEntity<Object> isLoggedIn(GetEmailDto getEmailDto, String token) throws Exception;

    ResponseEntity<Object> forgotPassword(GetEmailDto getEmailDto, String token) throws Exception;

    ResponseEntity<Object> verifyOtp(String otp, GetEmailDto getEmailDto) throws Exception;

    ResponseEntity<Object> resetPassword(String otp, LoginUserDto loginUserDto) throws Exception;
}
