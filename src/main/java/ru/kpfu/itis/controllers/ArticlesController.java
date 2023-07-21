package ru.kpfu.itis.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.kpfu.itis.dtos.ArticleDto;
import ru.kpfu.itis.forms.ArticleForm;
import ru.kpfu.itis.models.Article;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.services.ArticlesService;
import ru.kpfu.itis.services.UsersService;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticlesController {

    @Autowired
    private ArticlesService articlesService;
    @Autowired
    private UsersService usersService;

    private Logger logger = LoggerFactory.getLogger(AnswersController.class);

    @Value("${custom.absolute.file.storage}")
    private String absoluteFilePath;

    @Value("${custom.file.storage}")
    private String filePath;

    @GetMapping
    public ModelAndView articlesPage() {
        ModelAndView modelAndView = new ModelAndView();
        List<Article> articles = articlesService.findAll();
        modelAndView.addObject("articles", articles);
        modelAndView.setViewName("articles");
        return modelAndView;
    }

    @GetMapping("/create")
    public String createArticlePage() {
        return "createArticle";
    }

    @PostMapping
    public ModelAndView createArticle(ArticleDto articleDto, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) authentication.getPrincipal();
        ArticleForm articleForm = new ArticleForm();
        articleForm.setTitle(articleDto.getTitle());
        articleForm.setContent(articleDto.getText());
        articleForm.setUser(user);

        MultipartFile file = articleDto.getArticleFile();
        if (file != null) {
            logger.info("Загружаем файл");
            String fileName = file.getOriginalFilename();
            try {
                file.transferTo(new File(absoluteFilePath + fileName));
                articleForm.setImagePath(filePath + fileName);
                logger.info("Файл успешно загружен");
            } catch (IOException e) {
                logger.error("Произошла ошибка во время загрузки файла");
            }
        }
        Article article = articlesService.create(articleForm);
        usersService.addPoints(user, 200);
        modelAndView.setViewName("redirect:/articles/" + article.getId());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView articlePage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Article article = articlesService.findById(id);
        modelAndView.addObject("article", article);
        modelAndView.setViewName("article");
        return modelAndView;
    }
}
