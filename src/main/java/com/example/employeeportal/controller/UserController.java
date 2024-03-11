package com.example.employeeportal.controller;

import com.example.employeeportal.dto.GetEmailDto;
import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
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
    public ResponseEntity<Object> register(@RequestParam("file") MultipartFile file,
                                           @RequestParam("data") String data,
                                           @RequestHeader String token) throws Exception {
        return userService.register(file,data,token);
    }

    @PostMapping(path = "/isLoggedIn")
    public ResponseEntity<Object> isLoggedIn(@RequestBody GetEmailDto getEmailDto, @RequestHeader String token) throws Exception{
        return userService.isLoggedIn(getEmailDto,token);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Object> forgotPassword(@RequestBody GetEmailDto getEmailDto) throws Exception {
        return userService.forgotPassword(getEmailDto);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Object> verifyOtp(@RequestParam(value = "otp")String otp, @RequestBody GetEmailDto getEmailDto) throws Exception {
        return userService.verifyOtp(otp, getEmailDto);
    }

    @PutMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestParam(value = "otp")String otp, @RequestBody LoginUserDto loginUserDto) throws Exception {
        return userService.resetPassword(otp,loginUserDto);
    }
}
