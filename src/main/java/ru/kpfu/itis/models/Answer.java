package ru.kpfu.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Question question;

    @Column(name = "text")
    private String text;

    @Column(name = "image_path")
    private String imagePath;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "answer")
    private List<Like> likes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "answer")
    private List<Dislike> dislikes;
}
