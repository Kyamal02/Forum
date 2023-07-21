package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.models.Auth;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.forms.SignInForm;
import ru.kpfu.itis.forms.UserForm;
import ru.kpfu.itis.repositories.AuthRepository;
import ru.kpfu.itis.repositories.UsersRepository;

import javax.servlet.http.Cookie;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsersServicesImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private AuthRepository authRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(UserForm userForm) {

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setEmail(userForm.getEmail());
        user.setPoints(1000);
        String passwordHash = passwordEncoder.encode(userForm.getPassword());
        user.setPasswordHash(passwordHash);
        System.out.println(user);
        return usersRepository.save(user);
    }

    @Override
    public Cookie signIn(SignInForm signInForm) {

        User user = null;
        Optional<User> optional = usersRepository.findByEmail(signInForm.getEmail());
        if (optional.isPresent()) user = optional.get();

        System.out.println(user);
        if (user != null) {
            if (passwordEncoder.matches(signInForm.getPassword(), user.getPasswordHash())) {
                System.out.println("Вход выполнен!");
                String cookieValue = UUID.randomUUID().toString();

                Auth auth = new Auth();
                auth.setUser(user);
                auth.setCookieValue(cookieValue);
                Auth auth1 = authRepository.save(auth);
                System.out.println(auth1);
                Cookie cookie = new Cookie("auth", cookieValue);
                cookie.setMaxAge(10 * 60 * 60);
                return cookie;
            } else {
                System.out.println("Вход не выполнен!");
            }
        }
        return null;
    }

    @Override
    public User findUserByCookieValue(String cookieValue) {
        Optional<Auth> optional = authRepository.findByCookieValue(cookieValue);

        Auth auth = null;
        if (optional.isPresent()) {
            auth = optional.get();
        }
        if (auth != null) {
            return auth.getUser();
        } else {
            return null;
        }
    }

    @Override
    public boolean usernameDoesntExist(String username) {
        return !usersRepository.existsByUsername(username);
    }

    @Override
    public void addPoints(User user, Integer points) {
        usersRepository.updatePoints(user.getId(), user.getPoints() + points);
    }

    @Override
    public void removePoints(User user, Integer points) {
        Integer count = 0;
        if (points < user.getPoints()) {
            count = user.getPoints() - points;
        }
        usersRepository.updatePoints(user.getId(), count);
    }
}