package ru.kpfu.itis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kpfu.itis.models.User;

import java.util.Optional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String login);

    boolean existsByUsername(String username);

    @Modifying
    @Query("update User u set u.points = ?2 where u.id = ?1")
    void updatePoints(Long userId, Integer count);
}