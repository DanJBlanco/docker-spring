package org.dblanco.springcloud.msvc.users.controllers;

import org.dblanco.springcloud.msvc.users.models.entity.User;
import org.dblanco.springcloud.msvc.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(@Qualifier("UserServiceMySql") UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<User> list(){
        return userService.list();
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

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@RequestBody User user, @PathVariable Long id){
        Optional<User> oUser = userService.detailById(id);

        if( oUser.isPresent()){
            User userDb = oUser.get();
            userDb.setName(user.getName());
            userDb.setEmail(user.getEmail());
            userDb.setPassword(user.getPassword());

            return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userDb));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<User> oUser = userService.detailById(id);
        if( oUser.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }



}
