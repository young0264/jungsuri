package com.app.jungsuri.domain.comment.persistence;

import com.app.jungsuri.domain.comment.model.Comment;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentReadRepository commentReadRepository;

    /** 댓글 생성 */
    public CommentEntity createComment(Comment comment) {
        comment.getPostEntity().increaseCommentCount();
        return commentRepository.save(comment.toEntity());
    }

    /** 댓글 수정 */
    public void updateComment(Long commentId, String newComment) {
        commentRepository.findById(commentId).orElseGet(() -> {
            log.error("Comment not found");
            return null;
        }).updateComment(newComment);
    }

    /** 댓글 삭제 */
    public void deleteComment(Long commentId, PostEntity postEntity) {
        postEntity.decreaseCommentCount();
        commentRepository.deleteById(commentId);
    }

    /** 댓글 id(pk)로 commentEntity 가져오기 */
    public CommentEntity getComment(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("해당 댓글이 없습니다."));
    }

    /** 최신 댓글 5개 가져오기 */
    public List<CommentEntity> getRecentCommentList() {
        return commentReadRepository.findTop5ByOrderByCreatedAtDesc();
    }

}
