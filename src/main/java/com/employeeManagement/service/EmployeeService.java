package com.employeeManagement.service;

import com.employeeManagement.dto.EmployeeDTO;
import com.employeeManagement.dto.UserInfoDTO;
import com.employeeManagement.entity.Employee;
import com.employeeManagement.entity.Role;
import com.employeeManagement.entity.UserInfo;
import com.employeeManagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository empRepository;

    public List<Employee> getAllEmployee(UserInfoDTO userInfoDTO){
        if(userInfoDTO.getRole()!=Role.Admin && userInfoDTO.getRole()!=Role.HR){
            throw new RuntimeException("Don't have required Permissions");
        }

        return empRepository.findAll();
    }

    public String addEmployee(EmployeeDTO employee){
        Optional<Employee> emp=empRepository.findByEmail(employee.getEmail());
        if(emp.isPresent()){
            return "Employee with email" +employee.getEmail() +"already exists";
        }



        Employee employee1=new Employee();
        employee1.setName(employee.getName());
        employee1.setEmail(employee.getEmail());
        employee1.setJobTitle(employee.getJobTitle());
        employee1.setDepartment(employee.getDepartment());
        employee1.setSalary(employee.getSalary());
        employee1.setUser(employee.getUser());


        empRepository.save(employee1);
        return "Employee added successfully";
    }

    public Employee getById(long id){
        Optional<Employee> emp= empRepository.findById(id);
        Employee employee = emp.orElseThrow(() -> new RuntimeException("Employee not found"));
        return employee;
    }

    public void deleteById(long id,UserInfoDTO userInfoDTO){
        if(userInfoDTO.getRole()!=Role.Admin){
            throw new RuntimeException("Don't have required Permissions");
        }
        empRepository.deleteById(id);
    }
//Pending item update and delete an employee

}
