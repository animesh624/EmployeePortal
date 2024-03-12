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

    private static String defaultFileUrl;

    @PostConstruct
    public void init() {
        defaultFileUrl = "https://www.google.com/url?sa=i&url=https%3A%2F%2Fin.linkedin.com%2Fcompany%2Fmoney-view&psig=AOvVaw2YvgLNG5srKt6lybVl6Lns&ust=1710301590312000&source=images&cd=vfe&opi=89978449&ved=0CBMQjRxqFwoTCMD2w4jo7YQDFQAAAAAdAAAAABAD";
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public void saveEntryInEmployeeData(RegisterUserDto registerUserDto,String fileUrl) throws Exception{

        if (StringUtils.isEmpty(fileUrl)){
            fileUrl = defaultFileUrl;
        }
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
        employeeData.setProfileImageUrl(fileUrl);
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
