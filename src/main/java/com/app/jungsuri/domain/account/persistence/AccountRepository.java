package com.app.jungsuri.domain.account.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByLoginId(String loginId);
}
