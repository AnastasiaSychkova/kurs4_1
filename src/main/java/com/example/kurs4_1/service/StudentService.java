package com.example.kurs4_1.service;

import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import com.example.kurs4_1.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


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

    public Collection<Student> findStudentsByName(String name) {
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

    public List<String> getAll() {
        logger.info("Was invoked method for getAll");
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(student -> student.getName())
                .map(s -> s.toUpperCase())
                .filter(name -> name.startsWith("A"))
                .sorted()
                .collect(Collectors.toList());
    }

    public Double getAllAvgAge() {
        logger.info("Was invoked method for getAllAvgAge");
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .mapToDouble(student -> student.getAge())
                .average()
                .orElse(0);
    }

    public void printAllStudentsName() {
        logger.info("Was invoked method for printAllStudentsName");
        List<Student> students = studentRepository.findAll();

        System.out.println(students.get(0).getName());
        System.out.println(students.get(1).getName());

        new Thread(() -> {
            System.out.println(students.get(2).getName());
            System.out.println(students.get(3).getName());
        }).start();

        new Thread(() -> {
            System.out.println(students.get(4).getName());
            System.out.println(students.get(5).getName());
        }).start();
    }

    public void printAllStudentsNameWithSynchronized() {

        logger.info("Was invoked method for printAllStudentsNameWithSynchronized");
        List<Student> students = studentRepository.findAll();

        printStudentSync(students, 0);
        printStudentSync(students, 1);

        new Thread(() -> {
            printStudentSync(students, 2);
            printStudentSync(students, 3);
        }).start();

        new Thread(() -> {
            printStudentSync(students, 4);
            printStudentSync(students, 5);
        }).start();
    }

    private void printStudentSync(List<Student> students, int index) {
        synchronized (this) {
            System.out.println(students.get(index).getName());
        }
    }

}
