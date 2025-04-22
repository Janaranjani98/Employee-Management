package com.employeemanagement.controller;

import com.employeemanagement.dto.LeaveRequestDTO;
import com.employeemanagement.entity.LeaveRequest;
import com.employeemanagement.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping("/apply")
    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity<LeaveRequest> applyLeave(@RequestBody LeaveRequestDTO leaveRequestDTO){
        LeaveRequest leaveRequest =leaveRequestService.applyForLeave(leaveRequestDTO);
        return new ResponseEntity<>(leaveRequest, HttpStatus.CREATED);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<List<LeaveRequest>> getLeaveByEmployeeId(@PathVariable Long id){
        List<LeaveRequest> leaves = leaveRequestService.getLeaveByEmployee(id);
        return new ResponseEntity<>(leaves,HttpStatus.OK);
    }
    @PutMapping("/approve/{id}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<LeaveRequest> approveLeave(@PathVariable Long id){
        LeaveRequest leaveRequest = leaveRequestService.approveLeave(id);
        return new ResponseEntity<>(leaveRequest,HttpStatus.OK);
    }
    @PutMapping("/reject/{id}")
    @PreAuthorize("hasRole('HR')")
    public ResponseEntity<LeaveRequest> rejectLeave(@PathVariable Long id){
        LeaveRequest leaveRequest=leaveRequestService.rejectLeave(id);
        return new ResponseEntity<>(leaveRequest,HttpStatus.OK);
    }
    @PutMapping("/cancel/{id}")
    @PreAuthorize("hasRole('Employee')")
    public ResponseEntity<LeaveRequest> cancelLeave(@PathVariable Long id){
        LeaveRequest leaveRequest = leaveRequestService.cancelLeave(id);
        return new ResponseEntity<>(leaveRequest,HttpStatus.OK);
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LeaveRequest>> getByStatus(@PathVariable String status){
        List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveByStatus(status);
        return new ResponseEntity<>(leaveRequests,HttpStatus.OK);
    }

}
