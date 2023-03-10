package com.dblanco.springcloud.msvc.courses.clients;

import com.dblanco.springcloud.msvc.courses.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-users")
public interface UserClientRest {

    @GetMapping("/{id}")
    User detail(@PathVariable Long id,  @RequestHeader(value = "Authorization", required = true) String token);

    @PostMapping("/")
    User create(@RequestBody User user,  @RequestHeader(value = "Authorization", required = true) String token);

    @GetMapping("/users-by-courses")
    List<User> getUsersByCourses(@RequestParam Iterable<Long> ids,
                                 @RequestHeader(value = "Authorization", required = true) String token);

}
