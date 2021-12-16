package org.aibles.userservice.controller;

import lombok.AllArgsConstructor;
import org.aibles.userservice.dto.UserResponseDTO;
import org.aibles.userservice.mapper.UserMapper;
import org.aibles.userservice.model.User;
import org.aibles.userservice.dto.UserRequestDTO;
import org.aibles.userservice.repository.UserRepository;
import org.aibles.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserMapper userMapper;
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") int id){
        UserResponseDTO userResponseDTO = userMapper.getUserById(id);
        return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.OK);
    }

//    @GetMapping
//    public ResponseEntity<List<UserResponseDTO>> getUsers(){
//        List<UserResponseDTO> listOfUsersResponseDTO = userMapper.getUsers();
//        return new ResponseEntity<List<UserResponseDTO>>(listOfUsersResponseDTO, HttpStatus.OK);
//    }

    @GetMapping("/pagination/{pageNumber}/{pageSize}")
    public ResponseEntity<List<UserResponseDTO>> getUsersWithPagination(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize){
        List<UserResponseDTO> listOfUsersResponseDTO = userMapper.getUsersWithPagination(pageNumber,pageSize);
        return new ResponseEntity<List<UserResponseDTO>>(listOfUsersResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userMapper.addUser(userRequestDTO);
        return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable ("id") int id,@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userMapper.updateUser(id,userRequestDTO);
        return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        userMapper.deleteUser(id);
        return new ResponseEntity<String>("Delete Successfully",HttpStatus.OK);
    }
}
