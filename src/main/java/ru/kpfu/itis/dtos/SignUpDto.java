package ru.kpfu.itis.dtos;

import lombok.*;

@Data
public class SignUpDto {
    private String username;
    private String email;
    private String password;
    private String retypePassword;
}
