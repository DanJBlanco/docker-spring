package org.djblanco.springcloud.msvc.auth.services;

import org.djblanco.springcloud.msvc.auth.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collections;

@Service
public class UserServices implements UserDetailsService {

    private final WebClient client;

    private Logger log = LoggerFactory.getLogger(UserServices.class);

    public UserServices(WebClient client) {
        this.client = client;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = client.get()
                    .uri("http://msvc-users:8001/login", uriBuilder -> uriBuilder.queryParam("email", email).build())
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(User.class)
                    .block();

            log.info("User login: {}", user);

            return new org.springframework.security.core.userdetails
                    .User(email, user.getPassword(), true, true, true, true,
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

        } catch (RuntimeException e){
            String error = "Login ERROR - User doesnt exist " + email +
                    " in system" ;
            log.error("ERROR: {}", error);
            log.error(e.getMessage());
            throw new UsernameNotFoundException(error);
        }

        return null;
    }
}
