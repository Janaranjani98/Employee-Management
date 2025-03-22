package com.employeeManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class PayRoll {
    @Id
    private long id;
    @OneToOne
    private Employee employee;
    @Positive
    @Digits(integer = 10,fraction = 2)
    private BigDecimal salaryAmount;
    @NotNull
    private LocalDate payDate;
}
