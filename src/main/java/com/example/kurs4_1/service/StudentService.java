package com.example.kurs4_1.service;

import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import com.example.kurs4_1.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        logger.info("Was invoked method for getStudentById");
        return studentRepository.findById(id).get();
    }

    public Collection<Student> getAllByAge(int age) {
        logger.info("Was invoked method for getAllByAge");
        return studentRepository.findAllByAge(age);
    }

    public Collection<Student> findStudentByAgeBetween(int min, int max) {
        logger.info("Was invoked method for findStudentByAgeBetween");
        return studentRepository.findStudentByAgeBetween(min, max);
    }

    public Faculty findFacultyByStudent(Long studentId) {
        logger.info("Was invoked method for findFacultyByStudent");
        return getStudentById(studentId).getFaculty();
    }

    public Student updateStudent(Student student) {
        logger.info("Was invoked method for updateStudent");
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        logger.info("Was invoked method for deleteStudent");
        studentRepository.deleteById(id);
    }

    public long getAllCount() {
        logger.info("Was invoked method for getAllCount");
        return studentRepository.count();
    }
    public Collection<Student> findStudentsByName(String name){
        logger.info("Was invoked method for findStudentsByName");
        return studentRepository.findStudentsByName(name);
    }

    public double getAverageAge() {
        logger.info("Was invoked method for getAverageAge");
        return studentRepository.getAverageAge();
    }

    public List<Student> getLastFiveStudent() {
        logger.info("Was invoked method for getLastFiveStudent");
        return studentRepository.getLastFiveStudentById();
    }

}
