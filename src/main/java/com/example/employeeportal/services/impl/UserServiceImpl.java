package com.example.employeeportal.services.impl;

import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.UserData;
import com.example.employeeportal.services.UserService;
import com.example.employeeportal.util.JWTUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDataManager userDataManager;

    @Autowired
    EmployeeDataManager employeeDataManager;

    @Autowired
    JWTUtil jwtUtil;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public ResponseEntity<Object> login(LoginUserDto loginUserDto) throws Exception{

        Map<String, Object> result = new HashMap<>();
        UserData userData = userDataManager.getByUserName(loginUserDto.getUserName());
        if(userData == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isEmpty(loginUserDto.getPassword()) || !bCryptPasswordEncoder.matches(loginUserDto.getPassword(), userData.getPassword())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String token = jwtUtil.generateToken(loginUserDto.getUserName(),"Token for employee portal");
        result.put("data", token);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> register(RegisterUserDto registerUserDto,String token) throws Exception{

        if(!jwtUtil.validateSubject(token,registerUserDto.getRequestUserName()) || !(userDataManager.getByUserName(registerUserDto.getRequestUserName()).getIsAdmin())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(employeeDataManager.getByUserName(registerUserDto.getUserName()) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        saveEntryInEmployeeData(registerUserDto);
        saveEntryInUserData(registerUserDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void saveEntryInEmployeeData(RegisterUserDto registerUserDto) throws Exception{
        EmployeeData employeeData = new EmployeeData();
        employeeData.setContactNumber(registerUserDto.getContactNumber());
        employeeData.setEmpCode(registerUserDto.getEmpCode());
        employeeData.setDesignation(registerUserDto.getDesignation());
        employeeData.setLevel(registerUserDto.getLevel());
        employeeData.setFirstName(registerUserDto.getFirstName());
        employeeData.setLastName(registerUserDto.getLastName());
        employeeData.setUserName(registerUserDto.getUserName());
        employeeDataManager.save(employeeData);
    }

    public void saveEntryInUserData(RegisterUserDto registerUserDto) throws Exception{
        UserData userData = new UserData();
        userData.setUserName(registerUserDto.getUserName());
        userData.setLastName(registerUserDto.getLastName());
        userData.setFirstName(registerUserDto.getFirstName());
        userData.setPassword(bCryptPasswordEncoder.encode(registerUserDto.getPassword()));
        userData.setIsAdmin(registerUserDto.getIsAdmin());
        userDataManager.save(userData);
    }
    @Override
    public ResponseEntity<Object> isLoggedIn(String userName, String token) throws Exception{
        if(!jwtUtil.validateSubject(token,userName)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
