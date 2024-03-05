package com.example.employeeportal.services.impl;

import com.example.employeeportal.dto.*;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.UserData;
import com.example.employeeportal.services.EmployeeService;
import com.example.employeeportal.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Arrays;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDataManager employeeDataManager;

    @Autowired
    JWTUtil jwtUtil;

    @Override
    public ResponseEntity<Object> getByUserName(GetEmployeeDto getEmployeeDto, String token) throws Exception{
        if(!jwtUtil.isTokenValid(token,getEmployeeDto.getRequestUserName())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        EmployeeData employeeData = employeeDataManager.getByUserName(getEmployeeDto.getUserName());

        if(employeeData == null){
            log.error("User doesnt exist with userName " + getEmployeeDto.getRequestUserName());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data",employeeData);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    // to be convered if allowed
    @Override
    public EmployeeData editEmployee(EmployeeDto employeeDto) throws Exception{
        EmployeeData employeeData = employeeDataManager.getByUserName(employeeDto.getUserName());
        if(employeeData == null){
            log.error("User doesnt exist with userName " + employeeDto.getUserName());
            return null;
        }
        employeeData.setLevel(employeeDto.getLevel());
        employeeData.setDesignation(employeeDto.getDesignation());
        employeeData.setContactNumber(employeeDto.getContactNumber());
        employeeDataManager.save(employeeData);

        return employeeData;
    }

    @Override
    public ResponseEntity<Object> searchEmployee(SearchEmployeeDto searchEmployeeDto,String token) throws Exception{
         if(!jwtUtil.isTokenValid(token,searchEmployeeDto.getRequestedUserName())) {
             return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
         }
         Map<String,Object> result = new HashMap<>();
         result.put("data",employeeDataManager.searchEmployee(searchEmployeeDto.getKeyword()));
         return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @Override
    public TreeEmployeeResponseDto treeExpandEmployee(EmployeeCodeDto employeeCodeDto, String token) throws Exception{
        TreeEmployeeDto manager = new TreeEmployeeDto("Emily Davis", "204", "SDE-2");
        List<TreeEmployeeDto> children = Arrays.asList(
                new TreeEmployeeDto("Hello hfd", "205", "SDE-1"),
                new TreeEmployeeDto("Michael Williams", "206", "SDE-1")
        );

        // Create the response object
        TreeEmployeeResponseDto response = new TreeEmployeeResponseDto();
        response.setManager(manager);
        response.setChildren(children);

        return response;
    }


}
