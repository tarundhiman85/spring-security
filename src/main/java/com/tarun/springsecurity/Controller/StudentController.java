package com.tarun.springsecurity.Controller;

import com.tarun.springsecurity.Models.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    List<Student> students = List.of(
            new Student(1, "Tarun", "Java"),
            new Student(2, "Rahul", "Python"),
            new Student(3, "Raj", "C++")
    );

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {
        students.add(student);
    }
}
