package com.example.employeeportal.controller;

import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
        log.info("Animesh printint {} and {} and {}",file,data,token);
        return userService.register(file,data,token);
    }

    @PostMapping(path = "isLoggedIn")
    public ResponseEntity<Object> isLoggedIn(@RequestBody String userEmail, @RequestHeader String token) throws Exception{
        return userService.isLoggedIn(userEmail,token);
    }

}
