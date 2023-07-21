package ru.kpfu.itis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.models.Article;
import ru.kpfu.itis.models.Question;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.ArticlesService;
import ru.kpfu.itis.services.QuestionsAndAnswersService;

import java.util.List;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private QuestionsAndAnswersService questionsAndAnswersService;
    @Autowired
    private ArticlesService articlesService;

    @GetMapping
    public ModelAndView getProfile(Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();

        User user = (User) authentication.getPrincipal();
        modelAndView.addObject("user", user);

        List<Question> questions = questionsAndAnswersService.findAllQuestionsByUser(user);
        modelAndView.addObject("questions", questions);

        List<Article> articles = articlesService.findAllByUser(user);
        modelAndView.addObject("articles", articles);

        modelAndView.setViewName("profile");
        return modelAndView;
    }
}
