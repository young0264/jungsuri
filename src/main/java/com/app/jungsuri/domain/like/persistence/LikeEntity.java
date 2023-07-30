package com.app.jungsuri.domain.like.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.like.domain.LikeType;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="likes")
@AllArgsConstructor
@NoArgsConstructor
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private PostEntity postEntity;

    @JoinColumn(nullable = false)
    @ManyToOne
    private AccountEntity accountEntity;

    @Column(nullable = false)
    private LikeType type;

    @Column(name="created_at", nullable = false)
    private String createdAt;

    @Column(name="updated_at", nullable = true)
    private String updatedAt;

}
