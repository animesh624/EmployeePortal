package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.SkillsManager;
import com.example.employeeportal.model.Skills;
import com.example.employeeportal.repo.SkillsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SkillsManagerImpl extends GenericManagerImpl<Skills,String> implements SkillsManager {

    SkillsRepo skillsRepo;

    @Autowired
    public void setSkillsRepo(SkillsRepo skillsRepo){
        this.skillsRepo = skillsRepo;
        this.repository = skillsRepo;
    }

    @Override
    public List<String> getAllRoleIdByUserEmail (String userEmail) throws Exception{
        return skillsRepo.getAllRoleIdByUserEmail(userEmail);
    }
}
