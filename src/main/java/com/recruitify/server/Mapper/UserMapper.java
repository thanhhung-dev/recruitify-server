package com.recruitify.server.Mapper;

import com.recruitify.server.Dtos.Request.User.CompanyUser;
import com.recruitify.server.Dtos.Request.User.CreateUserRequest;
import com.recruitify.server.Dtos.Request.User.RoleUser;
import com.recruitify.server.Dtos.Request.User.UpdateUserRequest;
import com.recruitify.server.Dtos.Response.User.UserResponse;
import com.recruitify.server.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(CreateUserRequest request) {
        var user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        return request;
    }
    public UserResponse ToResUserDTO(User user) {
        UserResponse res = new UserResponse();
        UserResponse.CompanyUser com = new UserResponse.CompanyUser();
        UserResponse.RoleUser roleUser = new UserResponse.RoleUser();
        if (user.getCompany() != null) {
            com.setId(user.getCompany().getId());
            com.setName(user.getCompany().getName());
            res.setCompany(com);
        }

        if (user.getRole() != null) {
            roleUser.setId(user.getRole().getId());
            roleUser.setName(user.getRole().getName());
            res.setRole(roleUser);
        }

        res.setId(user.getId());
        res.setEmail(user.getEmail());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setDob(user.getDob());
        res.setUpdatedAt(user.getUpdatedAt());
        res.setCreatedAt(user.getCreatedAt());
        res.setGender(user.getGender());
        res.setAddress(user.getAddress());
        return res;
    }
    public UpdateUserRequest ToResUpdateUser(User user) {
        UpdateUserRequest dto = new UpdateUserRequest();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhoneNumber(user.getPhoneNumber());
        dto.setGender(user.getGender());
        dto.setImage(user.getImage());
        dto.setAddress(user.getAddress());
        dto.setDob(user.getDob() != null ? user.getDob().toString() : null);

        if (user.getCompany() != null) {
            dto.setCompanyId(user.getCompany().getId());
            dto.setCompanyName(user.getCompany().getName());
        }

        if (user.getRole() != null) {
            dto.setRoleId(user.getRole().getId());
            dto.setRoleName(user.getRole().getName());
        }

        dto.setUpdatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null);

        return dto;
    }
}
