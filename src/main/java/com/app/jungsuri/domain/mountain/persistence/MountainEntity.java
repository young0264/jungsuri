package com.app.jungsuri.domain.mountain.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name="mountain")
@NoArgsConstructor
@AllArgsConstructor
public class MountainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    private int height; //미터단위

    private String imageUrl;

    private String shortLocations;

    public MountainEntity(String mountainName, int mountainHeight, String image_url_str, String shortLocations) {
        this.name = mountainName;
        this.height = mountainHeight;
        this.imageUrl = image_url_str;
        this.shortLocations = shortLocations;
    }
}
