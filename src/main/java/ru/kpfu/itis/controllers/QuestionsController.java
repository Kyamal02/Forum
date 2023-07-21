package ru.kpfu.itis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.dtos.QuestionDto;
import ru.kpfu.itis.forms.QuestionForm;
import ru.kpfu.itis.models.Question;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.QuestionsAndAnswersService;
import ru.kpfu.itis.services.UsersService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private QuestionsAndAnswersService questionsAndAnswersService;
    @Autowired
    private UsersService usersService;

    private Logger logger = LoggerFactory.getLogger(AnswersController.class);

    @Value("${custom.absolute.file.storage}")
    private String absoluteFilePath;

    @Value("${custom.file.storage}")
    private String filePath;

    @GetMapping
    public ModelAndView questionsPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<Question> questions = questionsAndAnswersService.findAllQuestions();
        modelAndView.addObject("questions", questions);
        modelAndView.setViewName("questions");
        return modelAndView;
    }

    @GetMapping("/create")
    public String createQuestionPage() {
        return "createQuestion";
    }

    @PostMapping
    public ModelAndView createQuestion(QuestionDto questionDto, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) authentication.getPrincipal();
        QuestionForm questionForm = new QuestionForm();
        questionForm.setText(questionDto.getText());
        questionForm.setTitle(questionDto.getTitle());
        questionForm.setUser(user);

        MultipartFile file = questionDto.getQuestionFile();
        if (file != null) {
            logger.info("Загружаем файл");
            String fileName = file.getOriginalFilename();
            try {
                file.transferTo(new File(absoluteFilePath + fileName));
                questionForm.setImagePath(filePath + fileName);
                logger.info("Файл успешно загружен");
            } catch (IOException e) {
                logger.error("Произошла ошибка во время загрузки файла");
            }
        }
        Question question = questionsAndAnswersService.createQuestion(questionForm);
        modelAndView.setViewName("redirect:/questions/" + question.getId());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView questionPage(@PathVariable Long id, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        if (authentication != null) {
            User user = (User) authentication.getPrincipal();
            modelAndView.addObject("user", user);
        }
        Question question = questionsAndAnswersService.findQuestionById(id);
        modelAndView.addObject("question", question);
        modelAndView.setViewName("question");
        return modelAndView;
    }
}
