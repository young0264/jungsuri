package com.app.jungsuri.domain.like.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeUpdateResultDto {

    /** post id, comment id*/
    private Long id;
    private int likeCount;
    private boolean isLike;

    public LikeUpdateResultDto(int likeCount, boolean isLike, Long id) {
        this.id = id;
        this.likeCount = likeCount;
        this.isLike = isLike;
    }
}
