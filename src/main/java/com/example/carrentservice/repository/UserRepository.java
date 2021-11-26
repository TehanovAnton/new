package com.example.carrentservice.repository;

import com.example.carrentservice.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmailAndPassword(String email, String password);
    Set<User> findAllByEmailAndPassword(String email, String password);
    User findByEmail(String email);

}
