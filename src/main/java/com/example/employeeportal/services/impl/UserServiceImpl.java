package com.example.employeeportal.services.impl;

import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.facade.UserDataFacade;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.ManagerReporteeManager;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.UserData;
import com.example.employeeportal.services.UserService;
import com.example.employeeportal.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
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

    @Autowired
    ManagerReporteeManager managerReporteeManager;

    @Autowired
    UserDataFacade userDataFacade;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }
    @Override
    public ResponseEntity<Object> login(LoginUserDto loginUserDto) throws Exception{

        Map<String, Object> result = new HashMap<>();
        UserData userData = userDataManager.getByUserEmail(loginUserDto.getUserEmail());
        if(userData == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isEmpty(loginUserDto.getPassword()) || !bCryptPasswordEncoder.matches(loginUserDto.getPassword(), userData.getPassword())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String token = jwtUtil.createJWTToken(loginUserDto.getUserEmail(),"TokenForEmployeePortal");
        result.put("data", token);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> register(RegisterUserDto registerUserDto,String token) throws Exception{
        log.info("Token recieved is {} and {}",token,(userDataManager.getByUserEmail(registerUserDto.getRequestUserEmail()).getIsAdmin()));
        if(!jwtUtil.isTokenValid(token,registerUserDto.getRequestUserEmail())
                || !(userDataManager.getByUserEmail(registerUserDto.getRequestUserEmail()).getIsAdmin())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(employeeDataManager.getByUserEmail(registerUserDto.getUserEmail()) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        userDataFacade.saveEntryInEmployeeData(registerUserDto);
        userDataFacade.saveEntryInUserData(registerUserDto);
        userDataFacade.createMapping(registerUserDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> isLoggedIn(String userEmail, String token) throws Exception{
        if(!jwtUtil.isTokenValid(token,userEmail)){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
