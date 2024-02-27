package com.example.employeeportal.repo;

import com.example.employeeportal.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepo extends JpaRepository<UserData, String> {
    UserData getFirstByUserName(String userName);
}
