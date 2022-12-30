package org.dblanco.springcloud.msvc.users.services;

import org.dblanco.springcloud.msvc.users.models.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> list();
    Optional<User> detailById(Long id);
    User save(User user);
    void delete(Long id);

    Optional<User> getByEmail(String email);

    boolean existsByEmail(String email);

    List<User> findAllById(Iterable<Long> ids);

}
