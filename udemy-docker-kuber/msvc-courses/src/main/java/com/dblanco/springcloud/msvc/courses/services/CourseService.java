package com.dblanco.springcloud.msvc.courses.services;

import com.dblanco.springcloud.msvc.courses.models.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getList();
    Optional<Course> getById(Long id);
    Course save(Course course);
    void delete(Long id);

}
