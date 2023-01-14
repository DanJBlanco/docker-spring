package org.dblanco.springcloud.msvc.users.controllers;

import jakarta.validation.Valid;
import org.dblanco.springcloud.msvc.users.models.entity.User;
import org.dblanco.springcloud.msvc.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    private final UserService userService;

    private final ApplicationContext context;

    private final Environment env;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserController(@Qualifier("UserServiceMySql") UserService userService, ApplicationContext applicationContext, Environment env, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.context = applicationContext;
        this.env = env;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/crash")
    public void crash(){
        ((ConfigurableApplicationContext) context).close();
    }

    @GetMapping("/")
    public ResponseEntity<?> list(){
        Map<String, Object> body = new HashMap<>();
        body.put("user Kubernetes stages:", userService.list());
        body.put("podInfo", env.getProperty("MY_POD_NAME") + ": " + env.getProperty("MY_POD_IP"));
        body.put("Text: ", env.getProperty("config.text"));
        return ResponseEntity.ok(body);
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
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
            user.setPassword(passwordEncoder.encode(user.getPassword()));

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

    @GetMapping("/authorized")
    public Map<String, Object> auth(@RequestParam(name = "code") String code){
        return Collections.singletonMap("code", code);

    }

    @GetMapping("/login")
    public ResponseEntity<?> loginByEmail(@RequestParam String email){

        Optional<User> oEmail = userService.getByEmail(email);
        if (oEmail.isPresent()){
            return ResponseEntity.ok(oEmail.get());
        }

        return ResponseEntity.notFound().build();

    }

}
