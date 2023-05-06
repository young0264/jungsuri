package com.app.jungsuri.domain.post.persistence;

import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final ModelMapper modelMapper;

    public List<PostEntity> getPostList() {
        return postRepository.findAllByOrderByUpdatedAtDesc();
    }

    public PostEntity cretePost(PostCreateDto postCreateDto, String loginId) {
        PostEntity postEntity = postCreateDto.toPost(loginId).toEntity();
        return postRepository.save(postEntity);
    }

    public PostEntity getPostEntity(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글이 없습니다."));
    }

    public void updatePost( PostEntity existingPost, PostEntity postEntity) {
        modelMapper.map(existingPost, postEntity);
    }

    public void deletePost(Long postId) {
        postRepository.delete(getPostEntity(postId));
    }
}
