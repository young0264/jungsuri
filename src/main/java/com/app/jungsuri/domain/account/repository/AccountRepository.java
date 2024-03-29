package com.app.jungsuri.domain.account.repository;

import com.app.jungsuri.domain.account.model.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public interface AccountRepository extends JpaRepository<AccountEntity, Long>, AccountReadRepository {

    Optional<AccountEntity> findByLoginId(String loginId);
}
