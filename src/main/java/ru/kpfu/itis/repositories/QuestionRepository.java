package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.Article;
import ru.kpfu.itis.models.Question;
import ru.kpfu.itis.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    public Optional<Question> findById(Long id);

    public List<Question> findAllByUser(User user);
}