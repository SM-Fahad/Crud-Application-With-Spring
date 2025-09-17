package com.fahad.CRUD2.service;

import com.fahad.CRUD2.entity.Department;
import com.fahad.CRUD2.repository.DeptRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeptServ {

    private final DeptRepo repo;

    public DeptServ(DeptRepo repo) {
        this.repo = repo;
    }

    public Department save(Department dept) {
        return repo.save(dept);
    }

    public List<Department> findAll() {
        return repo.findAll();
    }

    public Optional<Department> findById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id){
        repo.deleteById(id);
    }


}
