package ru.kpfu.itis.services;

import ru.kpfu.itis.forms.AnswerForm;
import ru.kpfu.itis.forms.DislikeForm;
import ru.kpfu.itis.forms.LikeForm;
import ru.kpfu.itis.forms.QuestionForm;
import ru.kpfu.itis.models.Answer;
import ru.kpfu.itis.models.Question;
import ru.kpfu.itis.models.User;

import java.util.List;

public interface QuestionsAndAnswersService {

    public Question createQuestion(QuestionForm questionForm);

    public Question findQuestionById(Long id);

    public List<Question> findAllQuestions();

    public List<Question> findAllQuestionsByUser(User user);


    public Answer createAnswer(AnswerForm answerForm);

    public Answer findAnswerById(Long id);

    public List<Answer> findAllAnswersByQuestion(Question question);

    public void likeAnswer(LikeForm likeForm);

    public void dislikeAnswer(DislikeForm likeForm);

}
