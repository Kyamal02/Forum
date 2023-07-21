package ru.kpfu.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.dtos.AnswerDto;
import ru.kpfu.itis.forms.AnswerForm;
import ru.kpfu.itis.forms.DislikeForm;
import ru.kpfu.itis.forms.LikeForm;
import ru.kpfu.itis.models.Answer;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.QuestionsAndAnswersService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/answer")
public class AnswersController {

    @Autowired
    private QuestionsAndAnswersService questionsAndAnswersService;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Logger logger = LoggerFactory.getLogger(AnswersController.class);

    @Value("${custom.absolute.file.storage}")
    private String absoluteFilePath;

    @Value("${custom.file.storage}")
    private String filePath;

    @PostMapping("/{questionId}")
    public ModelAndView createAnswer(@PathVariable Long questionId, AnswerDto answerDto, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) authentication.getPrincipal();
        AnswerForm answerForm = new AnswerForm();
        answerForm.setText(answerDto.getText());
        answerForm.setUser(user);
        answerForm.setQuestion(questionsAndAnswersService.findQuestionById(questionId));

        MultipartFile file = answerDto.getAnswerFile();
        if (file != null) {
            logger.info("Загружаем файл");
            String fileName = file.getOriginalFilename();
            try {
                file.transferTo(new File(absoluteFilePath + fileName));
                answerForm.setImagePath(filePath + fileName);
                logger.info("Файл успешно загружен");
            } catch (IOException e) {
                logger.error("Произошла ошибка во время загрузки файла");
            }
        }
        Answer answer = questionsAndAnswersService.createAnswer(answerForm);

        modelAndView.setViewName("redirect:/questions/" + questionId);
        return modelAndView;
    }

    @PostMapping("/like")
    public void like(Long answerId, Authentication authentication, HttpServletResponse response) throws IOException {
        User user = (User) authentication.getPrincipal();
        LikeForm likeForm = new LikeForm();
        likeForm.setUser(user);
        Answer answer = questionsAndAnswersService.findAnswerById(answerId);
        likeForm.setAnswer(answer);
        questionsAndAnswersService.likeAnswer(likeForm);
        answer = questionsAndAnswersService.findAnswerById(answerId);
        answer.setQuestion(null);
        String json = objectMapper.writeValueAsString(answer);
        response.setContentType("application/json");
        response.getWriter().println(json);
    }

    @PostMapping("/dislike")
    public void dislike(Long answerId, Authentication authentication, HttpServletResponse response) throws IOException {
        User user = (User) authentication.getPrincipal();
        DislikeForm dislikeForm = new DislikeForm();
        dislikeForm.setUser(user);
        Answer answer = questionsAndAnswersService.findAnswerById(answerId);
        dislikeForm.setAnswer(answer);
        questionsAndAnswersService.dislikeAnswer(dislikeForm);
        answer = questionsAndAnswersService.findAnswerById(answerId);
        answer.setQuestion(null);
        String json = objectMapper.writeValueAsString(answer);
        response.setContentType("application/json");
        response.getWriter().println(json);
    }
}
