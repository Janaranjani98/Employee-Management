package com.employeeManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    @Email
    @Size(max = 50)
    @Column(unique = true)
    private String emailId;
    @NotNull
    @Size(min=8,max = 100)
    private String password;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Role role;


}
