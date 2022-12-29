package com.dblanco.springcloud.msvc.courses.controller;

import com.dblanco.springcloud.msvc.courses.models.entity.Course;
import com.dblanco.springcloud.msvc.courses.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> save(@Valid @RequestBody Course course, BindingResult result){

        if(result.hasErrors()){
            return validRequestBody(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody Course courseReq, BindingResult result){

        if(result.hasErrors()){
            return validRequestBody(result);
        }

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


    private static ResponseEntity<Map<String, String>> validRequestBody(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
