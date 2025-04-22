package com.employeemanagement.dto;

import com.employeemanagement.entity.Status;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LeaveRequestDTO {
    @NotNull(message = "id cannot be null")
    private long id;

    private EmployeeDTO employee;
    @NotNull(message = "Date cannot be null")
    private LocalDate startDate;

    @NotNull(message = "Date cannot be null")
    private LocalDate endDate;

    @NotNull(message = "Reason cannot be null")
    private String reason;

    @NotNull(message = "Status cannot be null")
    private Status status;

    @NotNull(message = "id cannot be null")
    public long getId() {
        return id;
    }

    public void setId (long id) {
        this.id = id;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate( LocalDate startDate) {
        this.startDate = startDate;
    }

    public  LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate( LocalDate endDate) {
        this.endDate = endDate;
    }

    public  String getReason() {
        return reason;
    }

    public void setReason( String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus (Status status) {
        this.status = status;
    }
}
