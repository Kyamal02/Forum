package ru.kpfu.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.itis.forms.ArticleForm;
import ru.kpfu.itis.models.Article;
import ru.kpfu.itis.models.Question;
import ru.kpfu.itis.models.User;
import ru.kpfu.itis.repositories.ArticleRepository;

import java.util.List;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    @Autowired
    private ArticleRepository articleRepository;

    @Override
    public Article create(ArticleForm articleForm) {
        Article build = Article.builder()
                .user(articleForm.getUser())
                .title(articleForm.getTitle())
                .content(articleForm.getContent())
                .imagePath(articleForm.getImagePath())
                .build();
        return articleRepository.save(build);
    }

    @Override
    public Article findById(Long id) {
        return articleRepository.findById(id).get();
    }

    @Override
    public List<Article> findAllByUser(User user) {
        List<Article> articles = articleRepository.getAllByUser(user);
        return articles;
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
