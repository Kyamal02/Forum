package ru.kpfu.itis.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ArticleDto {
    private String title;
    private String text;
    private MultipartFile articleFile;
}
