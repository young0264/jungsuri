package com.app.jungsuri.domain.comment.repository;

import com.app.jungsuri.domain.comment.model.CommentEntity;

import java.util.List;

public interface CommentReadRepository {
    List<CommentEntity> findTop5ByOrderByCreatedAtDesc();
}
