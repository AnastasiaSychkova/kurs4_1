package com.example.kurs4_1.repositories;

import com.example.kurs4_1.model.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Avatar findByStudentId(Long studentId);
}
