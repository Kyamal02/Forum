package ru.kpfu.itis.forms;

import lombok.Data;
import ru.kpfu.itis.models.Answer;
import ru.kpfu.itis.models.Like;
import ru.kpfu.itis.models.Question;
import ru.kpfu.itis.models.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class AnswerForm {

    private User user;
    private Question question;
    private String text;
    private String imagePath;
}
