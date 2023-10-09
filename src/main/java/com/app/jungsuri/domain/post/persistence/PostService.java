package com.app.jungsuri.domain.post.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.post.event.PostCreatedEvent;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.infra.pagination.PostPage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ApplicationEventPublisher eventPublisher;

    /**
     * 게시글 count 가져오기
     */
    public int getPostCount() {
        return postRepository.getPostCount();
    }

    /**
     * pagination number에 따른 post list 가져오기
     */
    public List<PostEntity> getPostListByPagination(int pageNumber) {
        return postRepository.findPostListByPagination(getStartRowNum(pageNumber));
    }


    /**
     * 인기 게시글(top5) post entity 가져오기
     */
    public List<PostEntity> getTop5ListByLikeCount() {
        return postRepository.findTop5ByLikeCountAsc();
    }

    /** 최신 update date 순으로 모든 post list 가져오기 */
    public List<PostEntity> getPostList() {
        return postRepository.findAllByOrderByUpdatedAtDesc();
    }


    /** 게시글 생성 */
    public PostEntity createPost(PostCreateDto postCreateDto, AccountEntity accountEntity) {
        PostEntity postEntity = new PostEntity(postCreateDto, accountEntity);
        log.info("createPost getLoginId  : " + accountEntity.getLoginId());
        eventPublisher.publishEvent(new PostCreatedEvent(postEntity));
        return postRepository.save(postEntity);
    }

    /** post (pk) id로 post entity 가져오기 by JPA*/
    public PostEntity getPostEntity(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("ID에 해당하는 게시글이 없습니다."));
    }

    /** post 게시물 수정  */
    public void updatePost(PostEntity postEntityExist, Long postId) {
        PostEntity postEntity = getPostEntity(postId);
        postEntity.update(postEntityExist);
    }

    /** post 게시물 삭제 */
    public void deletePost(Long postId) {
        postRepository.delete(getPostEntity(postId));
    }

    /** post (pk) id로 post entity 가져오기 by JOOQ */
    @Transactional(readOnly = true)
    public PostEntity findPostEntityById(Long id) {
        return postRepository.findPostEntityById(id);
    }

    /** tags(태그들)에 해당하는 게시글 가져오기 */
    public List<PostEntity> getPostListByTags(List<String> searchTags) {
        return postRepository.findAllByTags(searchTags);
    }

    /** page number에 따른 list start row number 가져오기 */
    private int getStartRowNum(int pageNumber) {
        return PostPage.PAGE_ROW_SIZE.getValue()*(pageNumber-1)+1;
    }

    /** pagination 시작버튼 button
     * paging button이 button_size보다 작으면 1 반환
     * 그렇지 않으면 (현재 페이지 번호 / button_size) - 1 * button_size + 1 반환
     * */
    public int getBeforePageNum(int currentPageNumber) {
        int pageBtnSize = PostPage.PAGE_BTN_SIZE.getValue();
        int quotient = (currentPageNumber/pageBtnSize);

        if(currentPageNumber < pageBtnSize) {
            return 1;
        }
        if(currentPageNumber % pageBtnSize == 0) {
            return (quotient-1)*pageBtnSize;
        }return (quotient-1)*pageBtnSize+1;
    }

    /** pagination 끝버튼 button */
    public int getEndPageNum(int currentPageNumber) {
        int lastPageButtonNumber = getLastPageButtonNumber(); // 페이징 총 버튼 갯수
        int pageBtnSize = PostPage.PAGE_BTN_SIZE.getValue(); // 노출되는 페이징 버튼 범위
        int nextPageBtnNumber = (((currentPageNumber - 1) / pageBtnSize) + 1) * pageBtnSize + 1;

        /** 마지막 last page button 숫자가 더 작으면 last page button숫자 반환  */
        if (lastPageButtonNumber >= nextPageBtnNumber) {
            return nextPageBtnNumber;
        } return lastPageButtonNumber;

    }

    /** 가장 마지막 pagination button 가져오기 */
    private int getLastPageButtonNumber() {
        int postCount = getPostCount();
        int pageBtnSize = PostPage.PAGE_BTN_SIZE.getValue();

        if(postCount % pageBtnSize == 0) {
            return postCount/pageBtnSize;
        }return postCount/pageBtnSize + 1;
    }


    /** 페이징 버튼 갯수를 반환 */
    public int getPagingNumber() {
        int postCount = getPostCount();

        if(postCount % PostPage.PAGE_ROW_SIZE.getValue() == 0) {
            return postCount / PostPage.PAGE_ROW_SIZE.getValue();
        }return postCount / PostPage.PAGE_ROW_SIZE.getValue() + 1;
    }

}
