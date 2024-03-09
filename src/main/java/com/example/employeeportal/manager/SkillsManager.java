package com.example.employeeportal.manager;

import com.example.employeeportal.model.Skills;

import java.util.List;

public interface SkillsManager extends GenericManager<Skills,String> {

    List<Skills> getAllByUserEmail (String userEmail) throws Exception;

}
