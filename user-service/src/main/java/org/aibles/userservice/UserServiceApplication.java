package org.aibles.userservice;

import org.aibles.userservice.mapper.UserMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserServiceApplication {

    @Bean
    public UserMapper usersMapper(){
        return new UserMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
