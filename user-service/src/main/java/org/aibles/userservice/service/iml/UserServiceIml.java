package org.aibles.userservice.service.iml;

import javassist.NotFoundException;
import org.aibles.userservice.exception.UserAlreadyExistsException;
import org.aibles.userservice.exception.UserNotFoundException;
import org.aibles.userservice.model.User;
import org.aibles.userservice.repository.UserRepository;
import org.aibles.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.*;
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
    public User createUser(User user) throws UserAlreadyExistsException{
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser !=null ) {
            throw new UserAlreadyExistsException();
        }
        User userCreated = userRepository.save(user);
        return userCreated;
    }

    @Override
    public User getUser(int id) throws UserNotFoundException{
        User existingUser  = userRepository.findById(id).orElse(null);
        if (existingUser == null ) {
                throw new UserNotFoundException();
        }
        else {
            return existingUser;
        }
    }

    @Override
    public List<User> getUsers() {
        return (userRepository.findAll());
    }

    @Override
    public void deleteUser(int id)  throws UserNotFoundException{
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new UserNotFoundException();
        } else {
            userRepository.delete(existingUser);
        }
    }

    @Override
    public User updateUser(int id, User newUserDetails)  throws UserNotFoundException{
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new UserNotFoundException();
        } else {
            existingUser.setName(newUserDetails.getName());
            existingUser.setAge(newUserDetails.getAge());
            User updatedUser = userRepository.save(existingUser);
            return updatedUser;
        }
    }
}
