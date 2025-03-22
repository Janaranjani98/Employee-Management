package com.employeeManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class LeaveRequest {
    @Id
    private long id;
    @ManyToOne
    private Employee employee;
    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;
    @NotNull
    @Size(max = 200)
    private String reason;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

}
