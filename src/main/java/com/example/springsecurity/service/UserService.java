package com.example.springsecurity.service;

import com.example.springsecurity.entity.ERole;
import com.example.springsecurity.entity.Role;
import com.example.springsecurity.entity.User;
import com.example.springsecurity.exception.LogicException;

import java.util.List;

public interface UserService {
    User saveUser(User user) throws LogicException;
    Role saveRole(Role role);
    void addRoleToUser(String userName, ERole roleName);
    User getUser(String userName);
    List<User> getUsers();
}
