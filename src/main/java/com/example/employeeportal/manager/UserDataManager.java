package com.example.employeeportal.manager;

import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.UserData;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;

public interface UserDataManager extends  GenericManager<UserData, String> {

    UserData getByUserName(String userName) throws Exception;
}
