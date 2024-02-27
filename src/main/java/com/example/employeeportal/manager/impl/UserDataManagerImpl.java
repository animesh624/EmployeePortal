package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.GenericManager;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.UserData;
import com.example.employeeportal.repo.UserDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDataManagerImpl extends GenericManagerImpl<UserData,String> implements UserDataManager{
    UserDataRepo userDataRepo;

    @Override
    public UserData getByUserName(String userName) throws Exception {
        return userDataRepo.getFirstByUserName(userName);
    }
}
