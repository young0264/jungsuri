package com.app.jungsuri.domain.post.persistence;

public interface PostReadRepository {

    PostEntity findPostEntityById(Long id);
}
