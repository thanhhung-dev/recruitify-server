package com.recruitify.server.Controllers;

import com.recruitify.server.Dtos.Response.CreateUserResponse;
import com.recruitify.server.Dtos.Response.User.UpdateUserResponse;
import com.recruitify.server.Dtos.Response.User.UserResponse;
import com.recruitify.server.Util.Annotation.ApiMessage;
import com.recruitify.server.Util.Error.IdInvalidException;
import com.recruitify.server.Entities.User;
import com.recruitify.server.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
@AllArgsConstructor
public class UserControllers {

    private final UserService userService;

    @GetMapping("/users")
    @ApiMessage("Fetch all users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(this.userService.getAllUser());
    }

    @PostMapping("/users")
    @ApiMessage("Create a new user")
    public ResponseEntity<CreateUserResponse> createNewUser(@RequestBody User user) throws IdInvalidException {
        // hash password later
        user.setPassword(user.getPassword());
        User createdUser = this.userService.handleCreateUser(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.userService.convertToResCreateUserDTO(createdUser));
    }

    @DeleteMapping("/users/{id}")
    @ApiMessage("Delete a user")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id)
            throws IdInvalidException {
        User currentUser = this.userService.fetchUserById(id);
        this.userService.handleDeleteUser(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/users/{id}")
    @ApiMessage("Fetch user by ID")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("id") long id)
            throws IdInvalidException {
        User fetchUser = this.userService.fetchUserById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.userService.convertToResUserDTO(fetchUser));
    }

    @PutMapping("/users")
    @ApiMessage("Update a user")
    public ResponseEntity<UpdateUserResponse> updateUser(@RequestBody User user)
            throws IdInvalidException {
        User updatedUser = this.userService.handleUpdateUser(user);
        if (updatedUser == null) {
            throw new IdInvalidException("User with id = " + user.getId() + " does not exist");
        }
        return ResponseEntity.ok(this.userService.convertToResUpdateUserDTO(updatedUser));
    }
}