package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.InterestsManager;
import com.example.employeeportal.model.Interests;
import com.example.employeeportal.repo.InterestsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InterestsManagerImpl extends GenericManagerImpl<Interests,String> implements InterestsManager {

    InterestsRepo interestsRepo;

    @Autowired
    public void setInterestsRepo(InterestsRepo interestsRepo) {
        this.interestsRepo = interestsRepo;
        this.repository = interestsRepo;
    }

    @Override
    public List<Interests> getAllByUserEmail (String userEmail) throws Exception{
        return interestsRepo.findAllByUserEmail(userEmail);
    }


}
