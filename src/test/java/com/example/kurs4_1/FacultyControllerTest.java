package com.example.kurs4_1;

import com.example.kurs4_1.controller.FacultyController;
import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import com.example.kurs4_1.service.FacultyService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FacultyController.class)

public class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FacultyService facultyService;
    private final Long ID = 1L;
    private final String NAME = "www";
    private final String COLOUR = "white";

    @Test
    public void createFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", ID);
        facultyObject.put("name", NAME);
        facultyObject.put("age", COLOUR);

        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOUR);


        when(facultyService.createFaculty(faculty)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getFacultyById() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOUR);

        when(facultyService.getFacultyById(ID)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAllFacultyByColour() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOUR);
        Collection<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);


        when(facultyService.getAllFacultyByColour(COLOUR)).thenReturn(faculties);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty?colour=white")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findFacultyByNameOrColor() throws Exception {
        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOUR);
        Collection<Faculty> faculties = new ArrayList<>();
        faculties.add(faculty);

        when(facultyService.getFacultyByNameOrColor(NAME, COLOUR)).thenReturn(faculties);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/getFacultyByNameOrColor?name=www&colour=white")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findStudentsByFaculty() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setName("www");
        student.setAge(23);
        Collection<Student> students = new ArrayList<>();
        students.add(student);

        when(facultyService.findStudentsByFaculty(ID)).thenReturn(students);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/findStudentsByFaculty?facultyId=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateFaculty() throws Exception {
        JSONObject facultyObject = new JSONObject();
        facultyObject.put("id", ID);
        facultyObject.put("name", NAME);
        facultyObject.put("color", COLOUR);


        Faculty faculty = new Faculty();
        faculty.setId(ID);
        faculty.setName(NAME);
        faculty.setColor(COLOUR);

        when(facultyService.updateFaculty(faculty)).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFaculty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/faculty/" + ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
