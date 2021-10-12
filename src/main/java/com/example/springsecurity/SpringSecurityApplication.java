package com.example.springsecurity;

import com.example.springsecurity.entity.ERole;
import com.example.springsecurity.entity.Role;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

    /*ROLE_USER,
    ROLE_ADMIN,
    ROLE_MANAGER,
    ROLE_SUPER_ADMIN*/

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
//
    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
//            userService.saveRole(new Role(null, ERole.ROLE_ADMIN));
//            userService.saveRole(new Role(null, ERole.ROLE_USER));
//            userService.saveRole(new Role(null, ERole.ROLE_MANAGER));
//            userService.saveRole(new Role(null, ERole.ROLE_SUPER_ADMIN));
//
//            userService.saveUser(new User(null,"bang1","username1","1234",new ArrayList<>()));
//            userService.saveUser(new User(null,"bang2","username2","1234",new ArrayList<>()));
//            userService.saveUser(new User(null,"bang3","username3","1234",new ArrayList<>()));
//            userService.saveUser(new User(null,"bang4","username4","1234",new ArrayList<>()));
//
//
//            userService.addRoleToUser("username1",ERole.ROLE_USER);
//            userService.addRoleToUser("username2",ERole.ROLE_ADMIN);
//            userService.addRoleToUser("username3",ERole.ROLE_SUPER_ADMIN);
//            userService.addRoleToUser("username4",ERole.ROLE_MANAGER);
        };
    }

}
