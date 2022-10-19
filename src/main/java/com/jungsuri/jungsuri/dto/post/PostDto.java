package com.jungsuri.jungsuri.dto.post;

import com.jungsuri.jungsuri.domain.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
@Getter
@Setter
public class PostDto {

    private Member member;
    private String title;
    private String content;

//    private LocalDateTime createDate;
//
//    private LocalDateTime modifyDate;
//
//    private Member member;
}
