package com.employeeManagement.controller;

import com.employeeManagement.dto.PayRollDTO;
import com.employeeManagement.dto.UserInfoDTO;
import com.employeeManagement.entity.PayRoll;
import com.employeeManagement.service.PayRollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payrolls")
public class PayRollController {

    @Autowired
    private PayRollService payRollService;

    @PostMapping("/generate/{id}")
    public ResponseEntity<PayRoll> generatePayRoll(@PathVariable Long id,@RequestBody UserInfoDTO userInfoDTO){
        PayRoll payRoll = payRollService.generatePayRoll(id,userInfoDTO);
        return new ResponseEntity<>(payRoll, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PayRoll> updatePayRoll(@PathVariable Long id, @RequestBody @Valid PayRollDTO payRollDTO, @RequestBody UserInfoDTO userInfoDTO){
        PayRoll payRoll = payRollService.updatePayRoll(id,payRollDTO,userInfoDTO);
        return new ResponseEntity<>(payRoll,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PayRoll> getPayRollByEmployee(@PathVariable @Valid Long id){
        PayRoll payRoll = payRollService.getPayRollByEmployee(id);
        return new ResponseEntity<>(payRoll,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @Valid Long id){
        payRollService.deletePayroll(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
