package com.example.springsecurity.service.impl;

import com.example.springsecurity.entity.ERole;
import com.example.springsecurity.entity.Role;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.repo.RoleRepo;
import com.example.springsecurity.repo.UserRepo;
import com.example.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Transactional
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepo;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(userName);
        if(user == null){
            log.info("User not found in the database");
            throw  new UsernameNotFoundException("User not found in the database");
        }else {
            log.info("User found in the database: {}",userName);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(item ->{
            authorities.add(new SimpleGrantedAuthority(item.getName().name()));
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user to the database");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }


    @Override
    public Role saveRole(Role role) {
        log.info("Saving new user to the database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String userName, ERole roleName) {
            log.info("Adding role {} to user {}", roleName, userName);
            User user = userRepo.findByUsername(userName);
            Role role = roleRepo.findByName(roleName);
            user.getRoles().add(role);
//            userRepo.save(user);
    }

    @Override
    public User getUser(String userName) {
        log.info("Fetching user {}",userName);
        return userRepo.findByUsername(userName);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users {}");
        return userRepo.findAll();
    }


}
