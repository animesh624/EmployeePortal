package com.example.employeeportal.repo;

import com.example.employeeportal.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepo extends JpaRepository<UserData, String> {
    UserData getFirstByUserEmail(String userEmail);
}
