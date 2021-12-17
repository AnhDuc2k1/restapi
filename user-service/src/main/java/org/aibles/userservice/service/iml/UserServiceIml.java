package org.aibles.userservice.service.iml;

import org.aibles.userservice.dto.UserRequestDTO;
import org.aibles.userservice.dto.UserResponseDTO;
import org.aibles.userservice.exception.UserAlreadyExistsException;
import org.aibles.userservice.exception.UserNotFoundException;
import org.aibles.userservice.mapper.UserMapper;
import org.aibles.userservice.model.User;
import org.aibles.userservice.repository.UserRepository;
import org.aibles.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceIml implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceIml(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws UserAlreadyExistsException{
        User existingUser = userRepository.findById(userMapper.convertToEntity(userRequestDTO).getId()).orElse(null);
        if (existingUser !=null ) {
            throw new UserAlreadyExistsException();
        }
        User userCreated = userRepository.save(userMapper.convertToEntity(userRequestDTO));
        return userMapper.convertToUserResponseDTO(userCreated);
    }

    @Override
    public UserResponseDTO getUser(int id) throws UserNotFoundException{
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null ) {
                throw new UserNotFoundException();
        }
        else {
            return userMapper.convertToUserResponseDTO(existingUser);
        }
    }

    @Override
    public List<UserResponseDTO> getUsersWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize);
        return  userRepository.findAll(pageable).stream().map(user -> userMapper.convertToUserResponseDTO(user))
                       .collect(Collectors.toList());
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
    public UserResponseDTO updateUser(int id, UserRequestDTO newUserDetails)  throws UserNotFoundException{
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser == null) {
            throw new UserNotFoundException();
        } else {
            existingUser.setName(newUserDetails.getName());
            existingUser.setAge(newUserDetails.getAge());
            existingUser.setPassword(newUserDetails.getPassword());
            existingUser.setAccount(newUserDetails.getAccount());
            existingUser.setEmail(newUserDetails.getEmail());
            User updatedUser = userRepository.save(existingUser);
            return userMapper.convertToUserResponseDTO(updatedUser);
        }
    }
}
