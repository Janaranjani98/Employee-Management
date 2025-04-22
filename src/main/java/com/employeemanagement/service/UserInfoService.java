package com.employeemanagement.service;

import com.employeemanagement.dto.UserInfoDTO;
import com.employeemanagement.entity.UserInfo;
import com.employeemanagement.repository.UserInfoRepository;
import com.employeemanagement.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
   private PasswordEncoder passwordEncoder;

//    public UserInfoService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    public String registerUser(UserInfoDTO userDto){
       if(userRepository.findByUserName(userDto.getUserName()).isPresent()){
    throw new RuntimeException("User name already exists");
        }
        UserInfo user = new UserInfo();
        user.setUserName(userDto.getUserName());
        user.setEmailId(userDto.getEmailId());
       // user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(userDto.getRole());
        userRepository.save(user);
        return "User Registered Successfully";
    }

    public String authenticateUser(String name, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(name,password)
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails);
    }

    public UserInfo updateUser(Long id,UserInfoDTO userDto){
        UserInfo user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setUserName(userDto.getUserName());
        user.setEmailId(userDto.getEmailId());
        return userRepository.save(user);
    }

    public UserInfo getUserById(Long id){
        UserInfo user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return  user;
    }

    public List<UserInfo> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }


}
