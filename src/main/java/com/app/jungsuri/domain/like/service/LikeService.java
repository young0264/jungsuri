package com.app.jungsuri.domain.like.service;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.repository.AccountRepository;
import com.app.jungsuri.domain.comment.model.CommentEntity;
import com.app.jungsuri.domain.comment.repository.CommentRepository;
import com.app.jungsuri.domain.like.model.LikeType;
import com.app.jungsuri.domain.like.model.LikeEntity;
import com.app.jungsuri.domain.like.repository.LikeRepository;
import com.app.jungsuri.domain.like.dto.CommentLikeUpdateDto;
import com.app.jungsuri.domain.like.dto.LikeUpdateResultDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final AccountRepository accountRepository;
    private final CommentRepository commentRepository;

    /** 특정 comment에 좋아요 여부에 따른 update*/
    public LikeUpdateResultDto updateCommentLike(CommentLikeUpdateDto commentLikeUpdateDto) {
        Long accountId = commentLikeUpdateDto.getAccountId();
        Long postId = commentLikeUpdateDto.getPostId();
        Long commentId = commentLikeUpdateDto.getCommentId();

        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 유저가 없습니다."));
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글이 없습니다."));
        CommentEntity commentEntity = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 댓글이 없습니다."));
        LikeUpdateResultDto likeUpdateResultDto;

        if(!isCheckedCommentLike(accountId, commentId)) {
            likeUpdateResultDto = new LikeUpdateResultDto(commentEntity.increaseLikeCount() ,true, commentId);
            likeRepository.save(getLikeEntity(accountEntity, postEntity, commentEntity, LikeType.COMMENT));
        } else {
            likeUpdateResultDto = new LikeUpdateResultDto(commentEntity.decreaseLikeCount(), false, commentId);
            likeRepository.deleteLikeByCommentId(accountId, commentId);
        }
        return likeUpdateResultDto;
    }

    /** 특정 post에 좋아요 여부에 따른 좋아요 수정.(update) */
    public LikeUpdateResultDto updatePostLike(Long accountId, Long postId) {
        AccountEntity accountEntity = accountRepository.findById(accountId).orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 유저가 없습니다."));
        PostEntity postEntity = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글이 없습니다."));
        LikeUpdateResultDto likeUpdateResultDto;

        if(!isCheckedPostLike(accountId, postId)) {
            likeUpdateResultDto = new LikeUpdateResultDto(postEntity.increaseLikeCount(),true, postId);
            likeRepository.save(getLikeEntity(accountEntity, postEntity, null, LikeType.POST));
        } else {
            likeUpdateResultDto = new LikeUpdateResultDto(postEntity.decreaseLikeCount(),false, postId);
            likeRepository.deleteLikeByPostId(accountId, postId);
        }
        return likeUpdateResultDto;
    }


    /**
     * post like 확인 : like repository에 postid와 accountId가 일치하는지
     * comment like 확인 : like repository에 commentid와 accountId가 일치하는지
     * */
    public boolean isCheckedPostLike(Long accountId, Long postId) {
        return likeRepository.isCheckedPostLike(accountId, postId);
    }

    public boolean isCheckedCommentLike(Long accountId, Long commentId) {
        return likeRepository.isCheckedCommentLike(accountId, commentId);
    }

    /** 현재 유저 id에서 post에 댓글중에 좋아요를 누른 comment id 리스트 가져오기*/
    public List<Long> getCommentLikeList(Long accountId, Long postId) {
        return likeRepository.getCommentLikeList(accountId, postId);
    }

    private LikeEntity getLikeEntity(AccountEntity accountEntity, PostEntity postEntity, CommentEntity commentEntity, LikeType type) {
        return LikeEntity.builder()
                .accountEntity(accountEntity)
                .postEntity(postEntity)
                .commentEntity(commentEntity)
                .type(type)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
