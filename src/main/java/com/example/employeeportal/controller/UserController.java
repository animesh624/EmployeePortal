package com.example.employeeportal.controller;

import com.example.employeeportal.dto.GetEmailDto;
import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    // this is only for testing purpose
    @GetMapping
    public String testing(){
        return "Hello world!!!";
    }


    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginUserDto loginUserDto) throws Exception {
        return userService.login(loginUserDto);
    }


    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestBody RegisterUserDto registerUserDto, @RequestHeader String token) throws Exception {
        return userService.register(registerUserDto,token);
    }

    @PostMapping(path = "isLoggedIn")
    public ResponseEntity<Object> isLoggedIn(@RequestBody String userEmail, @RequestHeader String token) throws Exception{
        return userService.isLoggedIn(userEmail,token);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody GetEmailDto getEmailDto, @RequestHeader String token) throws Exception {
        return userService.forgotPassword(getEmailDto,token);
    }

    @GetMapping("/verify-otp")
    public ResponseEntity<Object> verifyOtp(@RequestParam(value = "otp")String otp, @RequestBody GetEmailDto getEmailDto) throws Exception {
        return userService.verifyOtp(otp, getEmailDto);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestParam(value = "otp")String otp, @RequestBody LoginUserDto loginUserDto) throws Exception {
        return userService.resetPassword(otp,loginUserDto);
    }
}
