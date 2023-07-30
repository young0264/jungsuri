package com.app.jungsuri.domain.like.persistence;

import com.app.jungsuri.domain.account.persistence.AccountRepository;
import com.app.jungsuri.domain.like.domain.LikeType;
import com.app.jungsuri.domain.like.web.dto.LikeUpdateResultDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
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

    public LikeUpdateResultDto updatePostLike(Long accountId, Long postId) {
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글이 없습니다."));
        LikeUpdateResultDto likeUpdateResultDto;

        if(!isCheckedPostLike(accountId, postId)) {
            likeUpdateResultDto = new LikeUpdateResultDto(postEntity.increaseLikeCount(),true);
            likeRepository.save(getLikeEntity(accountId, postId));
        } else {
            likeUpdateResultDto = new LikeUpdateResultDto(postEntity.decreaseLikeCount(),false);
            likeRepository.deleteLikeByPostId(accountId, postId);
        }
        return likeUpdateResultDto;
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
