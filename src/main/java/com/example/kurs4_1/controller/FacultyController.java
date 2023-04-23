package com.example.kurs4_1.controller;

import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import com.example.kurs4_1.service.FacultyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.createFaculty(faculty);
    }

    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long id) {
        Faculty faculty = facultyService.getFacultyById(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> getAllFacultyByColour(@RequestParam(required = false) String colour) {
        if (colour != null && !colour.isBlank()) {
            return ResponseEntity.ok(facultyService.getAllFacultyByColour(colour));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("/findFacultyByNameOrColor")
    public ResponseEntity<Collection<Faculty>> findFacultyByNameOrColor(@RequestParam String name, @RequestParam String colour) {
        return ResponseEntity.ok(facultyService.getFacultyByNameOrColor(name, colour));
    }
   // @GetMapping("/findFacultyByStudents")
    //public ResponseEntity<Faculty> findFacultyByStudents(@RequestParam Collection<Student> students){
     //   return ResponseEntity.ok(facultyService.findFacultyByStudents(students));
   // }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty faculty1 = facultyService.updateFaculty(faculty);
        if (faculty1 == null) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(faculty1);
    }

    @DeleteMapping("{id}")
    public void deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
    }
}
