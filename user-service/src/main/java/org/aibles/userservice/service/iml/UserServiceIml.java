package org.aibles.userservice.service.iml;

import org.aibles.userservice.model.User;
import org.aibles.userservice.repository.UserRepository;
import org.aibles.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
@Service
public class UserServiceIml implements UserService {

    private final UserRepository userRepository;
    private Scanner sc = new Scanner(System.in);
    @Autowired
    public UserServiceIml(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user) {
        // save user to database
        User userCreated = userRepository.save(user);
        return userCreated;
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<User> getListOfUser() {
        return (userRepository.findAll());
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }
}
