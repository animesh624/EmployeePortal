package com.example.employeeportal.manager;

import com.example.employeeportal.model.Interests;

import java.util.List;

public interface InterestsManager extends GenericManager<Interests,String> {
    List<Interests> getAllByUserEmail (String userEmail) throws Exception;
}
