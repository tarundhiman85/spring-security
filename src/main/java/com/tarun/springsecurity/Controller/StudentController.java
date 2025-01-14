package com.tarun.springsecurity.Controller;

import com.tarun.springsecurity.Models.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import java.util.ArrayList;

@RestController
public class StudentController {

    private final List<Student> students = new ArrayList<>(
            List.of(
                    new Student(1, "Tarun", "Java"),
                    new Student(2, "Rahul", "Python"),
                    new Student(3, "Raj", "C++")
            )
    );

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return students;
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student) {
        students.add(student); // This will now work as `students` is mutable.
    }
}
