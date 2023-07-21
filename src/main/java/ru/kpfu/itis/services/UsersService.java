package ru.kpfu.itis.services;

import ru.kpfu.itis.models.User;
import ru.kpfu.itis.forms.SignInForm;
import ru.kpfu.itis.forms.UserForm;

import javax.servlet.http.Cookie;

public interface UsersService {

    User register(UserForm userForm);

    Cookie signIn(SignInForm authDto);

    User findUserByCookieValue(String cookieValue);

    boolean usernameDoesntExist(String username);

    void addPoints(User user, Integer points);

    void removePoints(User user, Integer points);
}