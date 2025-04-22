package com.employeemanagement.controller;

import com.employeemanagement.dto.PayRollDTO;
import com.employeemanagement.entity.PayRoll;
import com.employeemanagement.service.PayRollService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payrolls")
public class PayRollController {

    @Autowired
    private PayRollService payRollService;

    @PostMapping("/generate/{id}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<PayRoll> generatePayRoll(@PathVariable Long id,@RequestBody PayRollDTO payRollDTO){
        PayRoll payRoll = payRollService.generatePayRoll(id,payRollDTO);
        return new ResponseEntity<>(payRoll, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<PayRoll> updatePayRoll(@PathVariable Long id, @RequestBody @Valid PayRollDTO payRollDTO){
        PayRoll payRoll = payRollService.updatePayRoll(id,payRollDTO);
        return new ResponseEntity<>(payRoll,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('Employee')")
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
