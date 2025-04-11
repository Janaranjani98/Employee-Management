package com.employeeManagement.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayRollDTO {
    @NotNull(message = "Id cannot be null")
    private long id;

    @NotNull
    private EmployeeDTO employee;

    @Positive(message = "Salary cannot be negative")
    @Digits(integer = 10,fraction = 2)
    private BigDecimal basicSalary;

    @Positive(message = "Salary cannot be negative")
    @Digits(integer = 10,fraction = 2)
    private BigDecimal deductions;

    @Positive(message = "Salary cannot be negative")
    @Digits(integer = 10,fraction = 2)
    private BigDecimal netSalary;

    @NotNull(message = "Date cannot be null")
    private LocalDate payDate;

    @NotNull(message = "Id cannot be null")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmployeeDTO getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDTO employee) {
        this.employee = employee;
    }

    public  BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }

    public BigDecimal getDeductions() {
        return deductions;
    }

    public void setDeductions( BigDecimal deductions) {
        this.deductions = deductions;
    }

    public  BigDecimal getNetSalary() {
        return netSalary;
    }

    public void setNetSalary (BigDecimal netSalary) {
        this.netSalary = netSalary;
    }

    public LocalDate getPayDate() {
        return payDate;
    }

    public void setPayDate(LocalDate payDate) {
        this.payDate = payDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PayRollDTO that)) return false;
        return id == that.id && Objects.equals(employee, that.employee) && Objects.equals(basicSalary, that.basicSalary) && Objects.equals(deductions, that.deductions) && Objects.equals(netSalary, that.netSalary) && Objects.equals(payDate, that.payDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, basicSalary, deductions, netSalary, payDate);
    }
}
