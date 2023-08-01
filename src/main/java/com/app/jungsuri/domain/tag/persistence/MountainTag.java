package com.app.jungsuri.domain.tag.persistence;
import com.app.jungsuri.domain.mountain.persistence.MountainEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MountainTag extends Tag{
    //TODO : tag service를 만들어서 mountain_tag하고 tag 둘 다 저장

    @ManyToOne
    private MountainEntity mountainEntity;
}
