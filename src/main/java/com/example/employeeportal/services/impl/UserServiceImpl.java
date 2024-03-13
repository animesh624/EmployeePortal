package com.example.employeeportal.services.impl;

import com.example.employeeportal.dto.GetEmailDto;
import com.example.employeeportal.dto.LoginUserDto;
import com.example.employeeportal.dto.MailBody;
import com.example.employeeportal.dto.RegisterUserDto;
import com.example.employeeportal.facade.S3Facade;
import com.example.employeeportal.facade.UserDataFacade;
import com.example.employeeportal.manager.EmployeeDataManager;
import com.example.employeeportal.manager.ManagerReporteeManager;
import com.example.employeeportal.manager.UserDataManager;
import com.example.employeeportal.model.EmployeeData;
import com.example.employeeportal.model.ForgotPasswordData;
import com.example.employeeportal.model.UserData;
import com.example.employeeportal.repo.ForgotPasswordRepo;
import com.example.employeeportal.repo.UserDataRepo;
import com.example.employeeportal.services.EmailService;
import com.example.employeeportal.services.UserService;
import com.example.employeeportal.util.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserDataManager userDataManager;

    private EmployeeDataManager employeeDataManager;

    private JWTUtil jwtUtil;

    private ManagerReporteeManager managerReporteeManager;

    private UserDataFacade userDataFacade;

    private EmailService emailService;

    private ForgotPasswordRepo forgotPasswordRepo;

    private UserDataRepo userDataRepo;

    private S3Facade s3Facade;

    @Autowired
    public UserServiceImpl(UserDataManager userDataManager,
                         EmployeeDataManager employeeDataManager,
                         JWTUtil jwtUtil,
                         ManagerReporteeManager managerReporteeManager,
                         UserDataFacade userDataFacade,
                         EmailService emailService,
                         ForgotPasswordRepo forgotPasswordRepo,
                         UserDataRepo userDataRepo,
                         S3Facade s3Facade) {
        this.userDataManager = userDataManager;
        this.employeeDataManager = employeeDataManager;
        this.jwtUtil = jwtUtil;
        this.managerReporteeManager = managerReporteeManager;
        this.userDataFacade = userDataFacade;
        this.emailService = emailService;
        this.forgotPasswordRepo = forgotPasswordRepo;
        this.userDataRepo = userDataRepo;
        this.s3Facade = s3Facade;
    }

    private static BCryptPasswordEncoder bCryptPasswordEncoder;

    private static ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        objectMapper = new ObjectMapper();
    }

    @Override
    public ResponseEntity<Object> isLoggedIn(GetEmailDto getEmailDto, String token) throws Exception{

        if(!jwtUtil.isTokenValid(token,getEmailDto.getUserEmail())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> login(LoginUserDto loginUserDto) throws Exception{

        Map<String, Object> result = new HashMap<>();
        UserData userData = userDataManager.getByUserEmail(loginUserDto.getUserEmail());
        if(userData == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isEmpty(loginUserDto.getPassword()) || !bCryptPasswordEncoder.matches(loginUserDto.getPassword(), userData.getPassword())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String token = jwtUtil.createJWTToken(loginUserDto.getUserEmail(),"TokenForEmployeePortal");
        result.put("data", token);
        result.put("isAdmin",userData.getIsAdmin());
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> register(MultipartFile file, String data, String token) throws Exception{

        RegisterUserDto registerUserDto = objectMapper.readValue(data, RegisterUserDto.class);
        log.info("Animesh printint {}",registerUserDto);
        if(!jwtUtil.isTokenValid(token,registerUserDto.getRequestUserEmail())
                || !(userDataManager.getByUserEmail(registerUserDto.getRequestUserEmail()).getIsAdmin())){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(employeeDataManager.getByUserEmail(registerUserDto.getUserEmail()) != null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String fileUrl = s3Facade.uploadFile(file);
        userDataFacade.saveEntryInUserData(registerUserDto);
        userDataFacade.saveEntryInEmployeeData(registerUserDto,fileUrl);
        userDataFacade.createMapping(registerUserDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> forgotPassword(GetEmailDto getEmailDto) throws Exception{
        String userEmail = getEmailDto.getUserEmail();

        EmployeeData employeeData = employeeDataManager.getByUserEmail(userEmail);

        if (userEmail == null || employeeData == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        String otp = String.valueOf(otpGenerator());

        MailBody mailBody = MailBody.builder()
                .to(userEmail)
                .text("This is the otp for your forgot password Request : " + otp)
                .subject("OTP for employeePortal")
                .build();

        ForgotPasswordData fp = ForgotPasswordData.builder()
                .userEmail(userEmail)
                .otp(otp)
                .expirationTime(new Date(System.currentTimeMillis() + 300 * 1000))
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepo.save(fp);

        return ResponseEntity.ok("Email Sent for Verification");
    }

    private Integer otpGenerator() {
        Random random = new Random();
        int min = 100000;
        int max = 999999;
        return random.nextInt(max - min + 1) + min;
    }

    @Override
    public ResponseEntity<Object> verifyOtp(String otp, GetEmailDto getEmailDto) throws Exception{
        String userEmail = getEmailDto.getUserEmail();
        ForgotPasswordData fp = forgotPasswordRepo.findByOtpAndUser(otp,userEmail);
        if (fp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with this otp");
        }
        if (fp.getExpirationTime().before(Date.from(Instant.now()))) {
            forgotPasswordRepo.deleteById(fp.getId());
            return ResponseEntity.status(HttpStatus.GONE).body("OTP expired");
        }
        return ResponseEntity.ok("Otp Verified");
    }

    @Override
    public ResponseEntity<Object> resetPassword(String otp, LoginUserDto loginUserDto) throws Exception{
        String userEmail = loginUserDto.getUserEmail();
        String userPassword = loginUserDto.getPassword();

        ForgotPasswordData fp = forgotPasswordRepo.findByOtpAndUser(otp,userEmail);
        if (fp == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with this otp");
        }

        userDataRepo.updatePassword(userEmail,bCryptPasswordEncoder.encode(userPassword));
        forgotPasswordRepo.deleteById(fp.getId());

        return ResponseEntity.ok("Changed password");
    }
}
