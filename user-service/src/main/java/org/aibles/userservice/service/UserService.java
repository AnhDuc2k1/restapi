package org.aibles.userservice.service;

import org.aibles.userservice.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User getUser(int id);
    List<User> getListOfUser();
    void deleteUser(User user);
    User updateUser(User user);
}
