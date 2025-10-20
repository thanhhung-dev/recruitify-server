package com.recruitify.server.Services.Interface;

import com.recruitify.server.Entities.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
     List<Role> getAllRole();
     boolean existByName(String name);
     Role findRoleById(long id);
     Role createRole(Role request);
     Role updateRole(Role request, Long id);
     void deleteRole(long id);
}
