package com.recruitify.server.Dtos.Request.User;

import com.recruitify.server.Dtos.Response.User.UserResponse;
import com.recruitify.server.Entities.Company;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.Instant;
@Data
public class CreateUserRequest {
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
    private String firstName;
    private String lastName;
    private Instant createDate;
    private CompanyUser company;
    private RoleUser roleUser;
}
