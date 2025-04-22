package com.employeemanagement.controller;

import com.employeemanagement.dto.UserInfoDTO;
import com.employeemanagement.entity.UserInfo;
import com.employeemanagement.service.EmailService;
import com.employeemanagement.service.UserInfoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserInfoController {

    private final EmailService emailService;

    @Autowired
    private UserInfoService userInfoService;

    public UserInfoController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserInfoDTO userInfoDTO){
        String userInfo =userInfoService.registerUser(userInfoDTO);
        emailService.sendEmail(userInfoDTO.getEmailId(),"Welcome","Thanks for registering!!" +userInfoDTO.getUserName() +"!!");
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody Map<String,String> loginData){
        String token = userInfoService.authenticateUser(loginData.get("userName"),loginData.get("password"));
        //String n =userInfoService.authenticateUser(userInfoDTO);
        return new ResponseEntity<>(token,HttpStatus.OK);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<UserInfo> updateUserInfo(@RequestBody @Valid UserInfoDTO userInfoDTO, @PathVariable Long id){
        UserInfo user = userInfoService.updateUser(id,userInfoDTO);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable Long id){
        UserInfo user= userInfoService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping("/users")
    public ResponseEntity<List<UserInfo>> getAllUsers(){
        List<UserInfo> userInfo=userInfoService.getAllUsers();
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userInfoService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
