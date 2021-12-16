package org.aibles.userservice.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.aibles.userservice.dto.UserRequestDTO;
import org.aibles.userservice.dto.UserResponseDTO;
import org.aibles.userservice.model.User;
import org.aibles.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserMapper {

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserService userService;

    public User convertToEntity(UserRequestDTO userRequestDTO){
        return modelMapper.map(userRequestDTO, User.class);
    }

    public UserResponseDTO convertToUserResponseDTO(User user){
        return modelMapper.map(user, UserResponseDTO.class);
    }

    public UserResponseDTO getUserById(int id){
        return convertToUserResponseDTO(userService.getUser(id));
    }

//    public List<UserResponseDTO> getUsers(){
//        return userService.getUsers().stream().map(user -> convertToUserResponseDTO(user))
//                        .collect(Collectors.toList());
//    }

    public void deleteUser(int id){
        userService.deleteUser(id);
    }

    public UserResponseDTO addUser(UserRequestDTO userRequestDTO){
        return convertToUserResponseDTO(userService.createUser(convertToEntity(userRequestDTO)));
    }

    public UserResponseDTO updateUser(int id, UserRequestDTO userRequestDTO) {
        return convertToUserResponseDTO(userService.updateUser(id, convertToEntity(userRequestDTO)));
    }

    public List<UserResponseDTO> getUsersWithPagination(int pageNumber, int pageSize){
        return userService.getUsersWithPagination(pageNumber,pageSize).stream().map(user -> convertToUserResponseDTO(user))
                       .collect(Collectors.toList());
    }
}
