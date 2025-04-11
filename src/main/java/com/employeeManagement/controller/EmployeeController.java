package com.employeeManagement.controller;

import com.employeeManagement.dto.EmployeeDTO;
import com.employeeManagement.dto.UserInfoDTO;
import com.employeeManagement.entity.Employee;
import com.employeeManagement.repository.EmployeeRepository;
import com.employeeManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(@RequestBody UserInfoDTO userInfoDTO){
        List<Employee> emp = employeeService.getAllEmployee(userInfoDTO);
        return new ResponseEntity<>(emp, HttpStatus.OK);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee emp = employeeService.getById(id);
        return new ResponseEntity<>(emp,HttpStatus.OK);
    }
    @PostMapping("/employee/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody EmployeeDTO employeeDTO){
        employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> deleteById(@RequestBody UserInfoDTO userInfoDTO,@PathVariable Long id){
        employeeService.deleteById(id,userInfoDTO);
        return new ResponseEntity<>(HttpStatus.OK);

    }

}
