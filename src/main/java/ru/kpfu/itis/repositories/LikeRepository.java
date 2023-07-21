package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.models.Answer;
import ru.kpfu.itis.models.Like;
import ru.kpfu.itis.models.Question;
import ru.kpfu.itis.models.User;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {

    public List<Like> findAllByAnswer(Answer answer);

    boolean existsByUserAndAnswer(User user, Answer answer);
}