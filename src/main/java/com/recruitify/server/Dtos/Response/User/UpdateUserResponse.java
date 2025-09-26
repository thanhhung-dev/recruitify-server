package com.recruitify.server.Dtos.Response.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserResponse {
    private Long id;
    private String email;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int gender;
    private String image;
    private String address;
    private String dob;

    private Long companyId;
    private String companyName;

    private Long roleId;
    private String roleName;

    private String updatedAt;
}
