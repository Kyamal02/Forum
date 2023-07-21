package ru.kpfu.itis.dtos;

import lombok.Data;

@Data
public class SignInDto {
    private String email;
    private String password;
}