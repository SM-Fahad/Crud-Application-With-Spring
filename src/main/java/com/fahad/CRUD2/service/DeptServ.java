package com.fahad.CRUD2.service;

import com.fahad.CRUD2.DTO.DeptDTO;
import com.fahad.CRUD2.DTO.StuDTO;
import com.fahad.CRUD2.entity.Department;
import com.fahad.CRUD2.repository.DeptRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeptServ {

    private final DeptRepo deptRepo;

    public DeptServ(DeptRepo deptRepo) {
        this.deptRepo = deptRepo;
    }

//    @Transactional
//    public Department save(Department dept) {
//        return deptRepo.save(dept);
//    }

//    public DeptDTO save(DeptDTO deptdto) {
//        Department dp = new Department()
//        return toDTO(deptRepo.save(dp));
//    }

    @Transactional
    public ResponseEntity<?> saveDepartment2(DeptDTO dto) {
        try {
            Optional<Department> existing = deptRepo.findByName(dto.getName());
            if (existing.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body("Department already exists with name: " + dto.getName());
            }

            Department saved = deptRepo.save(toEntity(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to create department: " + e.getMessage());
        }
    }

    public List<Department> findAll() {
        return deptRepo.findAll();
    }

    public Optional<Department> findById(Long id) {
        return deptRepo.findById(id);
    }

    public void deleteById(Long id){
        deptRepo.deleteById(id);
    }


    // ---------- DTO Mappers ----------
    public DeptDTO toDTO(Department department) {
        if (department == null) return null;
        DeptDTO dto = new DeptDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());

        // Map students to StudentDTO (summary)
        if (department.getStudents() != null) {
            List<StuDTO> students = department.getStudents().stream().map(s -> {
                StuDTO studentDTO = new StuDTO();
                studentDTO.setId(s.getId());
                studentDTO.setFirstname(s.getFirstName());
                studentDTO.setLastname(s.getLastName());
                studentDTO.setEmail(s.getEmail());
                return studentDTO;
            }).collect(Collectors.toList());
            dto.setStudents(students);
        }

        return dto;
    }

    public Department toEntity(DeptDTO dto) {
        if (dto == null) return null;
        Department dept = new Department();
        dept.setId(dto.getId());
        dept.setName(dto.getName());
        // Note: We do not set students here to avoid overriding existing ones
        return dept;
    }
}
