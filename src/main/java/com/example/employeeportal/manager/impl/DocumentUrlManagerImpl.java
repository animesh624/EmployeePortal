package com.example.employeeportal.manager.impl;

import com.example.employeeportal.manager.DocumentUrlManager;
import com.example.employeeportal.model.DocumentUrl;
import com.example.employeeportal.repo.DocumentUrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentUrlManagerImpl extends GenericManagerImpl<DocumentUrl,String> implements DocumentUrlManager {

    DocumentUrlRepo documentUrlRepo;

    @Autowired
    public void setDocumentUrlRepo(DocumentUrlRepo documentUrlRepo){
        this.documentUrlRepo = documentUrlRepo;
        this.repository = documentUrlRepo;
    }

    @Override
    public List<Object> getAllByUserEmail (String userEmail) throws Exception{
        return documentUrlRepo.findAllByUserEmail(userEmail);
    }

    @Override
    public DocumentUrl getByUserEmailAndName (String userEmail, String name) throws Exception{
        return documentUrlRepo.findFirstByUserEmailAndDocumentName(userEmail,name);
    }
}
