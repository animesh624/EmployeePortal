package com.example.employeeportal.controller;

import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody LoginUserDto loginUserDto) throws Exception {
        return userService.login(loginUserDto);
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Object> resgister(@RequestBody RegisterUserDto registerUserDto) throws Exception {
        return userService.register(registerUserDto);
    }

}
