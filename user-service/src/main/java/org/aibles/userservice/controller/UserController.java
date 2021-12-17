package org.aibles.userservice.controller;

import lombok.AllArgsConstructor;
import org.aibles.userservice.dto.UserResponseDTO;
import org.aibles.userservice.dto.UserRequestDTO;
import org.aibles.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable("id") int id){
        UserResponseDTO userResponseDTO = userService.getUser(id);
        return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/pagination/{pageNumber}/{pageSize}")
    public ResponseEntity<List<UserResponseDTO>> getUsersWithPagination(@PathVariable("pageNumber") Integer pageNumber, @PathVariable("pageSize") Integer pageSize){
        List<UserResponseDTO> listOfUsersResponseDTO = userService.getUsersWithPagination(pageNumber,pageSize);
        return new ResponseEntity<List<UserResponseDTO>>(listOfUsersResponseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = userService.createUser(userRequestDTO);
        return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable ("id") int id,@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userResponseDTO = userService.updateUser(id,userRequestDTO);
        return new ResponseEntity<UserResponseDTO>(userResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return new ResponseEntity<String>("Delete Successfully",HttpStatus.OK);
    }
}
