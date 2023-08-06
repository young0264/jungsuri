package com.app.jungsuri.domain.post.persistence;

import java.util.List;

public interface PostReadRepository {

    PostEntity findPostEntityById(Long id);

    List<PostEntity> findAllByTags(List<String> searchTags);
}
