package com.app.jungsuri.domain.comment.persistence;


import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@Table(name="comment")
@NoArgsConstructor
@AllArgsConstructor
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private String author;

    private Integer likeCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToOne
    private PostEntity postEntity;

    @ManyToOne
    private AccountEntity accountEntity;

}
