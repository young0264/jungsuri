package com.app.jungsuri.domain.tag.persistence;

import com.app.jungsuri.domain.mountain.persistence.MountainEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MountainTag extends Tag{

    @ManyToOne
    private MountainEntity mountainEntity;
}
