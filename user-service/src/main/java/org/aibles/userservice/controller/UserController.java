package org.aibles.userservice.controller;

import org.aibles.userservice.model.User;
import org.aibles.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") int id){
        User user = userService.getUser(id);
//        return ResponseEntity.status(HttpStatus.OK).body(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
//        return userService.createUser(user);
        User createdUser = userService.createUser(user);
        return new ResponseEntity<User>(createdUser,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> listAllContact(){
        List<User> listUsers= userService.getUsers();
        return new ResponseEntity<List<User>>(listUsers, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<String>("Delete Successfully",HttpStatus.OK);
    }

}
