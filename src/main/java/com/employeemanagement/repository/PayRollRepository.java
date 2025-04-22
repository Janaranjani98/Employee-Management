package com.employeemanagement.repository;

import com.employeemanagement.entity.PayRoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PayRollRepository extends JpaRepository<PayRoll,Long> {

    Optional<PayRoll> findByEmployeeId(Long id);
}
