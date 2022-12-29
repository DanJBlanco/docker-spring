package com.dblanco.springcloud.msvc.courses.repositories;

import com.dblanco.springcloud.msvc.courses.entity.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository  extends CrudRepository<Course, Long> {
}
