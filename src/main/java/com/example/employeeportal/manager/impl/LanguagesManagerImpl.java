package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.LanguagesManager;
import com.example.employeeportal.model.Languages;
import com.example.employeeportal.repo.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguagesManagerImpl extends GenericManagerImpl<Languages, String> implements LanguagesManager {

    LanguageRepo languageRepo;

    @Autowired
    public void setLanguageRepo(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
        this.repository = languageRepo;
    }

    @Override
    public List<String> getAllRoleIdByUserEmail(String userEmail) throws Exception {
        return languageRepo.getAllRoleIdByUserEmail(userEmail);
    }

    @Override
    public Languages getByUserEmailAndRoleId(String userEmail, String roleId) throws Exception {
        return languageRepo.getFirstByUserEmailAndLanguage(userEmail, roleId);
    }

    @Override
    public void deleteAllByUserEmail(String userEmail) throws Exception{
          languageRepo.deleteAllByUserEmail(userEmail);
    }

}
