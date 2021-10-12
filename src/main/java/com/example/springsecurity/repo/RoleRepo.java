package com.example.springsecurity.repo;

import com.example.springsecurity.entity.ERole;
import com.example.springsecurity.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {
    Role findByName(ERole name);
}
