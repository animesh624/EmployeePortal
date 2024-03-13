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
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.manager.UserRoleMasterManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.Feedback;
import com.example.employeeportal.repo.FeedbackRepo;
import com.example.employeeportal.services.EmployeeService;
import com.example.employeeportal.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
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

    private EmployeeDataManager employeeDataManager;
    private ManagerReporteeManager managerReporteeManager;
    private JWTUtil jwtUtil;
    private S3Facade s3Facade;
    private EmployeeDataFacade employeeDataFacade;
    private SkillsManager skillsManager;
    private LanguagesManager languagesManager;
    private InterestsManager interestsManager;
    private DocumentUrlManager documentUrlManager;
    private ManagerReporteeFacade managerReporteeFacade;
    private DocumentUrlFacade documentUrlFacade;
    private UserRoleMasterManager userRoleMasterManager;
    private UserDataManager userDataManager;
    private FeedbackRepo feedbackRepo;

    private static ObjectMapper objectMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeDataManager employeeDataManager,
                         ManagerReporteeManager managerReporteeManager,
                         JWTUtil jwtUtil,
                         S3Facade s3Facade,
                         EmployeeDataFacade employeeDataFacade,
                         SkillsManager skillsManager,
                         LanguagesManager languagesManager,
                         InterestsManager interestsManager,
                         DocumentUrlManager documentUrlManager,
                         ManagerReporteeFacade managerReporteeFacade,
                         DocumentUrlFacade documentUrlFacade,
                         UserRoleMasterManager userRoleMasterManager,
                         UserDataManager userDataManager,
                         FeedbackRepo feedbackRepo) {
        this.employeeDataManager = employeeDataManager;
        this.managerReporteeManager = managerReporteeManager;
        this.jwtUtil = jwtUtil;
        this.s3Facade = s3Facade;
        this.employeeDataFacade = employeeDataFacade;
        this.skillsManager = skillsManager;
        this.languagesManager = languagesManager;
        this.interestsManager = interestsManager;
        this.documentUrlManager = documentUrlManager;
        this.managerReporteeFacade = managerReporteeFacade;
        this.documentUrlFacade = documentUrlFacade;
        this.userRoleMasterManager = userRoleMasterManager;
        this.userDataManager = userDataManager;
        this.feedbackRepo = feedbackRepo;
    }

    @PostConstruct
    public void init(){
        objectMapper = new ObjectMapper();
    }

    @Override
    public ResponseEntity<Object> getByUserEmail(GetEmployeeDto getEmployeeDto, String token) throws Exception{
        if(!jwtUtil.isTokenValid(token,getEmployeeDto.getRequestUserEmail())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        EmployeeData employeeData = employeeDataManager.getByUserEmail(getEmployeeDto.getUserEmail());

        if(employeeData == null){
            log.error("User doesnt exist with userEmail " + getEmployeeDto.getRequestUserEmail());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<String> interestsIds = interestsManager.getAllRoleIdByUserEmail(employeeData.getUserEmail());
        List<String> languagesIds = languagesManager.getAllRoleIdByUserEmail(employeeData.getUserEmail());
        List<String> skillsIds = skillsManager.getAllRoleIdByUserEmail(employeeData.getUserEmail());

        employeeDataFacade.updateFrequency(employeeData,getEmployeeDto.isSearched());

        Map<String,Object> result = new HashMap<>();
        result.put("data",employeeData);
        result.put("languages",userRoleMasterManager.getAllNameByRoleId(languagesIds));
        result.put("skills",userRoleMasterManager.getAllNameByRoleId(skillsIds));
        result.put("interests",userRoleMasterManager.getAllNameByRoleId(interestsIds));
        result.put("documentUrls",documentUrlManager.getAllByUserEmail(employeeData.getUserEmail()));
        result.put("isAdmin",userDataManager.getByUserEmail(employeeData.getUserEmail()).getIsAdmin());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> editEmployee(EditEmployeeDto editEmployeeDto, String token) throws Exception{
        log.info("Animesh here {}",editEmployeeDto);
        if(!jwtUtil.isTokenValid(token,editEmployeeDto.getRequestedUserEmail())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        EmployeeData employeeData = employeeDataManager.getByUserEmail(editEmployeeDto.getUserEmail());
        if(employeeData == null){
            log.error("User doesnt exist with userEmail " + editEmployeeDto.getUserEmail());
            return null;
        }
        employeeDataFacade.saveEditEmployeeDetails(employeeData,editEmployeeDto);
        employeeDataFacade.saveEditUserDetails(editEmployeeDto);
        employeeDataFacade.saveSkillsLanguagesInterests(editEmployeeDto.getUserEmail(),editEmployeeDto);
        employeeDataFacade.saveProfileUrls(editEmployeeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> searchEmployee(String name, String designation, String expertise ,String userEmail, String token) throws Exception{
         Map<String,Object> result = new HashMap<>();

         List<EmployeeData> employeeData = employeeDataManager.searchEmployee(name,designation,expertise,userEmail);
         result.put("data",employeeData);
         return new ResponseEntity<>(result,HttpStatus.OK);

    }

    @Override
    public  ResponseEntity<Object> getNeighbours(GetNeighboursDto getNeighboursDto, String token) throws Exception{
            if(!jwtUtil.isTokenValid(token,getNeighboursDto.getRequestUserEmail())) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

            ManagerReporteeResponseDto managerReporteeResponseDto = ManagerReporteeResponseDto.builder()
                    .manager(managerReporteeFacade.getDetailsForEmployeeManager(getNeighboursDto))
                    .reportee(managerReporteeFacade.getDetailsForEmployeeReportee(getNeighboursDto))
                    .node(managerReporteeFacade.getDetailsForNode(getNeighboursDto))
                    .build();
            Map<String, Object> result = new HashMap<>();
            result.put("data", managerReporteeResponseDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> uploadDocument(MultipartFile file, String data, String token) throws Exception{
        UploadDocumentDto uploadDocumentDto = objectMapper.readValue(data, UploadDocumentDto.class);
        if(!jwtUtil.isTokenValid(token,uploadDocumentDto.getRequestedUserEmail())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String fileUrl = s3Facade.uploadFile(file);
        documentUrlFacade.saveDocumentUrlData(uploadDocumentDto,fileUrl);
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

    @Override
    public ResponseEntity<Object> saveFeedback(String type, FeedbackDto feedbackDto, String token) throws Exception {
        if(!jwtUtil.isTokenValid(token,feedbackDto.getUserEmail())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        EmployeeData employeeData = employeeDataManager.getByUserEmail(feedbackDto.getUserEmail());
        if(employeeData == null){
            log.error("User doesnt exist with userEmail " + feedbackDto.getUserEmail());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Feedback feedback = Feedback.builder()
                .userEmail(feedbackDto.getUserEmail())
                .type(type)
                .message(feedbackDto.getMessage())
                .build();

        feedbackRepo.save(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body("Feedback submitted successfully");
    }

    public ResponseEntity<Object> getAll(GetEmailDto getMailDto, String token) throws Exception{
        if(!jwtUtil.isTokenValid(token,getMailDto.getUserEmail())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<EmployeeData> listEmployeeData = employeeDataManager.getAll();

        if(listEmployeeData == null){
            log.error("User doesnt exist with userEmail " + getMailDto.getUserEmail());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data",listEmployeeData);

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
