package org.dblanco.springcloud.msvc.users.controllers;

import jakarta.validation.Valid;
import org.dblanco.springcloud.msvc.users.models.entity.User;
import org.dblanco.springcloud.msvc.users.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(@Qualifier("UserServiceMySql") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public Map<String, List<User>> list(){
        return Collections.singletonMap("user docker:", userService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id){

        Optional<User> oUser = userService.detailById(id);

        if(oUser.isPresent()){
            return ResponseEntity.ok(oUser.get());
        }

        return ResponseEntity.notFound().build();

//        return oUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping("/")
    public ResponseEntity<?> save(@Valid  @RequestBody User user, BindingResult result){

        if(result.hasErrors()){
            return validRequestBody(result);
        }

        if (userService.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest()
                    .body(Collections
                            .singletonMap("message", "Email already exist!!!")
                    );
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@Valid @RequestBody User user, BindingResult result, @PathVariable Long id){


        if(result.hasErrors()){
            return validRequestBody(result);
        }

        Optional<User> oUser = userService.detailById(id);

        if( oUser.isPresent()){
            User userDb = oUser.get();


            if ( !user.getEmail().isEmpty()
                    && !user.getEmail().equalsIgnoreCase(userDb.getEmail())
                    && userService.getByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest()
                        .body(Collections
                                .singletonMap("message", "Email already exist")
                        );
            }

            userDb.setName(user.getName());
            userDb.setEmail(user.getEmail());
            userDb.setPassword(user.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDb));
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


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> oUser = userService.detailById(id);
        if( oUser.isPresent()){
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/users-by-courses")
    public ResponseEntity<?> getUsersByCourses(@RequestParam List<Long> ids){
        return ResponseEntity.ok(userService.findAllById(ids));
    }

}
