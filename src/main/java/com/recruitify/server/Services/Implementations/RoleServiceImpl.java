package com.recruitify.server.Services.Implementations;

import com.recruitify.server.Entities.Company;
import com.recruitify.server.Entities.Role;
import com.recruitify.server.Repositories.RoleRepository;
import com.recruitify.server.Services.Interface.IRoleService;
import com.recruitify.server.Util.Error.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements IRoleService {
    private final RoleRepository roleRepository;
    private static final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRole() {
        logger.debug("Retrieving all role");
        List<Role> roleList = roleRepository.findAll();
        return roleList;
    }

    @Override
    public boolean existByName(String name) {
        logger.debug("Checking if role exists with name: {}", name);
        return roleRepository.existsByName(name);
    }

    @Override
    public Role findRoleById(long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role", id));
    }

    @Override
    public Role createRole(Role request) {
        logger.info("Creating role with title: {}", request.getName());
        // Validate Company exists
        if (existByName(request.getName())) {
            throw new IllegalArgumentException("Role with name '" + request.getName() + "' already exists");
        }
        Role saveRole = roleRepository.save(request);
        logger.info("Role created successfully with ID: {}", request.getId());
        return saveRole;
    }

    @Override
    public Role updateRole(Role request, Long id) {
        logger.info("Updating Role with ID: {}", id);
        Role existingRole = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Role", id));
        if (!existingRole.getName().equals(request.getName()) && existByName(request.getName())) {
            throw new IllegalArgumentException("Role with name '" + request.getName() + "' already exists");
        }
        existingRole.setName(request.getName());
        Role role = roleRepository.save(existingRole);
        logger.info("Role update successfully with ID: {}", request.getId());
        return role;
    }

    @Override
    public void deleteRole(long id) {
        logger.info("Deleting role with ID: {}", id);
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("role", id));
        roleRepository.delete(role);
        logger.info("role deleted successfully with ID: {}", id);
    }
}
