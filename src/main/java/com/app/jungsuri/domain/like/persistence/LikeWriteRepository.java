package com.app.jungsuri.domain.like.persistence;

public interface LikeWriteRepository {

    public void deleteLikeByPostId(Long accountId, Long postId);
}