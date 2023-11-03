package com.app.jungsuri.domain.mountain.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mountain_exp")
public class MountainExpEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_entity_id", nullable = true)
    private AccountEntity accountEntity;

    @ManyToOne
    @JoinColumn(name = "mountain_entity_id", nullable = true)
    private MountainEntity mountainEntity;

    @Column(name = "hiking_date", nullable = true)
    private String hikingDate;

    @Column(name = "register_id", nullable = true)
    private Long registerId;

    public MountainExpEntity(AccountEntity accountEntity, MountainEntity mountainEntity, String hikingDate, Long id) {
        this.accountEntity = accountEntity;
        this.mountainEntity = mountainEntity;
        this.hikingDate = hikingDate;
        this.registerId = id;
    }
}
