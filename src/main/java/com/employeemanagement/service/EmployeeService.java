package com.employeemanagement.service;

import com.employeemanagement.dto.EmployeeDTO;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository empRepository;

    public List<Employee> getAllEmployee(){
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
        return emp.orElseThrow(() -> new RuntimeException("Employee not found"));

    }

    public void deleteById(long id){

    empRepository.deleteById(id);
    }


}
