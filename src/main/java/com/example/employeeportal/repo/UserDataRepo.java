package com.example.employeeportal.repo;

import com.example.employeeportal.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserDataRepo extends JpaRepository<UserData, String> {
    UserData getFirstByUserEmail(String userEmail);

    @Transactional
    @Modifying
    @Query("update UserData u set u.password = ?2 where u.userEmail = ?1")
    void updatePassword(String userEmail, String password);
}
