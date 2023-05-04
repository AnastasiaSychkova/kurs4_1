package com.example.kurs4_1.repositories;

import com.example.kurs4_1.model.CountStudents;
import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAge(int age);

    Collection<Student> findStudentByAgeBetween(int min, int max);
    Collection<Student> findStudentsByName(String name);

    @Query("select avg(s.age) from Student s")
    double getAverageAge();

    @Query(value = "SELECT * FROM Student order by id desc LIMIT 5", nativeQuery = true)
    List<Student> getLastFiveStudentById();
}
