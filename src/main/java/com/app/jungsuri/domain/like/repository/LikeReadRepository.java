package com.app.jungsuri.domain.like.repository;

import java.util.List;

public interface LikeReadRepository {
    public boolean isCheckedPostLike(Long accountId, Long postId);

    public boolean isCheckedCommentLike(Long accountId, Long commentId);

    public List<Long> getCommentLikeList(Long loginId, Long postId);

}
