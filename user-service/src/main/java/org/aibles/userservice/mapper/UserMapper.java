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

@Component
@NoArgsConstructor
@AllArgsConstructor
public class UserMapper {

    private ModelMapper modelMapper = new ModelMapper();

//    @Autowired
//    private UserService userService;

    public User convertToEntity(UserRequestDTO userRequestDTO){
        return modelMapper.map(userRequestDTO, User.class);
    }

    public UserResponseDTO convertToUserResponseDTO(User user){
        return modelMapper.map(user, UserResponseDTO.class);
    }

}
