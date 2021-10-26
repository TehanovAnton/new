package com.example.carrentservice.repository;

import com.example.carrentservice.models.Users;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<Users, Long> {
}
