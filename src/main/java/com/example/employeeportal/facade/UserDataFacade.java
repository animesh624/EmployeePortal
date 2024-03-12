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
        EmployeeData employeeData = buildEmployeeData(registerUserDto, fileUrl);
        employeeDataManager.save(employeeData);
    }

    private EmployeeData buildEmployeeData(RegisterUserDto registerUserDto, String fileUrl) throws Exception{
        return EmployeeData.builder()
                .contactNumber(registerUserDto.getContactNumber())
                .empCode(registerUserDto.getEmpCode())
                .designation(registerUserDto.getDesignation())
                .pod(registerUserDto.getPod())
                .firstName(registerUserDto.getFirstName())
                .lastName(registerUserDto.getLastName())
                .userEmail(registerUserDto.getUserEmail())
                .managerEmail(registerUserDto.getManagerEmail())
                .DOB(registerUserDto.getDOB())
                .profileImageUrl(fileUrl)
                .build();
    }

    public void saveEntryInUserData(RegisterUserDto registerUserDto) throws Exception{
        UserData userData = buildUserData(registerUserDto);
        userDataManager.save(userData);
    }

    private UserData buildUserData(RegisterUserDto registerUserDto) throws Exception{
        return UserData.builder()
                .userEmail(registerUserDto.getUserEmail())
                .lastName(registerUserDto.getLastName())
                .firstName(registerUserDto.getFirstName())
                .password(bCryptPasswordEncoder.encode(registerUserDto.getPassword()))
                .build();
    }

    public void createMapping(RegisterUserDto registerUserDto) throws Exception{
        if(!StringUtils.isEmpty(registerUserDto.getManagerEmail())){
            ManagerReportee managerReportee = buildManagerReportee(registerUserDto);
            managerReporteeManager.save(managerReportee);
        }
    }

    private ManagerReportee buildManagerReportee(RegisterUserDto registerUserDto) throws Exception{
        return ManagerReportee.builder()
                .reporteeEmail(registerUserDto.getUserEmail())
                .managerEmail(registerUserDto.getManagerEmail())
                .build();
    }

}
