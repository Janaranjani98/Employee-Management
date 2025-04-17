package com.employeeManagement.repository;

import com.employeeManagement.dto.UserInfoDTO;
import com.employeeManagement.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Long> {

    //Optional<UserInfo> findByEmailId(String email);
    Optional<UserInfo> findByUserName(String userName);
}
