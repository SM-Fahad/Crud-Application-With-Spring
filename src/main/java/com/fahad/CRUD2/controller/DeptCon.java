package com.fahad.CRUD2.controller;

import com.fahad.CRUD2.DTO.DeptDTO;
import com.fahad.CRUD2.entity.Department;
import com.fahad.CRUD2.service.DeptServ;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DeptCon {
    @Autowired
    private final DeptServ serv;

    public DeptCon(DeptServ serv) {
        this.serv = serv;
    }

//    @PostMapping
//    public Department create(@Valid @RequestBody Department dept) {
//        return serv.save(dept);
//    }

//    public DeptDTO create(@Valid @RequestBody DeptDTO deptdto) {
//        return serv.save(deptdto);
//    }

    @GetMapping
    public List<Department> getAll(){
        return serv.findAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return serv.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
//    public Department update(@PathVariable Long id, @Valid @RequestBody Department dept) {
//        dept.setId(id);
//        return serv.save(dept);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        serv.deleteById(id);
    }

}
