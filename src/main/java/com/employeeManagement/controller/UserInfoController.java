package com.employeeManagement.controller;

import com.employeeManagement.dto.UserInfoDTO;
import com.employeeManagement.entity.UserInfo;
import com.employeeManagement.service.UserInfoService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/create")
    public ResponseEntity<UserInfo> createUser(@RequestBody @Valid UserInfoDTO userInfoDTO){
        UserInfo userInfo =userInfoService.registerUser(userInfoDTO);
        return new ResponseEntity<>(userInfo, HttpStatus.CREATED);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticateUser(@RequestBody @Valid UserInfoDTO userInfoDTO){
        String n =userInfoService.authenticateUser(userInfoDTO);
        return new ResponseEntity<>(n,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserInfo> updateUserInfo(@RequestBody @Valid UserInfoDTO userInfoDTO, @PathVariable Long id){
        UserInfo user = userInfoService.updateUser(id,userInfoDTO);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable Long id){
        UserInfo user= userInfoService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<List<UserInfo>> getAllUsers(@RequestBody UserInfoDTO userInfoDTO){
        List<UserInfo> userInfo=userInfoService.getAllUsers(userInfoDTO);
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userInfoService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
