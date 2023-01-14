package com.dblanco.springcloud.msvc.courses.services;

import com.dblanco.springcloud.msvc.courses.models.User;
import com.dblanco.springcloud.msvc.courses.models.entity.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    List<Course> getList();
    Optional<Course> getById(Long id);
    Course save(Course course);
    void delete(Long id);

    void deleteCourseUserById(Long id);

    Optional<User> assignUser(User user, Long courseId, String token);
    Optional<User> createUser(User user, Long courseId, String token);
    Optional<User> unAssignUser(User user, Long courseId, String token);


    Optional<Course> getByIdWithUsers(Long id, String token);

}
