package com.example.springsecurity.repo;

import com.example.springsecurity.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
