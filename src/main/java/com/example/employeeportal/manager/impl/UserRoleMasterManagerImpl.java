package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.UserRoleMasterManager;
import com.example.employeeportal.model.UserRoleMaster;
import com.example.employeeportal.repo.UserRoleMasterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class UserRoleMasterManagerImpl extends GenericManagerImpl<UserRoleMaster,String> implements UserRoleMasterManager {

    UserRoleMasterRepo userRoleMasterRepo;

    @Autowired
    public void setUserRoleMasterRepo(UserRoleMasterRepo userRoleMasterRepo){
        this.userRoleMasterRepo = userRoleMasterRepo;
        this.repository = userRoleMasterRepo;
    }

    @Override
    public List<String> getAllNameByRoleId(List<String> roleId) throws Exception{
        return userRoleMasterRepo.getAllNameByRoleId(roleId);
    }

    @Override
    public List<String> getAllRoleIdByName(List<String> names) throws Exception{
        return userRoleMasterRepo.getAllRoleIdByName(names);
    }

    @Override
    public String getRoleIdByName (String name) throws Exception{
        return userRoleMasterRepo.getRoleIdByName(name);
    }
}
