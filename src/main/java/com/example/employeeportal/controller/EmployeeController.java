package com.example.employeeportal.controller;


import com.example.employeeportal.dto.*;
import com.example.employeeportal.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("/getByUserEmail")
    public ResponseEntity<Object> getByUserEmail(@RequestBody GetEmployeeDto getEmployeeDto, @RequestHeader String token) throws Exception{
        return employeeService.getByUserEmail(getEmployeeDto,token);
    }

    @PostMapping("/getAll")
    public ResponseEntity<Object> getAll(@RequestBody GetEmployeeDto getEmployeeDto, @RequestHeader String token) throws Exception{
        return employeeService.getAll(getEmployeeDto,token);
    }

    @PostMapping("/edit/employee")
    public ResponseEntity<Object> editEmployee(@RequestParam EditEmployeeDto editEmployeeDto, @RequestHeader String token) throws Exception{
        return employeeService.editEmployee(editEmployeeDto,token);
    }

    @PostMapping("/search")
    public ResponseEntity<Object> searchEmployee(@RequestParam(value = "name",required = false) String name,
                                                 @RequestParam(value = "designation",required = false) String designation,
                                                 @RequestParam(value = "expertise",required = false) String expertise,
                                                 @RequestParam(value = "user_email",required = false) String userEmail,
                                                 @RequestHeader String token) throws Exception {
        return employeeService.searchEmployee(name, designation, expertise, userEmail, token);
    }

    @PostMapping("/getNeighbours")
    public ResponseEntity<Object> getNeighbours(@RequestBody  GetNeighboursDto getNeighboursDto, @RequestHeader String token) throws Exception{
        return employeeService.getNeighbours(getNeighboursDto,token);
    }

    // TODO seperate folder for pdf and images.
    @PostMapping(path = "/uploadFile")
    public ResponseEntity<Object> uploadFile(@RequestParam(value = "file") MultipartFile file) throws Exception {
        return employeeService.uploadFile(file);
    }

    @GetMapping(path = "/downloadFile")
    public ResponseEntity<Object> downloadFile(@RequestParam(value = "file") String fileName) throws Exception {
        return employeeService.downloadFile(fileName);
    }
    @PostMapping("/feedback")
    public ResponseEntity<Object> submitFeedback(@RequestParam(value = "type") String type, @RequestBody FeedbackDto feedbackRequest, @RequestHeader String token) throws Exception {
        return employeeService.saveFeedback(type,feedbackRequest,token);
    }
}
