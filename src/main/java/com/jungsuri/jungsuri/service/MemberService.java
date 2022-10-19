package com.jungsuri.jungsuri.service;

import com.jungsuri.jungsuri.domain.member.Member;
import com.jungsuri.jungsuri.domain.member.MemberRepository;
import com.jungsuri.jungsuri.dto.member.SignUpMemberDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
//@Builder
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입

    public Member createMember(SignUpMemberDto signUpMemberDto) {
        Member member = Member.builder()
                .username(signUpMemberDto.getUsername())
                .password(passwordEncoder.encode(signUpMemberDto.getPassword1()))
                .email(signUpMemberDto.getEmail())
                .build();
//        log.info(signUpMemberDto.getEmail());
        return memberRepository.save(member);
    }

    //유저 닉네임으로 멤버찾기
    public Member findMemberByusername(String username) {
        Optional<Member> member = memberRepository.findByUsername(username);
        return member.get();
    }

}
