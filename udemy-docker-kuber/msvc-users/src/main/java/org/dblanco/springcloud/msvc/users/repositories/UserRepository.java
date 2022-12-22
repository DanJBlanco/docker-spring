package org.dblanco.springcloud.msvc.users.repositories;

import org.dblanco.springcloud.msvc.users.models.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
