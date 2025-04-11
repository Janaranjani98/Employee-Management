package com.employeeManagement.repository;

import com.employeeManagement.dto.LeaveRequestDTO;
import com.employeeManagement.entity.LeaveRequest;
import com.employeeManagement.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest,Long> {
    public List<LeaveRequest> findByEmployeeId(Long id);
    public List<LeaveRequest> findByStatus(Status status);
}
