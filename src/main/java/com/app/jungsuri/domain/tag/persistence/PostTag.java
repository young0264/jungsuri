package com.app.jungsuri.domain.tag.persistence;

import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("PostTag")
@PrimaryKeyJoinColumn(name = "post_tag_id")
public class PostTag extends Tag{

    @ManyToOne
    private PostEntity postEntity;

    public PostTag(PostEntity postEntity, String tagName) {
        super(tagName);
        this.postEntity = postEntity;
    }

}
