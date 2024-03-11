package com.example.employeeportal.manager;

import com.example.employeeportal.model.Interests;

import java.util.List;

public interface InterestsManager extends GenericManager<Interests,String> {
    List<String> getAllRoleIdByUserEmail (String userEmail) throws Exception;

    Interests getByUserEmailAndRoleId(String userEmail,String roleId) throws Exception;
}
