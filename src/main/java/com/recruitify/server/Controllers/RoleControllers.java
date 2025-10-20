package com.recruitify.server.Controllers;

import com.recruitify.server.Services.Implementations.RoleServiceImpl;
import com.recruitify.server.Util.Annotation.ApiMessage;
import com.recruitify.server.Entities.Role;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RoleControllers {
    private final RoleServiceImpl roleService;
    @PostMapping("/roles")
    @ApiMessage("Create a role")
    public ResponseEntity<Role> createRole(@Valid @RequestBody Role role) {
        Role createdRole = roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRole);
    }
    @PutMapping("/roles")
    @ApiMessage("Update a role")
    public ResponseEntity<Role> updateRole(
            @PathVariable Long id,
            @Valid @RequestBody Role role) {
        Role updatedRole = roleService.updateRole(role, id);
        return ResponseEntity.ok(updatedRole);
    }
    @DeleteMapping("/roles/{id}")
    @ApiMessage("Delete a role")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Role role = roleService.findRoleById(id);
        return ResponseEntity.ok(role);
    }
    @GetMapping("/roles")
    @ApiMessage("Fetch roles")
    public ResponseEntity<List<Role>> getAllRoles()
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.roleService.getAllRole());
    }
}
