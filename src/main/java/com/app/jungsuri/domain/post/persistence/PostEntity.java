package com.app.jungsuri.domain.post.persistence;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.comment.persistence.CommentEntity;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name="POST")
//@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title", nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

//    @Column(name="short_content", nullable = false)
//    private String shortContent;

    @Column(name="image_path", nullable = true)
    private String imagePath;

    @Column(name="created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name="updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @NotNull
    @Column(name="login_id", nullable = false)
    private String loginId;

    @Column(name="author", nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name="account_entity_id", nullable = false)
    private AccountEntity accountEntity;

    @Column(name="comment_count", nullable = false)
    private int commentCount;

    @Column(name="like_count", nullable = false)
    private int likeCount;

    @Column(name="view_count", nullable = false)
    private int viewCount;


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

    public PostEntity(PostCreateDto postCreateDto,AccountEntity accountEntity) {
        this.title = postCreateDto.getTitle();
        this.content = postCreateDto.getContent();
        this.author = postCreateDto.getAuthor();
        this.imagePath = postCreateDto.getImagePath();
        this.commentCount = postCreateDto.getCommentCount();
        this.createdAt = LocalDateTime.now();
        this.loginId = accountEntity.getLoginId();
        this.accountEntity = accountEntity;
        this.likeCount = 0;
        this.viewCount = 0;
    }

    public int increaseLikeCount() {
        return this.likeCount += 1;
    }
    public int decreaseLikeCount() {
        if(this.likeCount == 0) return 0;
        return this.likeCount -= 1;
    }

}
