package com.app.jungsuri.domain.tag.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Entity
@Getter
@Setter //
@Builder //
@NoArgsConstructor
@DiscriminatorValue("AccountTag")
@Table(name="account_tag")
@PrimaryKeyJoinColumn(name = "account_tag_id")
public class AccountTag extends Tag{

    @ManyToOne
    private AccountEntity accountEntity;

    public AccountTag(AccountEntity accountEntity) {
        super(accountEntity.getLoginId());
        log.info("accountTag 생성자 시작");
        log.info(accountEntity.toString());
        this.accountEntity = accountEntity;
    }

}
