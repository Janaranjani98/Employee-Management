package com.employeemanagement.service;

import com.employeemanagement.dto.PayRollDTO;
import com.employeemanagement.entity.Employee;
import com.employeemanagement.entity.PayRoll;
import com.employeemanagement.repository.EmployeeRepository;
import com.employeemanagement.repository.PayRollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayRollService {

    @Autowired
    private PayRollRepository payRepository;

    @Autowired
    private EmployeeRepository empRepository;

    public PayRoll generatePayRoll(Long id,PayRollDTO payRollDTO){
        Employee emp = empRepository.findById(id)
                .orElseThrow(() ->new RuntimeException("Employee not found"));

//        if(userInfoDTO.getRole()!=Role.Employee){
//            throw new RuntimeException("You don't have required privileges");
//        }

        PayRoll payRoll = new PayRoll();
        payRoll.setBasicSalary(payRollDTO.getBasicSalary());
        payRoll.setDeductions(payRollDTO.getDeductions());
        payRoll.setNetSalary(payRollDTO.getNetSalary());
        payRoll.setPayDate(payRollDTO.getPayDate());
        payRoll.setEmployee(emp);

        return payRepository.save(payRoll);
    }

    public PayRoll updatePayRoll(Long id, PayRollDTO payDto){
        PayRoll payRoll=payRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Pay roll not found"));

//        if(userInfoDTO.getRole()!= Role.Admin && userInfoDTO.getRole()!=Role.HR){
//            throw new RuntimeException("Your don't have required privilieges");
//        }
        payRoll.setBasicSalary(payDto.getBasicSalary());
        payRoll.setDeductions(payDto.getDeductions());
        payRoll.setNetSalary(payDto.getBasicSalary().subtract(payDto.getDeductions()));
        return payRepository.save(payRoll);
    }

    public PayRoll getPayRollByEmployee(Long id){
        return payRepository.findByEmployeeId(id)
                .orElseThrow(()->new RuntimeException("No Employee found for this id"));
    }

    public void deletePayroll(Long id){
        payRepository.deleteById(id);
    }

}
