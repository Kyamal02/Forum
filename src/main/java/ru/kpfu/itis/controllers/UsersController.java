package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.dtos.SignUpDto;
import ru.kpfu.itis.forms.UserForm;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.UsersService;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @RequestMapping(method = RequestMethod.GET, value = "/signIn")
    private String signInPage() {
        return "signIn";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/signUp")
    private String signUpPage() {
        return "signUp";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signUp")
    private ModelAndView signUp(SignUpDto signUpDto, HttpServletResponse response) {

        String username = signUpDto.getUsername();
        String email = signUpDto.getEmail();
        String password = signUpDto.getPassword();
        String retypePassword = signUpDto.getRetypePassword();

        if (password.equals(retypePassword)) {
            UserForm userForm = new UserForm();
            userForm.setUsername(username);
            userForm.setEmail(email);
            userForm.setPassword(password);

            User user = usersService.register(userForm);
            if (user != null) {
                ModelAndView modelAndView = new ModelAndView();
                modelAndView.setViewName("redirect:/signIn");
                return modelAndView;
            }
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/signUp");
        return modelAndView;
    }
}
