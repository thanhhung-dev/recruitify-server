package com.recruitify.server.Dtos.Response.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int gender;
    private String image;
    private String address;
    private LocalDate dob;
    private String displayName;
    private Instant updatedAt;
    private Instant createdAt;

    private CompanyUser company;

    private RoleUser role;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompanyUser {
        private long id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoleUser {
        private long id;
        private String name;
    }
}
