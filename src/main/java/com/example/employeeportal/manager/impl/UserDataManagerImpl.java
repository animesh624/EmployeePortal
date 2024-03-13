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

    @Autowired
    public void setUserDataRepo(UserDataRepo userDataRepo){
        this.userDataRepo = userDataRepo;
        this.repository = userDataRepo;
    }

    @Override
    public UserData getByUserEmail(String userEmail) throws Exception {
        return userDataRepo.getFirstByUserEmail(userEmail);
    }

    @Override
    public void deleteByUserEmail(String userEmail) throws Exception{
        userDataRepo.deleteByUserEmail(userEmail);
    }
}
