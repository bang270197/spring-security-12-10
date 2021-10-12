package com.example.springsecurity.payload;


import com.example.springsecurity.entity.ERole;
import lombok.Data;

@Data
public class RoleToUserRequest {
    private String username;
    private ERole rolename;
}
