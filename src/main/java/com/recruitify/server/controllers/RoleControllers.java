package com.recruitify.server.controllers;

import com.recruitify.server.Util.Annotation.ApiMessage;
import com.recruitify.server.Util.Error.IdInvalidException;
import com.recruitify.server.entities.Role;
import com.recruitify.server.repositories.RoleRepository;
import com.recruitify.server.services.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class RoleControllers {
    private final RoleService roleService;
    @PostMapping("/roles")
    @ApiMessage("Create a role")
    public ResponseEntity<Role> create(@RequestBody Role role) throws IdInvalidException {
        // check name
        if (this.roleService.existByName(role.getName())) {
            throw new IdInvalidException("Role with name = " + role.getName() + " Existent");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.roleService.createRole(role));
    }
    @PutMapping("/roles")
    @ApiMessage("Update a role")
    public ResponseEntity<Role> update(@RequestBody Role role) throws IdInvalidException {
        // check id
        if (this.roleService.findRoleById(role.getId()) == null) {
            throw new IdInvalidException("Role with id = " + role.getId() + " Non Existent");
        }
        return ResponseEntity.ok().body(this.roleService.updateRole(role));
    }
    @DeleteMapping("/roles/{id}")
    @ApiMessage("Delete a role")
    public ResponseEntity<Void> delete(@PathVariable("id") long id) throws IdInvalidException {
        // check id
        if (this.roleService.findRoleById(id) == null) {
            throw new IdInvalidException("Role với id = " + id + " không tồn tại");
        }
        this.roleService.deleteRole(id);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/roles")
    @ApiMessage("Fetch roles")
    public ResponseEntity<List<Role>> getAllRoles()
    {
        return ResponseEntity.status(HttpStatus.OK).body(this.roleService.getAllRole());
    }
}
