package com.app.jungsuri.domain.tag.persistence;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTag extends Tag{

    @ManyToOne
    private AccountEntity accountEntity;

}
