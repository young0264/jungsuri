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
    private String loginId;

    private String author;

    @ManyToOne
    private AccountEntity accountEntity;

    private Integer commentCount;

    private Long likeCount;

    private Long viewCount;


    @OneToMany(mappedBy = "postEntity")
    private List<CommentEntity> commentList;

    @Override
    public String toString() {
        return "PostEntity {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", loginId='" + loginId + '\'' +
                ", author='" + author + '\'' +
                ", commentCount=" + commentCount +
                ", likeCount=" + likeCount +
                ", viewCount=" + viewCount +
                '}';
    }

    public void update(PostEntity postEntity) {
        this.title = postEntity.getTitle();
        this.content = postEntity.getContent();
        this.imagePath = postEntity.getImagePath();
        this.updatedAt = LocalDateTime.now();
    }

    public void increaseCommentCount() {
        this.commentCount++;
    }

    public void decreaseCommentCount() {
        this.commentCount--;
    }
}
