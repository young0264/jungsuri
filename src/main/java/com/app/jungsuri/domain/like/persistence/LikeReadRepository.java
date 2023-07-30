package com.app.jungsuri.domain.like.persistence;

public interface LikeReadRepository {

    public boolean isCheckedPostLike(Long accountId, Long postId);

    public boolean isCheckedCommentLike(Long accountId, Long commentId);
}
