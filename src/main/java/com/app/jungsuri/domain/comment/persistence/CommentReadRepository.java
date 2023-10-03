package com.app.jungsuri.domain.comment.persistence;

import java.util.List;

public interface CommentReadRepository {

    List<CommentEntity> findTop5ByOrderByCreatedAtDesc();
}
