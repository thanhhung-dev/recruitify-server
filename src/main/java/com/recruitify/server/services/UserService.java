package com.recruitify.server.services;

import com.recruitify.server.entities.Company;
import com.recruitify.server.entities.Res.ResCreateUserDTO;
import com.recruitify.server.entities.Res.ResUpdateUserDTO;
import com.recruitify.server.entities.Res.ResUserDTO;
import com.recruitify.server.entities.Role;
import com.recruitify.server.entities.User;
import com.recruitify.server.repositories.UserRepository;
import lombok.AllArgsConstructor;
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
    public User handleCreateUser(User user)
    {
        if (user.getCompany() != null) {
            Optional<Company> companyOptional = Optional.ofNullable(this.companyService.findCompanyById(user.getCompany().getId()));
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
    public User fetchUserById(long id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.orElse(null);
    }

    public boolean isEmailExist(String email) {
        return this.userRepository.existsByEmail(email);
    }
    public User handleUpdateUser(User reqUser) {
        User currentUser = this.fetchUserById(reqUser.getId());
        if (currentUser != null) {
            currentUser.setAddress(reqUser.getAddress());
            currentUser.setGender(reqUser.getGender());
            currentUser.setDob(reqUser.getDob());
            currentUser.setFirstName(reqUser.getFirstName());
            currentUser.setLastName(reqUser.getLastName());
            currentUser.setPhoneNumber(reqUser.getPhoneNumber());
            currentUser.setImage(reqUser.getImage());
            if (reqUser.getCompany() != null) {
                Company company = this.companyService.findCompanyById(reqUser.getCompany().getId());
                currentUser.setCompany(company);
            }
            if (reqUser.getRole() != null) {
                Role role = this.roleService.findRoleById(reqUser.getRole().getId());
                currentUser.setRole(role);
            }
            currentUser = this.userRepository.save(currentUser);
        }
        return currentUser;
    }
    public ResCreateUserDTO convertToResCreateUserDTO(User user) {
        ResCreateUserDTO res = new ResCreateUserDTO();
        ResCreateUserDTO.CompanyUser com = new ResCreateUserDTO.CompanyUser();

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
    public ResUserDTO convertToResUserDTO(User user) {
        ResUserDTO res = new ResUserDTO();
        ResUserDTO.CompanyUser com = new ResUserDTO.CompanyUser();
        ResUserDTO.RoleUser roleUser = new ResUserDTO.RoleUser();
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
    public ResUpdateUserDTO convertToResUpdateUserDTO(User user) {
        ResUpdateUserDTO dto = new ResUpdateUserDTO();
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
