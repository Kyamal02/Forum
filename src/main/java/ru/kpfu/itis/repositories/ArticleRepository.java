package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.Answer;
import ru.kpfu.itis.models.Article;
import ru.kpfu.itis.models.Dislike;
import ru.kpfu.itis.models.User;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    public List<Article> getAllByUser(User user);
}