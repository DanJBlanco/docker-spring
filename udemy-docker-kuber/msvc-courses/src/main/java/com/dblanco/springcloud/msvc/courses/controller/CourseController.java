package com.dblanco.springcloud.msvc.courses.controller;

import com.dblanco.springcloud.msvc.courses.entity.Course;
import com.dblanco.springcloud.msvc.courses.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CourseController {

    private final CourseService courseService;


    public CourseController(@Qualifier("CourseServicePostgres") CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/")
    public ResponseEntity<List<Course>> getList(){
        return ResponseEntity.ok(courseService.getList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id){
        Optional<Course> oCourse = courseService.getById(id);

        return oCourse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody Course course){
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Course courseReq){

        Optional<Course> oCourse = courseService.getById(id);

        if(oCourse.isPresent()){
            Course coursePut = oCourse.get();
            coursePut.setName(courseReq.getName());

            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(coursePut));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        Optional<Course> oCourse = courseService.getById(id);

        if(oCourse.isPresent()){
            courseService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
