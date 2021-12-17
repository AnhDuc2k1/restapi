package org.aibles.userservice.service;

import org.aibles.userservice.dto.UserRequestDTO;
import org.aibles.userservice.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO getUser(int id) ;
    List <UserResponseDTO> getUsersWithPagination(int pageNumber, int pageSize);
    void deleteUser(int id);
    UserResponseDTO updateUser(int id, UserRequestDTO userRequestDTO);
}
