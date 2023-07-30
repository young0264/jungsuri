package com.app.jungsuri.domain.like.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class LikeUpdateResultDto {

    private int likeCount;
    private boolean isLike;

    public LikeUpdateResultDto(int likeCount, boolean isLike) {
        this.likeCount = likeCount;
        this.isLike = isLike;
    }
}
