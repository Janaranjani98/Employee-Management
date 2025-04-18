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
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody @Valid UserInfoDTO userInfoDTO){
        String userInfo =userInfoService.registerUser(userInfoDTO);
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
