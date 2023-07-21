package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.Answer;
import ru.kpfu.itis.models.Question;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    public List<Answer> findAllByQuestion(Question question);
}