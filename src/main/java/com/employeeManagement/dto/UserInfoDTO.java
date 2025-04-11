package com.employeeManagement.dto;

import com.employeeManagement.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserInfoDTO {

    @NotNull
    private long id;

    @NotNull(message = "Email id cannot be null")
    @Email(message = "Invalid pattern")
    @Size(max = 50)
    private String emailId;

    @NotNull(message = "Password cannot be null")
    @Size(min=8,max = 100)
    private String password;

    @NotNull
    private Role role;

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId (String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public  Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoDTO that = (UserInfoDTO) o;
        return id == that.id && Objects.equals(emailId, that.emailId) && Objects.equals(password, that.password) && role == that.role && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, emailId, password, role, name);
    }
}
