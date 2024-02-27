package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.UserData;
import com.example.employeeportal.repo.UserDataRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDataManagerImpl implements UserDataManager{
    UserDataRepo userDataRepo;

    @Override
    public UserData getByUserName(String userName) throws Exception {
        return userDataRepo.getFirstByUserName(userName);
    }
}
