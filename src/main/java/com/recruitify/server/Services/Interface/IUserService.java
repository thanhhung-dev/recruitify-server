package com.recruitify.server.Services.Interface;

import com.recruitify.server.Entities.User;

import java.util.List;

public interface IUserService {
    List<User> getAllUser();
    User handleCreateUser(User request);
    void handleDeleteUser(long id);
    User handleUpdateUser(User request);
}
