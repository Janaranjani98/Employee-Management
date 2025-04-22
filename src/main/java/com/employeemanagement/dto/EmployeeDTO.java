package com.employeemanagement.dto;

import com.employeemanagement.entity.UserInfo;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    @NotNull(message = "Id cannot be null")
    private long id;

    @NotNull(message = "Name cannot be empty")
    @Size(min=3,max=50)
    private String name;

    @NotNull(message = "Email cannot be empty")
    @Email(message = "Invalid format")
    @Size(max = 50)
    private String email;

    @NotNull(message = "Department cannot be empty")
    @Size(max = 50)
    private String department;

    @NotNull(message = "Jobtitle cannot be empty")
    @Size(max = 50)
    private String jobTitle;

    @Digits(integer = 10,fraction = 2)
    @Positive(message = "salary cannot be negative")
    private BigDecimal salary;

    @NotNull
    private UserInfo user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public UserInfo getUser() {
        return user;
    }

    public void setUser(UserInfo user) {
        this.user = user;
    }
}
