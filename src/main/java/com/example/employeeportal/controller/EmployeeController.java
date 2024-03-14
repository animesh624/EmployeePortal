package com.example.employeeportal.controller;

import com.example.employeeportal.dto.EditEmployeeDto;
import com.example.employeeportal.dto.FeedbackDto;
import com.example.employeeportal.dto.GetEmailDto;
import com.example.employeeportal.dto.GetEmployeeDto;
import com.example.employeeportal.dto.GetNeighboursDto;
import com.example.employeeportal.dto.UploadDocumentDto;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.UserData;
import com.example.employeeportal.services.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping("/getByUserEmail")
    public ResponseEntity<Object> getByUserEmail(@RequestBody GetEmployeeDto getEmployeeDto, @RequestHeader String token) throws Exception{
        return employeeService.getByUserEmail(getEmployeeDto,token);
    }
    @PostMapping("/edit/employee")
    public ResponseEntity<Object> editEmployee(@RequestBody EditEmployeeDto editEmployeeDto, @RequestHeader String token) throws Exception{
        return employeeService.editEmployee(editEmployeeDto,token);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> searchEmployee(@RequestParam(value = "name", required = false) String name,
                                                 @RequestParam(value = "designation", required = false) String designation,
                                                 @RequestParam(value = "skill", required = false) String skill,
                                                 @RequestParam(value = "language", required = false) String language,
                                                 @RequestParam(value = "interest", required = false) String interest,
                                                 @RequestParam(value = "user_email", required = false) String userEmail) throws Exception {
        return employeeService.searchEmployee(name, designation, userEmail, skill, language, interest);
    }

    @PostMapping("/get-reportees")
    public ResponseEntity<Object> getReportees(@RequestBody GetEmailDto getMailDto, @RequestHeader String token) throws Exception{
        return employeeService.getReportees(getMailDto,token);
    }

    @PostMapping("/getNeighbours")
    public ResponseEntity<Object> getNeighbours(@RequestBody GetNeighboursDto getNeighboursDto, @RequestHeader String token) throws Exception{
        return employeeService.getNeighbours(getNeighboursDto,token);
    }

    // TODO seperate folder for pdf and images.
    @PostMapping(path = "/uploadDocument")
    public ResponseEntity<Object> uploadDocument(@RequestParam("file") MultipartFile file,
                                             @RequestParam("data") String data,
                                             @RequestHeader String token) throws Exception {
        return employeeService.uploadDocument(file,data,token);
    }

    @GetMapping(path = "/downloadFile")
    public ResponseEntity<Object> downloadFile(@RequestParam(value = "file") String fileName) throws Exception {
        return employeeService.downloadFile(fileName);
    }

    // this is only for testing purpose.

    @PostMapping("/feedback")
    public ResponseEntity<Object> submitFeedback(@RequestParam(value = "type") String type, @RequestBody FeedbackDto feedbackRequest, @RequestHeader String token) throws Exception {
        return employeeService.saveFeedback(type,feedbackRequest,token);
    }

    @PostMapping("/get-all")
    public ResponseEntity<Object> getAll(@RequestBody GetEmailDto getMailDto, @RequestHeader String token) throws Exception{
        return employeeService.getAll(getMailDto,token);
    }

    @PostMapping("/deleteEmployee")
    public ResponseEntity<Object> deleteNode(@RequestBody GetEmailDto getEmailDto, @RequestHeader String token) throws Exception{
        return employeeService.deleteEmployee(getEmailDto,token);
    }

    @GetMapping(path="/downloadCheck")
    public ResponseEntity<Object> downloadCheck() throws Exception{
        Map<String, Object> result = new HashMap<>();
        result.put("url","https://employee-portal-file.s3.ap-south-1.amazonaws.com/1710002013462-aefa701f1a7a22d4c4ff6d63486f781e.jpg");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
