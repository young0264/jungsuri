package com.app.jungsuri.domain.account.persistence;

import com.app.jungsuri.domain.account.web.form.SignUpForm;
import com.app.jungsuri.domain.account.web.form.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;



    public void login(AccountEntity accountEntity) {
        System.out.println("로그인 시작");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
//                new UserAccount(accountEntity),
                accountEntity.getLoginId(),
                accountEntity.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
//        Authentication authentication = this.authenticationProvider.authenticate(token);

//        SecurityContext context = SecurityContextHolder.getContext();
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(token);
    }

    public AccountEntity createNewAccount(SignUpForm signUpForm) {
        signUpForm.setPassword(passwordEncodeByRawPassword(signUpForm.getPassword()));
        AccountEntity accountEntity = accountRepository.save(modelMapper.map(signUpForm, AccountEntity.class));
        return accountEntity;
    }

    private String passwordEncodeByRawPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }



    /**
     * spring security
     */
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        AccountEntity accountEntity = accountRepository.findByLoginId(loginId);
        if (accountEntity == null) {
            throw new UsernameNotFoundException("로그인 아이디에 해당하는 유저는 존재하지 않습니다.");
        }
        //권한 정보 등록
        List<GrantedAuthority> roles = new ArrayList<>();
//        roles.add(new SimpleGrantedAuthority(accountEntity.getRole()));
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        //AccountContext 생성자로 UserDetails 타입 생성
        AccountContext accountContext = new AccountContext(accountEntity, roles);

//        return accountContext;
        return new UserAccount(accountEntity);
    }

}
