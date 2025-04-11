package com.employeeManagement.controller;

import com.employeeManagement.dto.LeaveRequestDTO;
import com.employeeManagement.dto.UserInfoDTO;
import com.employeeManagement.entity.LeaveRequest;
import com.employeeManagement.service.LeaveRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
public class LeaveRequestController {

    @Autowired
    private LeaveRequestService leaveRequestService;

    @PostMapping("/apply")
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
    public ResponseEntity<LeaveRequest> approveLeave(@PathVariable Long id, @RequestBody UserInfoDTO userInfoDTO){
        LeaveRequest leaveRequest = leaveRequestService.approveLeave(id,userInfoDTO);
        return new ResponseEntity<>(leaveRequest,HttpStatus.OK);
    }
    @PutMapping("/reject/{id}")
    public ResponseEntity<LeaveRequest> rejectLeave(@PathVariable Long id,@RequestBody UserInfoDTO userInfoDTO){
        LeaveRequest leaveRequest=leaveRequestService.rejectLeave(id,userInfoDTO);
        return new ResponseEntity<>(leaveRequest,HttpStatus.OK);
    }
    @PutMapping("/cancel/{id}")
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
