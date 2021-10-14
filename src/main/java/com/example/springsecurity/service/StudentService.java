package com.example.springsecurity.service;

import com.example.springsecurity.entity.Student;

import java.util.List;

public interface StudentService {
    void save(Student student);
    List<Student> findAll();
}
