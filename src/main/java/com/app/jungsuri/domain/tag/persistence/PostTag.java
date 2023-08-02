package com.app.jungsuri.domain.tag.persistence;

import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostHashTag extends Tag{

    @ManyToOne
    private PostEntity postEntity;
}
