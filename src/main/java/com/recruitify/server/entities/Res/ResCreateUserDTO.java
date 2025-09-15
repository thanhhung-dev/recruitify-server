package com.recruitify.server.entities.Res;

import lombok.Data;
import java.time.LocalDate;
@Data
public class ResCreateUserDTO {
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
