package org.dblanco.springcloud.msvc.users.services;

import org.dblanco.springcloud.msvc.users.client.CourseClientRest;
import org.dblanco.springcloud.msvc.users.models.entity.User;
import org.dblanco.springcloud.msvc.users.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("UserServiceMySql")
public class UserServiceMySql implements UserService {

    private final UserRepository userRepository;

    private final CourseClientRest clientRest;

    public UserServiceMySql(UserRepository userRepository, CourseClientRest clientRest) {
        this.userRepository = userRepository;
        this.clientRest = clientRest;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> list() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> detailById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        userRepository.deleteById(id);
        clientRest.deleteCourseUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getByEmail(String email) {
        return userRepository.findEmail(email);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public List<User> findAllById(Iterable<Long> ids) {
        return (List<User>) userRepository.findAllById(ids);
    }
}
