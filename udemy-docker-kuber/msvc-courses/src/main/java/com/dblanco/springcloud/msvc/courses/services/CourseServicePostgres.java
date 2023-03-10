package com.dblanco.springcloud.msvc.courses.services;

import com.dblanco.springcloud.msvc.courses.clients.UserClientRest;
import com.dblanco.springcloud.msvc.courses.models.User;
import com.dblanco.springcloud.msvc.courses.models.entity.Course;
import com.dblanco.springcloud.msvc.courses.models.entity.CourseUser;
import com.dblanco.springcloud.msvc.courses.repositories.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("CourseServicePostgres")
public class CourseServicePostgres implements CourseService {

    private final CourseRepository courseRepository;
    private final UserClientRest clientRest;

    public CourseServicePostgres(CourseRepository courseRepository, UserClientRest clientRest) {
        this.courseRepository = courseRepository;
        this.clientRest = clientRest;
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

    @Override
    @Transactional
    public Optional<User> assignUser(User user, Long courseId, String token) {
        Optional<Course> o = courseRepository.findById(courseId);
        if(o.isPresent()){
            User userMsvc = clientRest.detail(user.getId(), token);

            Course course = o.get();

            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(userMsvc.getId());

            course.addCourseUser(courseUser);
            courseRepository.save(course);

            return Optional.of(userMsvc);

        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, Long courseId, String token) {
        Optional<Course> o = courseRepository.findById(courseId);
        if(o.isPresent()){
            User newUserMsvc = clientRest.create(user, token);

            Course course = o.get();

            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(newUserMsvc.getId());

            course.addCourseUser(courseUser);
            courseRepository.save(course);

            return Optional.of(newUserMsvc);

        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> unAssignUser(User user, Long courseId, String token) {

        Optional<Course> o = courseRepository.findById(courseId);

        if(o.isPresent()){
            User newUserMsvc = clientRest.detail(user.getId(), token);

            Course course = o.get();

            CourseUser courseUser = new CourseUser();
            courseUser.setUserId(newUserMsvc.getId());

            course.removeCourseUser(courseUser);
            courseRepository.save(course);

            return Optional.of(newUserMsvc);

        }
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> getByIdWithUsers(Long id, String token) {

        Optional<Course> o = courseRepository.findById(id);
        if (o.isPresent()){
            Course course = o.get();
            if(!course.getCourseUsers().isEmpty()){
                List<Long> ids = course.getCourseUsers().stream().map(CourseUser::getUserId).toList();
                List<User> usersByCourses = clientRest.getUsersByCourses(ids, token);
                course.setUsers(usersByCourses);
            }
            return Optional.of(course);
        }

        return Optional.empty();


    }

    @Override
    @Transactional
    public void deleteCourseUserById(Long id) {
        courseRepository.deleteCourseUserById(id);
    }
}
