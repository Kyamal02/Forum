package ru.kpfu.itis.forms;

import lombok.Data;
import ru.kpfu.itis.models.Answer;
import ru.kpfu.itis.models.User;

import javax.persistence.ManyToOne;

@Data
public class LikeForm {

    private User user;
    private Answer answer;
}
