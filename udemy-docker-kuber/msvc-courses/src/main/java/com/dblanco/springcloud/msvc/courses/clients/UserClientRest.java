package com.dblanco.springcloud.msvc.courses.clients;

import com.dblanco.springcloud.msvc.courses.models.User;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-users", url = "localhost:8001")
public interface UserClientRest {

    @GetMapping("/{id}")
    User detail(@PathVariable Long id);

    @PostMapping("/")
    User create(@RequestBody User user);

    @GetMapping("/users-by-courses")
    List<User> getUsersByCourses(@RequestParam Iterable<Long> ids);

}
