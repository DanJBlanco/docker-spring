package com.dblanco.springcloud.msvc.courses.services;

import com.dblanco.springcloud.msvc.courses.entity.Course;
import com.dblanco.springcloud.msvc.courses.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("CourseServicePostgres")
public class CourseServicePostgres implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServicePostgres(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getList() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> getById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
