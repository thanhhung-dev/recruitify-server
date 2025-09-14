package com.recruitify.server.services;

import com.recruitify.server.entities.Role;
import com.recruitify.server.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    public List<Role> getAllRole()
    {
        return this.roleRepository.findAll();
    }
    public boolean existByName(String name) {
        return this.roleRepository.existsByName(name);
    }
    public Role findRoleById(long id)
    {
        Optional<Role> roleById = roleRepository.findById(id);
        return roleById.orElseThrow(() -> new RuntimeException("Role Not Found"));
    }
    public Role createRole(Role role)
    {
        return this.roleRepository.save(role);
    }
    public Role updateRole(Role role)
    {
        Role roleDB = this.findRoleById(role.getId());
        roleDB.setName(role.getName());
        roleDB = this.roleRepository.save(roleDB);
        return roleDB;
    }
    public void deleteRole(long id) {
        this.roleRepository.deleteById(id);
    }
}
