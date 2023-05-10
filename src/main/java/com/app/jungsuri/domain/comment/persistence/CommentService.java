package com.app.jungsuri.domain.comment.persistence;

import com.app.jungsuri.domain.comment.model.Comment;
import com.app.jungsuri.domain.comment.web.dto.CommentCreateDto;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void createComment(Comment comment, PostEntity postEntity) {
        postEntity.increaseCommentCount();
        commentRepository.save(comment.toEntity());
    }

    public void updateComment(Long commentId, String newComment) {
        commentRepository.findById(commentId).orElseGet(() -> {
            log.error("Comment not found");
            return null;
        }).updateComment(newComment);
    }

    public void deleteComment(Long commentId, PostEntity postEntity) {
        postEntity.decreaseCommentCount();
        commentRepository.deleteById(commentId);
    }
}
