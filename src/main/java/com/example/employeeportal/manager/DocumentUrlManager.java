package com.example.employeeportal.manager;

import com.example.employeeportal.model.DocumentUrl;

import java.util.List;

public interface DocumentUrlManager extends GenericManager<DocumentUrl,String> {

    List<Object> getAllByUserEmail (String userEmail) throws Exception;
}
