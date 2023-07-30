package com.app.jungsuri.domain.like.persistence;

import com.app.jungsuri.domain.account.persistence.AccountRepository;
import com.app.jungsuri.domain.like.domain.LikeType;
import com.app.jungsuri.domain.post.persistence.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;

    public void updatePostLike(Long accountId, Long postId) {
        if(!isCheckedPostLike(accountId, postId)) {
            log.info("not checked post like");
            likeRepository.save(getLikeEntity(accountId, postId));
        } else {
            log.info("checked post like");
            likeRepository.deleteLikeByPostId(accountId, postId);
        }
    }

    private LikeEntity getLikeEntity(Long accountId, Long postId) {
        return LikeEntity.builder()
                .accountEntity(accountRepository.findById(accountId).orElseThrow(null))
                .postEntity(postRepository.findById(postId).orElseThrow(null))
                .type(LikeType.POST)
                .createdAt(LocalDateTime.now())
                .build();
    }

    /**
     * post like 확인 : like repository에 postid와 accountId가 일치하는지
     * comment like 확인 : like repository에 commentid와 accountId가 일치하는지
     * */
    public boolean isCheckedPostLike(Long accountId, Long postId) {
        return likeRepository.isCheckedPostLike(accountId, postId);
    }
}
