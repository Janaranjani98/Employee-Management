package com.employeeManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import jdk.jfr.Enabled;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Size(min=3,max=50)
    private String name;
    @NotNull
    @Email
    @Size(max = 50)
    @Column(unique = true)
    private String email;
    @NotNull
    @Size(max = 50)
    private String department;
    @NotNull
    @Size(max = 50)
    private String jobTitle;
    @Digits(integer = 10,fraction = 2)
    @Positive
    private BigDecimal salary;
    @OneToOne
    private UserInfo user;
}
