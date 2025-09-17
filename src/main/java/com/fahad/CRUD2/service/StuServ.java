package com.fahad.CRUD2.service;

import com.fahad.CRUD2.entity.Student;
import com.fahad.CRUD2.repository.StuRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StuServ {
    private final StuRepo repo;

    public StuServ(StuRepo repo) {
        this.repo = repo;
    }

    public Student save(Student stu) {
        return repo.save(stu);
    }

    public List<Student> findAll() {
        return repo.findAll();
    }

    public Optional<Student> findById(Long id) {
        return repo.findById(id);
    }

    public void deleteById(Long id){
        repo.deleteById(id);
    }
}
