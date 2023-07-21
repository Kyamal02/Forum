package ru.kpfu.itis.forms;

import lombok.Data;
import ru.kpfu.itis.models.User;

@Data
public class ArticleForm {

    private User user;
    private String title;
    private String content;
    private String imagePath;
}
