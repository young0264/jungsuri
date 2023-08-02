package com.app.jungsuri.domain.tag.persistence;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED) //because
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int usedCount = 0;

    private LocalDateTime createdAt;

    public Tag(String name, int usedCount, LocalDateTime createdAt) {
        this.name = name;
        this.usedCount = usedCount;
        this.createdAt = createdAt;
    }

    public void increaseUsedCount() {
        this.usedCount += 1;
    }
}
