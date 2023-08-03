package com.app.jungsuri.domain.tag.persistence;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) //because
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", unique = true)
    private String name;

    @Column(name="used_count")
    private int usedCount = 0;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    public Tag(String name, LocalDateTime createdAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.usedCount = 1;
    }

    public void increaseUsedCount() {
        this.usedCount += 1;
    }
}
