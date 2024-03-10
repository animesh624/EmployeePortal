package com.example.employeeportal.repo;

import com.example.employeeportal.model.ForgotPasswordData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepo extends JpaRepository<ForgotPasswordData,String> {
    @Query("select fp from ForgotPasswordData fp where fp.otp = ?1 and fp.userEmail = ?2")
    ForgotPasswordData findByOtpAndUser(String otp, String userEmail);
    ForgotPasswordData save(ForgotPasswordData forgotPasswordData);
}