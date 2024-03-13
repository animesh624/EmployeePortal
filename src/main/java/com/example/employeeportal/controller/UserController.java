package com.example.employeeportal.controller;

import com.example.employeeportal.dto.GetEmailDto;
import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.UserData;
import com.example.employeeportal.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    // this is only for testing purpose
    @GetMapping
    public String testing(){
        return "Hello world!!!";
    }

    @PostMapping(path = "/login")
    public ResponseEntity<Object> here(@RequestBody LoginUserDto loginUserDto) throws Exception {
        log.info("Animesh enetered here at login{}",loginUserDto);
        return userService.login(loginUserDto);
    }

    @PostMapping(path = "/isLoggedIn")
    public ResponseEntity<Object> isLoggedIn(@RequestBody GetEmailDto getEmailDto, @RequestHeader String token) throws Exception{
        return userService.isLoggedIn(getEmailDto,token);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Object> register(@RequestParam(value = "file", required = false) MultipartFile file,
                                           @RequestParam("data") String data,
                                           @RequestHeader String token) throws Exception {
        return userService.register(file,data,token);
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
