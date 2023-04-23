package com.example.kurs4_1.repositories;

import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByColor(String colour);

    Collection<Faculty> findFacultyByNameIgnoreCaseOrColorIgnoreCase(String name, String colour);

   // Faculty findFacultyByStudents(Collection<Student> students);
}
