package com.app.jungsuri.domain.like.repository;

public interface LikeWriteRepository {

    public void deleteLikeByPostId(Long accountId, Long postId);
    public void deleteLikeByCommentId(Long accountId, Long postId);
}
