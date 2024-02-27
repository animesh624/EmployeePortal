package com.example.employeeportal.manager;

import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.UserData;
import org.springframework.stereotype.Component;

@Component
public interface UserDataManager{


    UserData getByUserName(String userName) throws Exception;
}
