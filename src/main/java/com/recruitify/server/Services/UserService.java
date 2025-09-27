package com.recruitify.server.Services;

import com.recruitify.server.Dtos.Response.CreateUserResponse;
import com.recruitify.server.Dtos.Response.User.UpdateUserResponse;
import com.recruitify.server.Dtos.Response.User.UserResponse;
import com.recruitify.server.Entities.Company;
import com.recruitify.server.Entities.Role;
import com.recruitify.server.Entities.User;
import com.recruitify.server.Repositories.UserRepository;
import com.recruitify.server.Util.Error.IdInvalidException;
import lombok.AllArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CompanyService companyService;
    public List<User> getAllUser()
    {
        return this.userRepository.findAll();
    }
    public User handleCreateUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new ServiceException("Email is already in use: " + user.getEmail());
        }
        if (user.getCompany() != null) {
            Optional<Company> companyOptional = this.companyService.fetchCompanyById(user.getCompany().
                    getId());
            user.setCompany(companyOptional.orElse(null));
        }
        // check role
        if (user.getRole() != null) {
            Role r = this.roleService.findRoleById(user.getRole().getId());
            user.setRole(r);
        }
        return this.userRepository.save(user);
    }
    public void handleDeleteUser(long id) {
        this.userRepository.deleteById(id);
    }
    public User fetchUserById(long id) throws IdInvalidException {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new IdInvalidException("User not found with id: " + id));
    }
    public User handleUpdateUser(User reqUser) throws IdInvalidException {
        return this.userRepository.findById(reqUser.getId())
                .map(currentUser -> {currentUser.setAddress(reqUser.getAddress());
                    currentUser.setGender(reqUser.getGender());
                    currentUser.setDob(reqUser.getDob());
                    currentUser.setFirstName(reqUser.getFirstName());
                    currentUser.setLastName(reqUser.getLastName());
                    currentUser.setPhoneNumber(reqUser.getPhoneNumber());
                    currentUser.setImage(reqUser.getImage());
                    if (reqUser.getCompany() != null && reqUser.getCompany().getId() != null) {
                        Optional<Company> company = this.companyService.fetchCompanyById(reqUser.getCompany().getId());
                        currentUser.setCompany(company.orElse(null));
                    }
                    if (reqUser.getRole() != null) {
                        Role role = this.roleService.findRoleById(reqUser.getRole().getId());
                        currentUser.setRole(role);
                    }
                    return this.userRepository.save(currentUser);
                })
                .orElseThrow(() -> new IdInvalidException("User not found with id: " + reqUser.getId()));
    }
    public CreateUserResponse convertToResCreateUserDTO(User user) {
        CreateUserResponse res = new CreateUserResponse();
        CreateUserResponse.CompanyUser com = new CreateUserResponse.CompanyUser();

        res.setId(user.getId());
        res.setEmail(user.getEmail());
        res.setFirstName(user.getFirstName());
        res.setLastName(user.getLastName());
        res.setDob(user.getDob());
        res.setGender(user.getGender());
        res.setAddress(user.getAddress());
        if (user.getCompany() != null) {
            com.setId(user.getCompany().getId());
            com.setName(user.getCompany().getName());
            res.setCompany(com);
        }
        return res;
    }
    public UserResponse convertToResUserDTO(User user) {
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
    public UpdateUserResponse convertToResUpdateUserDTO(User user) {
        UpdateUserResponse dto = new UpdateUserResponse();
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
