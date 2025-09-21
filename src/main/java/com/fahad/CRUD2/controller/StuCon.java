package com.fahad.CRUD2.controller;


import com.fahad.CRUD2.DTO.StuDTO;
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
    public StuDTO create(@Valid @RequestBody StuDTO stuDTO) {
        return serv.save(stuDTO);
    }

    @GetMapping
    public List<StuDTO> getAll(){
        return serv.getAllStudnets();
    }

    @GetMapping("/{id}")
    public StuDTO getById(@PathVariable Long id) {
        return serv.getStudentById(id);
    }

    @PutMapping("/{id}")
    public StuDTO update(@PathVariable Long id, @Valid @RequestBody StuDTO stu) {
        stu.setId(id);
        return serv.save(stu);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serv.deleteById(id);
    }

    @GetMapping("/student/{id}")
    public StuDTO getStudentById(@PathVariable Long id) {
        return serv.getStudentById(id);
    }
}
