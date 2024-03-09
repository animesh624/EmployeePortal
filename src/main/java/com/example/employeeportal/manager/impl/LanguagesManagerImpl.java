package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.LanguagesManager;
import com.example.employeeportal.model.Languages;
import com.example.employeeportal.repo.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguagesManagerImpl extends GenericManagerImpl<Languages,String> implements LanguagesManager {

    LanguageRepo languageRepo;

    @Autowired
    public void setLanguageRepo(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
        this.repository = languageRepo;
    }

    @Override
    public List<Languages> getAllByUserEmail(String userEmail) throws Exception{
        return languageRepo.findAllByUserEmail(userEmail);
    }
}
