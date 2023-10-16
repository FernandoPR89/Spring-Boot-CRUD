package com.portafolio.aplicacionrest.controller;

import com.portafolio.aplicacionrest.model.Student;
import com.portafolio.aplicacionrest.service.Studentservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private Studentservice studentservice;


    @PostMapping
    public ResponseEntity<Student> saveStudent (@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentservice.saveStudent(student));
    }


    @GetMapping
    public ResponseEntity<Page<Student>> getAllStudent (
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination
    ){
        return ResponseEntity.ok(studentservice.getAllStudent(page, size, enablePagination));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteStudent(@PathVariable ("id") Long id){
        studentservice.deleteStudent(id);
        return ResponseEntity.ok(!studentservice.existById(id));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Student>> findStudentById(@PathVariable ("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(studentservice.findById(id));
    }

    @PutMapping
    public ResponseEntity<Student> editStudent (@RequestBody Student student){
        return ResponseEntity.status(HttpStatus.CREATED).body(studentservice.editStudent(student));
    }
}
