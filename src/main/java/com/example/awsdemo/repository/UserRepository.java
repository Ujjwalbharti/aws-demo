package com.example.awsdemo.repository;

import com.example.awsdemo.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserName(String username);
    User findByEmail(String email);
}
