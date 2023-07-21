package ru.kpfu.itis.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.models.Question;

@Data
public class AnswerDto {
    private String text;
    private Question question;
    private MultipartFile answerFile;
}