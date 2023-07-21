package ru.kpfu.itis.forms;

import lombok.Data;
import ru.kpfu.itis.models.Answer;
import ru.kpfu.itis.models.User;

@Data
public class DislikeForm {

    private User user;
    private Answer answer;
}
