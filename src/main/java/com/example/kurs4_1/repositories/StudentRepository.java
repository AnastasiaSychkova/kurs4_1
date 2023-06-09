package com.example.kurs4_1.repositories;

import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAge(int age);
    Collection<Student> findStudentByAgeBetween(int min, int max);
    Collection<Student> findStudentsByFaculty(Faculty faculty);
}
