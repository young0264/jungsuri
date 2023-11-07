package com.app.jungsuri.domain.tag.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn // 하위 테이블의 구분 컬럼 생성(default = DTYPE)
public abstract class Tag {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name", unique=true)
    private String name;

    @Column(name="used_count")
    private int usedCount = 0;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    public Tag(String name) {
        this.name = name;
        this.createdAt = LocalDateTime.now();
        this.usedCount = 1;
    }


    public void increaseUsedCount() {
        this.usedCount += 1;
    }

    public String toString() {
        return "Tag(id=" + this.getId() + ", name=" + this.getName() + ", usedCount=" + this.getUsedCount() + ", createdAt=" + this.getCreatedAt() + ")";
    }
}
