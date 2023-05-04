package com.example.kurs4_1.service;

import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import com.example.kurs4_1.repositories.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for createFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long id) {
        logger.info("Was invoked method for getFacultyById");
        return facultyRepository.findById(id).get();
    }

    public Collection<Faculty> getAllFacultyByColour(String colour) {
        logger.info("Was invoked method for getAllFacultyByColour");
        return facultyRepository.findAllByColor(colour);
    }

    public Collection<Student> findStudentsByFaculty(Long facultyId) {
        logger.info("Was invoked method for findStudentsByFaculty");
        return getFacultyById(facultyId).getStudents();
    }

    public Collection<Faculty> getFacultyByNameOrColor(String name, String colour) {
        logger.info("Was invoked method for getFacultyByNameOrColor");
        return facultyRepository.findFacultyByNameIgnoreCaseOrColorIgnoreCase(name, colour);
    }

    public Faculty updateFaculty(Faculty faculty) {
        logger.info("Was invoked method for updateFaculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        logger.info("Was invoked method for deleteFaculty");
        facultyRepository.deleteById(id);
    }
}
