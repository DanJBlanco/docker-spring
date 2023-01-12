package org.dblanco.springcloud.msvc.users.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-courses")
public interface CourseClientRest {

    @DeleteMapping("/delete-course-user/{userId}")
    void deleteCourseUser(@PathVariable Long userId);

}
