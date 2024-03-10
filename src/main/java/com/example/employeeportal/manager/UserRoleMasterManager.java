package com.example.employeeportal.manager;

import com.example.employeeportal.model.UserRoleMaster;

import java.util.List;

public interface UserRoleMasterManager extends GenericManager<UserRoleMaster,String> {

    List<String> getAllNameByRoleId(List<String> roleId) throws Exception;

    List<String> getAllRoleIdByName(List<String> names) throws Exception;

    String getRoleIdByName (String name) throws Exception;
}
