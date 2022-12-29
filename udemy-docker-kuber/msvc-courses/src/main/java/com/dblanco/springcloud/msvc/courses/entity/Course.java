package com.dblanco.springcloud.msvc.courses.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_courses")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;




}
