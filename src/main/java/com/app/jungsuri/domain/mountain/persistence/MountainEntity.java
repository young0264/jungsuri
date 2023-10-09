package com.app.jungsuri.domain.mountain.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@Table(name="mountain")
@NoArgsConstructor
@AllArgsConstructor
public class MountainEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="name", unique = true, nullable = false)
    private String name;

    @Column(name="height", nullable = false)
    private int height; //미터단위
    @Column(name="image_url", nullable = true)
    private String imageUrl;

    @Column(name="short_locations", nullable = true)
    private String shortLocations;

    public MountainEntity(String mountainName, int mountainHeight, String image_url_str, String shortLocations) {
        this.name = mountainName;
        this.height = mountainHeight;
        this.imageUrl = image_url_str;
        this.shortLocations = shortLocations;
    }
}
