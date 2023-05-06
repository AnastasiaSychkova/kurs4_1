package com.example.kurs4_1.controller;

import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import com.example.kurs4_1.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {

        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<Collection<Student>> getAllStudentByAge(@RequestParam(required = false) int age) {
        if (age <= 0) {
            return ResponseEntity.ok(Collections.emptyList());
        }
        return ResponseEntity.ok(studentService.getAllByAge(age));
    }

    @GetMapping("/findStudentByAgeBetween")
    public ResponseEntity<Collection<Student>> findStudentByAgeBetween(@RequestParam int min, @RequestParam int max) {
        return ResponseEntity.ok(studentService.findStudentByAgeBetween(min, max));
    }

    @GetMapping("/findFacultyByStudent")
    public Faculty findFacultyByStudent(@RequestParam Long id) {
        return studentService.findFacultyByStudent(id);
    }

    @GetMapping("/findStudentsByName")
    public ResponseEntity<Collection<Student>> findStudentsByName(@RequestParam String name) {
        Collection<Student> students = studentService.findStudentsByName(name);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/getAll")
    public List<String> getAll(){
        return studentService.getAll();
    }

    @GetMapping("/getAllAvgAge")
    public Double getAllAvgAge(){
        return studentService.getAllAvgAge();
    }

    @GetMapping("/printAllStudentsName")
    public void printAllStudentsName(){
        studentService.printAllStudentsName();
    }

    @GetMapping("/printAllStudentsNameWithSynchronized")
    public void printAllStudentsNameWithSynchronized(){
        studentService.printAllStudentsNameWithSynchronized();
    }

    @PutMapping

    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        Student student1 = studentService.updateStudent(student);
        if (student1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student1);
    }

    @DeleteMapping("{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/count")
    public long getCount() {
        return studentService.getAllCount();
    }

    @GetMapping("/average-age")
    public double getAverageAge() {
        return studentService.getAverageAge();
    }

    @GetMapping("/last-five-students")
    public List<Student> getLastFiveStudent() {
        return studentService.getLastFiveStudent();
    }
}
