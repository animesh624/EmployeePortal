package com.example.employeeportal.manager;

import com.example.employeeportal.model.Language;

import java.util.List;

public interface LanguageManager extends GenericManager<Language,String> {

    List<Language> findAllByUserEmail (String userEmail) throws Exception;
}
