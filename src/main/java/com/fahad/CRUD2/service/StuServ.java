package com.fahad.CRUD2.service;

import com.fahad.CRUD2.DTO.DeptDTO;
import com.fahad.CRUD2.DTO.StuDTO;
import com.fahad.CRUD2.entity.Course;
import com.fahad.CRUD2.entity.Department;
import com.fahad.CRUD2.entity.Student;
import com.fahad.CRUD2.repository.CourRepo;
import com.fahad.CRUD2.repository.DeptRepo;
import com.fahad.CRUD2.repository.StuRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StuServ {
    private final StuRepo stuRepo;
    private final CourRepo courRepo;
    private final DeptRepo deptRepo;

    public StuServ(StuRepo repo, CourRepo courRepo, DeptRepo deptRepo) {
        this.stuRepo = repo;
        this.courRepo = courRepo;
        this.deptRepo = deptRepo;
    }

    @Transactional
    public StuDTO save(StuDTO studto) {
        Student stu = toEntity(studto);
        return toDTO(stuRepo.save(stu));
    }

    public List<StuDTO> getAllStudnets() {
        return stuRepo.findAll().stream().map(this::toDTO).toList();
    }

    public StuDTO getStudentById(Long id) {
        return stuRepo.findById(id).map(this::toDTO)
                .orElseThrow(()-> new RuntimeException("Student not found with id: " + id));
    }

    public void deleteById(Long id){
        if(!stuRepo.existsById(id)){
            throw new RuntimeException("Student not found with id: " + id);
        }
        stuRepo.deleteById(id);
    }

//    public StuDTO getStudentById(Long id) {
//        Optional<Student> stu = stuRepo.findById(id);
//        StuDTO std = new StuDTO();
//        stu.ifPresent(student -> {
//            BeanUtils.copyProperties(student, std);
//            Set<Long> oldC = student.getCourses().stream().map(Course::getId).collect(Collectors.toSet());
//        }
//        );
//        return std;
//    }

    public StuDTO toDTO (Student stu) {
        if (stu == null) return null;
        StuDTO studto = new StuDTO();
        studto.setId(stu.getId());
        studto.setFirstname(stu.getFirstName());
        studto.setLastname(stu.getLastName());
        studto.setEmail(stu.getEmail());
        studto.setDob(stu.getDob());

        if(stu.getDepartment() != null) {
            DeptDTO dt = new DeptDTO();
            dt.setId(stu.getDepartment().getId());
            dt.setName(stu.getDepartment().getName());
            studto.setDeptDto(dt);
        }

        if(stu.getCourses() != null) {
            studto.setCourseIds(
                    stu.getCourses().stream().map(Course::getId).collect(Collectors.toSet())
            );
        }
        return studto;
    }

    public Student toEntity(StuDTO studto) {
        if (studto == null) return null;

        Student stu = new Student();
        stu.setId(studto.getId());
        stu.setFirstName(studto.getFirstname());
        stu.setLastName(studto.getLastname());
        stu.setEmail(studto.getEmail());
        stu.setDob(studto.getDob());

        if(studto.getDeptDto()!=null) {
            Department dp = deptRepo.findById(studto.getDeptDto().
                    getId()).orElseThrow(() -> new RuntimeException("Department Not Found"));
//            dp.setId(studto.getDeptDto().getId());
            dp.setName(studto.getDeptDto().getName());
            stu.setDepartment(dp);
        }

        if(studto.getCourseIds() != null && !studto.getCourseIds().isEmpty()){
            Set<Course> courses = new HashSet<>(courRepo.findAllById(studto.getCourseIds()));
            stu.getCourses().clear();
            stu.getCourses().addAll(courses);
        }

        return stu;
    }
}
