package com.app.jungsuri.domain.like.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;

    public boolean isCheckedLike(Long likesId) {
        likeRepository.isCheckedLike();
        return true;
    }
}
