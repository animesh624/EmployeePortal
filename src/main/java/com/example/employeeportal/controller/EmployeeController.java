package com.example.employeeportal.controller;


import com.example.employeeportal.dto.*;
import com.example.employeeportal.model.EmployeeData;
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

    @CrossOrigin(origins = "*")
    @PostMapping("/getByUserEmail")
    public ResponseEntity<Object> getByUserEmail(@RequestBody GetEmployeeDto getEmployeeDto, @RequestHeader String token) throws Exception{
        return employeeService.getByUserEmail(getEmployeeDto,token);
    }

    @PostMapping("/edit/employee")
    public ResponseEntity<Object> editEmployee(@RequestParam EditEmployeeDto editEmployeeDto, @RequestHeader String token) throws Exception{
        return employeeService.editEmployee(editEmployeeDto,token);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/search")
    public ResponseEntity<Object> searchEmployee(@RequestParam(value = "name") String name,
                                                 @RequestParam(value = "designation") String designation,
                                                 @RequestParam(value = "expertise") String expertise,
                                                 @RequestParam(value = "user_email") String userEmail,
                                                 @RequestHeader String token) throws Exception {
        return employeeService.searchEmployee(name, designation, expertise, userEmail, token);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/getNeighbours")
    public ResponseEntity<Object> getNeighbours(@RequestBody  GetNeighboursDto getNeighboursDto, @RequestHeader String token) throws Exception{
        return employeeService.getNeighbours(getNeighboursDto,token);
    }

    // TODO seperate folder for pdf and images.
    @CrossOrigin(origins = "*")
    @PostMapping(path = "/uploadFile")
    public ResponseEntity<Object> uploadFile(@RequestParam(value = "file") MultipartFile file) throws Exception {
        return employeeService.uploadFile(file);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/downloadFile")
    public ResponseEntity<Object> downloadFile(@RequestParam(value = "file") String fileName) throws Exception {
        return employeeService.downloadFile(fileName);
    }
}
