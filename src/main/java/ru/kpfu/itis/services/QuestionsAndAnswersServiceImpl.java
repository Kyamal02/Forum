package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.forms.AnswerForm;
import ru.kpfu.itis.forms.DislikeForm;
import ru.kpfu.itis.forms.LikeForm;
import ru.kpfu.itis.forms.QuestionForm;
import ru.kpfu.itis.models.*;
import ru.kpfu.itis.repositories.*;

import java.util.List;

@Service
public class QuestionsAndAnswersServiceImpl implements QuestionsAndAnswersService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private DislikeRepository dislikeRepository;
    @Autowired
    private UsersService usersService;

    @Override
    public Question createQuestion(QuestionForm questionForm) {
        Question build = Question.builder()
                .user(questionForm.getUser())
                .title(questionForm.getTitle())
                .text(questionForm.getText())
                .imagePath(questionForm.getImagePath())
                .build();
        usersService.removePoints(questionForm.getUser(), 200);
        return questionRepository.save(build);
    }

    @Override
    public Question findQuestionById(Long id) {
        Question question = questionRepository.findById(id).get();
        question.setAnswers(findAllAnswersByQuestion(question));
        return question;
    }

    @Override
    public List<Question> findAllQuestions() {
        List<Question> all = questionRepository.findAll();
        for (Question question : all) {
            question.setAnswers(findAllAnswersByQuestion(question));
        }
        return all;
    }

    @Override
    public List<Question> findAllQuestionsByUser(User user) {
        List<Question> questions = questionRepository.findAllByUser(user);
        for (Question question : questions) {
            question.setAnswers(answerRepository.findAllByQuestion(question));
        }
        return questions;
    }

    @Override
    public Answer createAnswer(AnswerForm answerForm) {
        Answer build = Answer.builder()
                .user(answerForm.getUser())
                .text(answerForm.getText())
                .imagePath(answerForm.getImagePath())
                .question(answerForm.getQuestion())
                .build();
        return answerRepository.save(build);
    }

    @Override
    public Answer findAnswerById(Long id) {
        Answer answer = answerRepository.findById(id).get();

        List<Like> likes = likeRepository.findAllByAnswer(answer);
        for (Like like : likes) {
            like.setAnswer(null);
        }
        answer.setLikes(likes);

        List<Dislike> dislikes = dislikeRepository.findAllByAnswer(answer);
        for (Dislike dislike : dislikes) {
            dislike.setAnswer(null);
        }
        answer.setDislikes(dislikes);
        return answer;
    }

    @Override
    public List<Answer> findAllAnswersByQuestion(Question question) {
        List<Answer> allByQuestion = answerRepository.findAllByQuestion(question);
        for (Answer answer : allByQuestion) {
            answer.setLikes(likeRepository.findAllByAnswer(answer));
            answer.setDislikes(dislikeRepository.findAllByAnswer(answer));
        }
        return allByQuestion;
    }

    @Override
    public void likeAnswer(LikeForm likeForm) {
        if (!likeRepository.existsByUserAndAnswer(likeForm.getUser(), likeForm.getAnswer()) &&
                !dislikeRepository.existsByUserAndAnswer(likeForm.getUser(), likeForm.getAnswer())) {
            Like build = Like.builder()
                    .user(likeForm.getUser())
                    .answer(likeForm.getAnswer())
                    .build();
            usersService.addPoints(likeForm.getAnswer().getUser(), 50);
            likeRepository.save(build);
        }
    }

    @Override
    public void dislikeAnswer(DislikeForm likeForm) {
        if (!dislikeRepository.existsByUserAndAnswer(likeForm.getUser(), likeForm.getAnswer()) &&
                !likeRepository.existsByUserAndAnswer(likeForm.getUser(), likeForm.getAnswer())) {
            Dislike build = Dislike.builder()
                    .user(likeForm.getUser())
                    .answer(likeForm.getAnswer())
                    .build();
            usersService.removePoints(likeForm.getAnswer().getUser(), 25);
            dislikeRepository.save(build);
        }
    }
}
