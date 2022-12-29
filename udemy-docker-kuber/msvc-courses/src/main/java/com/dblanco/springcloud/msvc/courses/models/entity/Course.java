package com.dblanco.springcloud.msvc.courses.models.entity;

import com.dblanco.springcloud.msvc.courses.models.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<CourseUser> courseUsers;

    @Transient
    private List<User> users;


    public Course() {
        courseUsers = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addCourseUser(CourseUser courseUser){
        courseUsers.add(courseUser);
    }

    public void removeCourseUser(CourseUser courseUser){
        courseUsers.remove(courseUser);
    }


}
