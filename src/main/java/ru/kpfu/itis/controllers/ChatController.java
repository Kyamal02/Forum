package ru.kpfu.itis.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.User;

@Controller
@RequestMapping("/main_chat")
public class ChatController {

    @GetMapping
    public ModelAndView mainChatPage(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        ModelAndView modelAndView = new ModelAndView("mainChat");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
