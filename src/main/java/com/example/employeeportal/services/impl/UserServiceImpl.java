package com.example.employeeportal.services.impl;

import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.UserData;
import com.example.employeeportal.services.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDataManager userDataManager;

    @Autowired
    EmployeeDataManager employeeDataManager;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public ResponseEntity<Object> login(LoginUserDto loginUserDto) throws Exception{

        UserData userData = userDataManager.getByUserName(loginUserDto.getUserName());
        if(userData == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isEmpty(loginUserDto.getPassword()) || !bCryptPasswordEncoder.matches(loginUserDto.getPassword(), userData.getPassword())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> register(RegisterUserDto registerUserDto) throws Exception{
        EmployeeData employeeData = employeeDataManager.getByUserName(registerUserDto.getUserName());
        if(employeeData != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        employeeData = new EmployeeData();
        employeeData.setContactNumber(registerUserDto.getContactNumber());
        employeeData.setEmpCode(registerUserDto.getEmpCode());
        employeeData.setDesignation(registerUserDto.getDesignation());
        employeeData.setLevel(registerUserDto.getLevel());
        employeeData.setFirstName(registerUserDto.getFirstName());
        employeeData.setLastName(registerUserDto.getLastName());
        employeeData.setUserName(registerUserDto.getUserName());
        employeeData.setPassword(bCryptPasswordEncoder.encode(registerUserDto.getPassword()));
        employeeDataManager.save(employeeData);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
