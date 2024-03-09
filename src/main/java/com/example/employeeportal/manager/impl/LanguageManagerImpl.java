package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.GenericManager;
import com.example.employeeportal.manager.LanguageManager;
import com.example.employeeportal.model.Language;
import com.example.employeeportal.repo.LanguageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageManagerImpl extends GenericManagerImpl<Language,String> implements LanguageManager {

    LanguageRepo languageRepo;

    @Autowired
    public void setLanguageRepo(LanguageRepo languageRepo) {
        this.languageRepo = languageRepo;
        this.repository = languageRepo;
    }

    @Override
    public List<Language> findAllByUserEmail (String userEmail) throws Exception{
        return languageRepo.findAllByUserEmail(userEmail);
    }
}
