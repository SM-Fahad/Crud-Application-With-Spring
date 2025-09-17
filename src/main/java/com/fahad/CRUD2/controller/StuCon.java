package com.fahad.CRUD2.controller;


import com.fahad.CRUD2.entity.Student;
import com.fahad.CRUD2.service.StuServ;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StuCon {
    @Autowired
    private final StuServ serv;

    public StuCon(StuServ serv) {
        this.serv = serv;
    }

    @PostMapping
    public Student create(@Valid @RequestBody Student stu) {
        return serv.save(stu);
    }

    @GetMapping
    public List<Student> getAll(){
        return serv.findAll();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return serv.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student stu) {
        stu.setId(id);
        return serv.save(stu);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serv.deleteById(id);
    }
}
