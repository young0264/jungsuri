package com.app.jungsuri.domain.post.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.comment.persistence.CommentEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name="post")
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String imagePath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    @NotNull
    @Column(unique = true)
    private String loginId;

    private String author;

    @ManyToOne
    private AccountEntity accountEntity;

    private Long likeCount;

    private Long commentCount;

    private Long viewCount;


    @OneToMany(mappedBy = "postEntity")
    private List<CommentEntity> comments;

}
