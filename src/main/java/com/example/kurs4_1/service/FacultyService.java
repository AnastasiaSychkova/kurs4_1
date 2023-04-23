package com.example.kurs4_1.service;

import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import com.example.kurs4_1.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id).get();
    }

    public Collection<Faculty> getAllFacultyByColour(String colour) {
        return facultyRepository.findAllByColor(colour);
    }

    public Collection<Faculty> getFacultyByNameOrColor(String name, String colour) {
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, colour);
    }

    //public Faculty findFacultyByStudents(Collection<Student> students) {
       // return facultyRepository.findFacultyByStudents(students);
    //}

    public Faculty updateFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
}
