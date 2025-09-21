package com.fahad.CRUD2.controller;

import com.fahad.CRUD2.DTO.CourDTO;
import com.fahad.CRUD2.DTO.StuDTO;
import com.fahad.CRUD2.service.CourServ;
import com.fahad.CRUD2.service.StuServ;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourCon {
    @Autowired
    private final CourServ serv;

    public CourCon(CourServ serv) {
        this.serv = serv;
    }

    @PostMapping
    public CourDTO create(@Valid @RequestBody CourDTO courdto) {
        return serv.createCourse(courdto);
    }

    @GetMapping
    public List<CourDTO> getAll(){
        return serv.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourDTO getById(@PathVariable Long id) {
        return serv.getCourseById(id);
    }

    @PutMapping("/{id}")
    public CourDTO update(@PathVariable Long id, @Valid @RequestBody CourDTO courDTO) {
        courDTO.setId(id);
        return serv.updateCourse(id, courDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serv.deleteCourse(id);
    }

    @GetMapping("/course/{id}")
    public CourDTO getCourseById(@PathVariable Long id) {
        return serv.getCourseById(id);
    }
}
