package org.dblanco.springcloud.msvc.users.repositories;

import org.dblanco.springcloud.msvc.users.models.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query( "SELECT u from User u where u.email = ?1")
    Optional<User> findEmail(String email);

    boolean existsByEmail(String email);
}
