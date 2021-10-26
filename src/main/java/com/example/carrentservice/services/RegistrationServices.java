package com.example.carrentservice.services;

import com.example.carrentservice.models.Users;
import com.example.carrentservice.repository.UserRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegistrationServices
{
    public void Register(UserRepository repository, Users user)
    {
//        add validation

        repository.save(user);
    }
}
