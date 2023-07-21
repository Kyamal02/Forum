package ru.kpfu.itis.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class QuestionDto {
    private String title;
    private String text;
    private MultipartFile questionFile;
}