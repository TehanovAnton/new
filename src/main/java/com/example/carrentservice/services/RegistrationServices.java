package com.example.carrentservice.services;

import com.example.carrentservice.models.User;
import com.example.carrentservice.repository.UserRepository;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegistrationServices
{
    public void Register(UserRepository repository, User user)
    {
//        add validation

        repository.save(user);
    }
}
