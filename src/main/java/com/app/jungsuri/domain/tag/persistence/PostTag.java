package com.app.jungsuri.domain.tag.persistence;

import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private PostEntity postEntity;

    @ManyToOne
    private Tag tag;

    public PostTag(PostEntity postEntity, Tag tag) {
        this.postEntity = postEntity;
        this.tag = tag;
    }

}
