package com.app.jungsuri.domain.mountain.persistence.MountainExp;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.mountain.persistence.MountainEntity;
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

    /**
     * 추후 type 구분
     * 100대명산, 서울시명산, 경기도명산 등등..
     * */

    public MountainExpEntity(AccountEntity accountEntity, MountainEntity mountainEntity, String hikingDate, Long id) {
        this.accountEntity = accountEntity;
        this.mountainEntity = mountainEntity;
        this.hikingDate = hikingDate;
        this.registerId = id;
    }
}
