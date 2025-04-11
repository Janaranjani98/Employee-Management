package com.employeeManagement.repository;

import com.employeeManagement.dto.PayRollDTO;
import com.employeeManagement.entity.PayRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayRollRepository extends JpaRepository<PayRoll,Long> {

    Optional<PayRoll> findByEmployeeId(Long id);
}
