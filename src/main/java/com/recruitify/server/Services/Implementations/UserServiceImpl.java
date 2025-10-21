package com.recruitify.server.Services.Implementations;

import com.recruitify.server.Dtos.Request.User.CreateUserRequest;
import com.recruitify.server.Dtos.Response.User.UserResponse;
import com.recruitify.server.Entities.Company;
import com.recruitify.server.Entities.Role;
import com.recruitify.server.Entities.User;
import com.recruitify.server.Mapper.UserMapper;
import com.recruitify.server.Repositories.UserRepository;
import com.recruitify.server.Services.Interface.IUserService;
import com.recruitify.server.Util.Error.EntityNotFoundException;
import com.recruitify.server.Util.Error.IdInvalidException;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserServiceImpl implements IUserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final RoleServiceImpl roleService;
    private final CompanyServiceImpl companyService;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, RoleServiceImpl roleService, CompanyServiceImpl companyService, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.companyService = companyService;
        this.userMapper = userMapper;
    }

    @Override
    public List<User> getAllUser() {
        logger.debug("Retrieving all user");
        return this.userRepository.findAll();
    }

    @Override
    public UserResponse handleCreateUser(CreateUserRequest request) {
        logger.info("Creating user with email: {}", request.getEmail());

        // Validate email uniqueness
        if (userRepository.existsByEmail(request.getEmail())) {
            logger.error("Email already in use: {}", request.getEmail());
            throw new ServiceException("Email is already in use: " + request.getEmail());
        }
        CreateUserRequest user = userMapper.toResponseUser(request);
    }

    public User fetchUserById(long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User ", id));
    }

    @Override
    public void handleDeleteUser(long id) {
        logger.info("Deleting user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User", id));
        userRepository.delete(user);
        logger.info("user deleted successfully with ID: {}", id);
    }

    @Override
    public User handleUpdateUser(User request){
        logger.info("Updating user with ID: {}", request.getId());

        // Validate input
        User currentUser = this.userRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("User " , request.getId()));

        // Update basic information
        Optional.ofNullable(request.getAddress()).ifPresent(currentUser::setAddress);
        Optional.ofNullable(request.getGender()).ifPresent(currentUser::setGender);
        Optional.ofNullable(request.getDob()).ifPresent(currentUser::setDob);
        Optional.ofNullable(request.getFirstName()).ifPresent(currentUser::setFirstName);
        Optional.ofNullable(request.getLastName()).ifPresent(currentUser::setLastName);
        Optional.ofNullable(request.getPhoneNumber()).ifPresent(currentUser::setPhoneNumber);
        Optional.ofNullable(request.getImage()).ifPresent(currentUser::setImage);

        // Update company
        Optional.ofNullable(request.getCompany())
                .map(Company::getId)
                .map(this.companyService::getByCompanyId)
                .ifPresent(currentUser::setCompany);

        // Update role
        Optional.ofNullable(request.getRole())
                .map(Role::getId)
                .map(this.roleService::findRoleById)
                .ifPresent(currentUser::setRole);

        User updatedUser = this.userRepository.save(currentUser);
        logger.info("Successfully updated user with ID: {}", updatedUser.getId());

        return updatedUser;
    }
}
