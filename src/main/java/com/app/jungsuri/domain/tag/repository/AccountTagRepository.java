package com.app.jungsuri.domain.tag.repository;

import com.app.jungsuri.domain.tag.model.AccountTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTagRepository extends JpaRepository<AccountTag, Long>, AccountTagReadRepository{
}
