package com.recruitify.server.Dtos.Request.User;

import lombok.Data;
import java.time.LocalDate;
@Data
public class CreateUserRequest {
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
    private CompanyUser company;
    @Data
    public static class CompanyUser {
        private long id;
        private String name;
    }
}
