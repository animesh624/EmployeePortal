package com.example.employeeportal.manager;

import com.example.employeeportal.model.UserData;

public interface UserDataManager extends GenericManager<UserData, String> {

    UserData getByUserEmail(String userEmail) throws Exception;
}
