package com.employeeManagement.service;

import com.employeeManagement.dto.LeaveRequestDTO;
import com.employeeManagement.dto.UserInfoDTO;
import com.employeeManagement.entity.Employee;
import com.employeeManagement.entity.LeaveRequest;
import com.employeeManagement.entity.Role;
import com.employeeManagement.entity.Status;
import com.employeeManagement.repository.EmployeeRepository;
import com.employeeManagement.repository.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestRepository leaveRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    public LeaveRequest applyForLeave(LeaveRequestDTO request){
        Employee employee = employeeRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Employee Not found"));
        LeaveRequest l =new LeaveRequest();
        l.setEmployee(employee);
        l.setStartDate(request.getStartDate());
        l.setEndDate(request.getEndDate());
        l.setStatus(Status.Pending);
        l.setReason(request.getReason());
        return leaveRepository.save(l);
    }

    public LeaveRequest approveLeave(long id){
        LeaveRequest leaveRequest = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
//        if(userInfoDTO.getRole()!=Role.HR){
//            throw new RuntimeException("You dont have required priviliges");
//        }
        leaveRequest.setStatus(Status.Approved);
        return leaveRepository.save(leaveRequest);

    }
    public LeaveRequest rejectLeave(long id){
        LeaveRequest leaveRequest = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
//        if(userInfoDTO.getRole()!=Role.HR){
//            throw new RuntimeException("You dont have required priviliges");
//        }
        leaveRequest.setStatus(Status.Rejected);
        return leaveRepository.save(leaveRequest);

    }

    public LeaveRequest cancelLeave(long id){
        LeaveRequest leaveRequest = leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
        if(leaveRequest.getStatus()==Status.Pending) {
            leaveRequest.setStatus(Status.Cancelled);
            return leaveRepository.save(leaveRequest);
        }else{
            throw  new RuntimeException("Only pending leave request can be cancelled");
        }

    }
    public List<LeaveRequest> getLeaveByEmployee(Long id){
        return leaveRepository.findByEmployeeId(id);
    }

    public List<LeaveRequest> getLeaveByStatus(String status){
        return leaveRepository.findByStatus(Status.valueOf(status));
    }


}
