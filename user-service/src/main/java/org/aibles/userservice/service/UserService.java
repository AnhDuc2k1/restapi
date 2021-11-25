package org.aibles.userservice.service;

import org.aibles.userservice.exception.UserAlreadyExistsException;
import org.aibles.userservice.exception.UserNotFoundException;
import org.aibles.userservice.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user) throws UserAlreadyExistsException;
    User getUser(int id) throws UserNotFoundException;
    List<User> getUsers();
    void deleteUser(int id) throws UserNotFoundException;
    User updateUser(int id, User user) throws UserNotFoundException;
}
