package com.jungsuri.jungsuri.domain.post;

import com.jungsuri.jungsuri.domain.member.Member;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    private String content;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;
    private String postTime;

//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    String username = principal.getClass().getName();
    public void modifyPost(String title,String  content,Member member) {
        this.title = title;
        this.content = content;
        this.member = member;

    }

}
