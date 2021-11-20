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
        if (user !=null) {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
//    @GetMapping
//    public List <User> getListOfUser(){
//        return  userService.getListOfUser();
//    }
    @GetMapping
    public ResponseEntity<List<User>> listAllContact(){
        List<User> listOfUser= userService.getListOfUser();
        if(listOfUser.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<User>>(listOfUser, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
        User user = userService.getUser(id);
        if(user == null) {
//            return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.deleteUser(user);
            return ResponseEntity.status(HttpStatus.OK).build();
//        return ResponseEntity.ok().build();
    }
}
