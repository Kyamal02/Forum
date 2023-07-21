package ru.kpfu.itis.forms;

import lombok.Data;
import ru.kpfu.itis.models.Answer;
import ru.kpfu.itis.models.User;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class QuestionForm {

    private User user;
    private String title;
    private String text;
    private String imagePath;
}
