package com.home.dreamcar.repository;

import com.home.dreamcar.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findById(Long id);

    User save(User user);
}
