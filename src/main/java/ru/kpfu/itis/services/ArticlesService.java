package ru.kpfu.itis.services;

import ru.kpfu.itis.forms.ArticleForm;
import ru.kpfu.itis.models.Article;
import ru.kpfu.itis.models.User;

import java.util.List;

public interface ArticlesService {

    public Article create(ArticleForm articleForm);

    public Article findById(Long id);

    public List<Article> findAllByUser(User user);

    public List<Article> findAll();
}
