package com.employeeManagement.service;

import com.employeeManagement.dto.UserInfoDTO;
import com.employeeManagement.entity.Role;
import com.employeeManagement.entity.UserInfo;
import com.employeeManagement.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public UserInfoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserInfo registerUser(UserInfoDTO userDto){
        UserInfo user = new UserInfo();
        user.setName(userDto.getName());
        user.setEmailId(userDto.getEmailId());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        return userRepository.save(user);
    }

    public String authenticateUser(UserInfoDTO userDto){
        UserInfo user = userRepository.findByEmailId(userDto.getEmailId())
                .orElseThrow(() -> new RuntimeException("User not found"));
                return "User Authenticated";
    }

    public UserInfo updateUser(Long id,UserInfoDTO userDto){
        UserInfo user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        user.setEmailId(userDto.getEmailId());
        return userRepository.save(user);
    }

    public UserInfo getUserById(Long id){
        UserInfo user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return  user;
    }

    public List<UserInfo> getAllUsers(UserInfoDTO userInfoDTO){
        if(userInfoDTO.getRole()!=Role.Admin){
            throw new RuntimeException("You don't have required Permissions");
        }
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }


}
