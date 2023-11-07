package com.app.jungsuri.domain.tag.persistence;

import com.app.jungsuri.domain.mountain.model.MountainEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name="mountain_tag")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("MountainTag")
@PrimaryKeyJoinColumn(name = "mountain_tag_id")
public class MountainTag extends Tag{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @ManyToOne
    private MountainEntity mountainEntity;


    public MountainTag(MountainEntity mountainEntity, String name) {
        super(name);
        this.mountainEntity = mountainEntity;
    }
}
