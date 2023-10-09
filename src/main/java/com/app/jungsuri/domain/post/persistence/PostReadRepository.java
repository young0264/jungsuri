package com.app.jungsuri.domain.post.persistence;

import java.util.List;

public interface PostReadRepository {

    PostEntity findPostEntityById(Long id);

    List<PostEntity> findAllByTags(List<String> searchTags);

    List<PostEntity> findTop5ByLikeCountAsc();

    List<PostEntity> findPostListByPagination(int startRowNum);

    List<PostEntity> findPostListByRecentTop3();

    int getPostCount();
}
