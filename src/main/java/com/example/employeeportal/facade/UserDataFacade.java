package com.example.employeeportal.facade;


import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.ManagerReporteeManager;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.ManagerReportee;
import com.example.employeeportal.model.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

@Component
public class UserDataFacade {

    @Autowired
    EmployeeDataManager employeeDataManager;

    @Autowired
    UserDataManager userDataManager;

    @Autowired
    ManagerReporteeManager managerReporteeManager;

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostConstruct
    public void init() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public void saveEntryInEmployeeData(RegisterUserDto registerUserDto) throws Exception{
        EmployeeData employeeData = new EmployeeData();
        employeeData.setContactNumber(registerUserDto.getContactNumber());
        employeeData.setEmpCode(registerUserDto.getEmpCode());
        employeeData.setDesignation(registerUserDto.getDesignation());
        employeeData.setLevel(registerUserDto.getLevel());
        employeeData.setFirstName(registerUserDto.getFirstName());
        employeeData.setLastName(registerUserDto.getLastName());
        employeeData.setUserEmail(registerUserDto.getUserEmail());
        employeeData.setManagerEmail(registerUserDto.getManagerEmail());
        employeeData.setDOB(registerUserDto.getDOB());
        employeeDataManager.save(employeeData);
    }

    public void saveEntryInUserData(RegisterUserDto registerUserDto) throws Exception{
        UserData userData = new UserData();
        userData.setUserEmail(registerUserDto.getUserEmail());
        userData.setLastName(registerUserDto.getLastName());
        userData.setFirstName(registerUserDto.getFirstName());
        userData.setPassword(bCryptPasswordEncoder.encode(registerUserDto.getPassword()));
        userData.setIsAdmin(registerUserDto.getIsAdmin());
        userDataManager.save(userData);
    }

    public void createMapping(RegisterUserDto registerUserDto) throws Exception{
        if(!StringUtils.isEmpty(registerUserDto.getManagerEmail())){
            ManagerReportee managerReportee = new ManagerReportee();
            managerReportee.setManagerEmail(registerUserDto.getManagerEmail());
            managerReportee.setReporteeEmail(registerUserDto.getUserEmail());
            managerReporteeManager.save(managerReportee);
        }
    }

}
