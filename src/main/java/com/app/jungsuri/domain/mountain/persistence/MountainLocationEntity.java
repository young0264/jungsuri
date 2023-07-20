package com.app.jungsuri.domain.mountain.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="mountainLocation")
@NoArgsConstructor
@AllArgsConstructor
public class MountainLocationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    @ManyToOne
    private MountainEntity mountainEntity;

    public MountainLocationEntity(String mountainAllLocation, MountainEntity mountainEntity) {
        this.location = mountainAllLocation;
        this.mountainEntity = mountainEntity;
    }
}
