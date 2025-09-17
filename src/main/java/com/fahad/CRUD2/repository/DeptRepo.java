package com.fahad.CRUD2.repository;

import com.fahad.CRUD2.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepo extends JpaRepository<Department, Long> {

}
