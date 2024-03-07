package com.example.employeeportal.services.impl;

import com.example.employeeportal.dto.EmployeeDto;
import com.example.employeeportal.dto.GetEmployeeDto;
import com.example.employeeportal.dto.SearchEmployeeDto;
import com.example.employeeportal.facade.S3Facade;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.services.EmployeeService;
import com.example.employeeportal.util.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDataManager employeeDataManager;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    S3Facade s3Facade;

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
    public ResponseEntity<Object> uploadFile(MultipartFile file) throws Exception{

        Map<String,Object> result = new HashMap<>();
        result.put("data",s3Facade.uploadFile(file));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> downloadFile(String fileName) throws Exception{
        File file = s3Facade.downloadFile(fileName);
        Path path = Paths.get(file.getAbsolutePath());
        ByteArrayResource resource;
        try {
            resource = new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }

}
