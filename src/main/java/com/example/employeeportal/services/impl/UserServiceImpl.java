package com.example.employeeportal.services.impl;

import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.UserData;
import com.example.employeeportal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;

public class UserServiceImpl implements UserService {

    @Autowired
    UserDataManager userDataManager;

    @Autowired
    EmployeeDataManager employeeDataManager;

    @Override
    public ResponseEntity<Object> login(String userName, String password) throws Exception{

        UserData userData = userDataManager.getByUserName(userName);
        if(userData == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(StringUtils.isEmpty(password) || !bCryptPasswordEncoder.matches(password, userData.getPassword())){
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
        employeeData.setContactNumber(registerUserDto.getContactNumber());
        employeeData.setEmpCode(registerUserDto.getEmpCode());
        employeeData.setDesignation(registerUserDto.getEmpCode());
        employeeData.setLevel(registerUserDto.getLevel());
        employeeData.setFirstName(registerUserDto.getFirstName());
        employeeData.setLastName(registerUserDto.getLastName());
        employeeDataManager.save
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
