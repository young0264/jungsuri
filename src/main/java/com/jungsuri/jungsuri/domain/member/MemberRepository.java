package com.jungsuri.jungsuri.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


}
