package com.example.employeeportal.manager;

import com.example.employeeportal.model.UserData;

public interface UserDataManager extends GenericManager<UserData, String> {

    UserData getByUserName(String userName) throws Exception;
}
