package com.app.jungsuri.domain.post.persistence;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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

    private String author;

    private Long account_id;

    private Long likeCount;

    private Long commentCount;

    private Long viewCount;


//    @OneToMany(mappedBy = "post")
//    private List<CommentEntity> comments;

    public void update(PostEntity postEntity) {
        this.title = postEntity.getTitle();
        this.content = postEntity.getContent();
        this.imagePath = postEntity.getImagePath();
        this.updatedAt = LocalDateTime.now();
    }
}
