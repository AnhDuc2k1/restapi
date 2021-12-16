package org.aibles.userservice.service;

import org.aibles.userservice.exception.UserAlreadyExistsException;
import org.aibles.userservice.exception.UserNotFoundException;
import org.aibles.userservice.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    User createUser(User user);
    User getUser(int id) ;
    List <User> getUsersWithPagination(int pageNumber, int pageSize);
    void deleteUser(int id);
    User updateUser(int id, User user);
}
