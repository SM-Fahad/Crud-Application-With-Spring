package com.fahad.CRUD2.service;

import com.fahad.CRUD2.DTO.CourDTO;
import com.fahad.CRUD2.entity.Course;
import com.fahad.CRUD2.entity.Department;
import com.fahad.CRUD2.repository.CourRepo;
import com.fahad.CRUD2.repository.StuRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CourServ {
    private final CourRepo CourRepo;
    private final StuRepo stuRepo;

    public CourServ(CourRepo courRepo, StuRepo stuRepo) {
        CourRepo = courRepo;
        this.stuRepo = stuRepo;
    }

    @Transactional
    public CourDTO createCourse(CourDTO courdto) {
        Course course = new Course();
        course.setName(courdto.getName());

        if(courdto.getStudentIds() != null) {
            course.setStudents(new HashSet<>(stuRepo.findAllById(courdto.getStudentIds())));
        }

        Course saved = CourRepo.save(course);
        return mapToDTO(saved);
    }

    public List<CourDTO> getAllCourses() {
        List<Course> courseList = CourRepo.findAll();

        return courseList.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CourDTO getCourseById(Long id) {
        Optional<Course> course = CourRepo.findById(id);
        return course.map(this::mapToDTO).orElse(null);
    }

    public CourDTO updateCourse(Long id, CourDTO courdto) {
        Course course = CourRepo.findById(id).orElseThrow(() -> new RuntimeException("course not found"));
        course.setName(courdto.getName());

        if(courdto.getStudentIds() != null) {
            course.setStudents(new HashSet<>(stuRepo.findAllById(courdto.getStudentIds())));
        }

        return mapToDTO(CourRepo.save(course));
    }

    public void deleteCourse(Long id) {
        CourRepo.deleteById(id);
    }

    private CourDTO mapToDTO(Course course) {
        CourDTO dto = new CourDTO();
        dto.setId(course.getId());
        dto.setName(course.getName());
        return dto;
    }
}
