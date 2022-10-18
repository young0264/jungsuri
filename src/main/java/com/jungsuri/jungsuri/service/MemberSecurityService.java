package com.jungsuri.jungsuri.service;

import com.jungsuri.jungsuri.domain.member.Member;
import com.jungsuri.jungsuri.domain.member.MemberRepository;
import com.jungsuri.jungsuri.domain.security.MemberRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    //사용자명으로 비밀번호를 조회, 리턴하는 메소드
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> loginUser = memberRepository.findByUsername(username);
        if (loginUser.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        Member member = loginUser.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if ("admin".equals(loginUser)) {
            authorities.add(new SimpleGrantedAuthority(MemberRole.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(MemberRole.MEMBER.getValue()));
        }
        return new User(member.getUsername(), member.getPassword(), authorities);
    }

}
