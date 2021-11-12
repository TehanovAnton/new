package com.example.carrentservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class SignInSet {
    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String password;
}
