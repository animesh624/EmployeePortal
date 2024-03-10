package com.example.employeeportal.services.impl;

import com.example.employeeportal.dto.*;
import com.example.employeeportal.facade.DocumentUrlFacade;
import com.example.employeeportal.facade.EmployeeDataFacade;
import com.example.employeeportal.facade.ManagerReporteeFacade;
import com.example.employeeportal.facade.S3Facade;
import com.example.employeeportal.manager.DocumentUrlManager;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.InterestsManager;
import com.example.employeeportal.manager.LanguagesManager;
import com.example.employeeportal.manager.ManagerReporteeManager;
import com.example.employeeportal.manager.SkillsManager;
import com.example.employeeportal.model.DocumentUrl;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.Interests;
import com.example.employeeportal.model.Languages;
import com.example.employeeportal.model.Skills;
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
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDataManager employeeDataManager;

    @Autowired
    ManagerReporteeManager managerReporteeManager;

    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    S3Facade s3Facade;

    @Autowired
    EmployeeDataFacade employeeDataFacade;

    @Autowired
    SkillsManager skillsManager;

    @Autowired
    LanguagesManager languagesManager;

    @Autowired
    InterestsManager interestsManager;

    @Autowired
    DocumentUrlManager documentUrlManager;

    @Autowired
    ManagerReporteeFacade managerReporteeFacade;

    @Autowired
    DocumentUrlFacade documentUrlFacade;

    @Override
    public ResponseEntity<Object> getByUserEmail(GetEmployeeDto getEmployeeDto, String token) throws Exception{
        if(!jwtUtil.isTokenValid(token,getEmployeeDto.getRequestUserEmail())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        EmployeeData employeeData = employeeDataManager.getByUserEmail(getEmployeeDto.getUserEmail());
        List<Interests> interests = interestsManager.getAllByUserEmail(employeeData.getUserEmail());
        List<Languages> languages = languagesManager.getAllByUserEmail(employeeData.getUserEmail());
        List<Skills> skills = skillsManager.getAllByUserEmail(employeeData.getUserEmail());
        List<Object> documentUrls = documentUrlManager.getAllByUserEmail(employeeData.getUserEmail());

        if(employeeData == null){
            log.error("User doesnt exist with userEmail " + getEmployeeDto.getRequestUserEmail());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data",employeeData);
        result.put("languages",languages);
        result.put("skills",skills);
        result.put("interests",interests);
        result.put("documentUrls",documentUrls);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> editEmployee(EditEmployeeDto editEmployeeDto, String token) throws Exception{
        if(!jwtUtil.isTokenValid(token,editEmployeeDto.getRequestedUserEmail())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        EmployeeData employeeData = employeeDataManager.getByUserEmail(editEmployeeDto.getUserEmail());
        if(employeeData == null){
            log.error("User doesnt exist with userEmail " + editEmployeeDto.getUserEmail());
            return null;
        }
        employeeDataFacade.saveEditEmployeeDetails(employeeData,editEmployeeDto);
        employeeDataFacade.saveSkillsLanguagesInterests(editEmployeeDto.getUserEmail(),editEmployeeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> searchEmployee(String name, String designation, String expertise ,String userEmail, String token) throws Exception{
         Map<String,Object> result = new HashMap<>();
         result.put("data",employeeDataManager.searchEmployee(name,designation,expertise,userEmail));
         return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @Override
    public  ResponseEntity<Object> getNeighbours(GetNeighboursDto getNeighboursDto, String token) throws Exception{
            if(!jwtUtil.isTokenValid(token,getNeighboursDto.getRequestUserEmail())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            ManagerReporteeResponseDto managerReporteeResponseDto = new ManagerReporteeResponseDto();
            managerReporteeResponseDto.setManager(managerReporteeFacade.getDetailsForEmployeeManager(getNeighboursDto));
            managerReporteeResponseDto.setReportee(managerReporteeFacade.getDetailsForEmployeeReportee(getNeighboursDto));
            managerReporteeResponseDto.setNode(managerReporteeFacade.getDetailsForNode(getNeighboursDto));
            Map<String, Object> result = new HashMap<>();
            result.put("data", managerReporteeResponseDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> uploadDocument(MultipartFile file, UploadDocumentDto uploadDocumentDto, String token) throws Exception{

        if(!jwtUtil.isTokenValid(token,uploadDocumentDto.getRequestedUserEmail())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String fileUrl = s3Facade.uploadFile(file);
        documentUrlFacade.builDocumentUrlData(uploadDocumentDto,fileUrl);
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
