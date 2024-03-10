package com.example.employeeportal.manager;

import com.example.employeeportal.model.Languages;

import java.util.List;

public interface LanguagesManager extends GenericManager<Languages,String> {

    List<String> getAllRoleIdByUserEmail (String userEmail) throws Exception;
}
