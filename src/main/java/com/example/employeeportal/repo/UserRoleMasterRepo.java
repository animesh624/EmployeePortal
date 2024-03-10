package com.example.employeeportal.repo;

import com.example.employeeportal.model.UserRoleMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMasterRepo extends JpaRepository<UserRoleMaster,String> {

    @Query(value="SELECT u.name  FROM user_role_master_temp u WHERE u.role_id IN ?1", nativeQuery = true)
    List<String> getAllNameByRoleId(List<String> roleId) throws Exception;

    @Query(value="SELECT u.role_id  FROM user_role_master_temp u WHERE u.name IN ?1", nativeQuery = true)
    List<String> getAllRoleIdByName(List<String> names) throws Exception;

    @Query(value="SELECT u.role_id  FROM user_role_master_temp u WHERE u.name = ?1", nativeQuery = true)
    String getRoleIdByName(String names) throws Exception;

}
