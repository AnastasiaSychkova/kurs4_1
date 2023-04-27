package com.example.kurs4_1;

import com.example.kurs4_1.controller.StudentController;
import com.example.kurs4_1.model.Faculty;
import com.example.kurs4_1.model.Student;
import com.example.kurs4_1.service.StudentService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private StudentService studentService;
    private final Long ID = 1L;
    private final String NAME = "www";
    private final int AGE = 3;

    @Test
    public void getStudentById_success() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("name", NAME);
        studentObject.put("age", AGE);

        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);


        when(studentService.createStudent(student)).thenReturn(student);
        when(studentService.getStudentById(ID)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

    @Test
    public void getAllStudentByAge() throws Exception {

        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);
        Collection<Student> students = new ArrayList<>();
        students.add(student);


        when(studentService.getAllByAge(AGE)).thenReturn(students);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student?age=3")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findStudentByAgeBetween() throws Exception {
        int min = 1;
        int max = 4;
        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);
        Collection<Student> students = new ArrayList<>();
        students.add(student);


        when(studentService.findStudentByAgeBetween(min, max)).thenReturn(students);


        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/findStudentByAgeBetween?min=1&max=4")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void findFacultyByStudent() throws Exception {

        when(studentService.findFacultyByStudent(ID)).thenReturn(any(Faculty.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/findFacultyByStudent?id=1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateStudent() throws Exception {
        JSONObject studentObject = new JSONObject();
        studentObject.put("id", ID);
        studentObject.put("name", NAME);
        studentObject.put("age", AGE);


        Student student = new Student();
        student.setId(ID);
        student.setName(NAME);
        student.setAge(AGE);

        when(studentService.updateStudent(student)).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteStudent() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/student/" + ID)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
