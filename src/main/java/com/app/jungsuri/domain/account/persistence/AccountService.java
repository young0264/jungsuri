package com.app.jungsuri.domain.account.persistence;

import com.app.jungsuri.domain.account.web.form.SignUpForm;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
//    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    ModelMapper modelMapper = new ModelMapper();


    public void login(AccountEntity accountEntity) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                accountEntity,
                accountEntity.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
    }

    public AccountEntity createNewAccount(SignUpForm signUpForm) {
        System.out.println(signUpForm.getEmail());
        signUpForm.setPassword(passwordEncodeByRawPassword(signUpForm.getPassword()));
        initModelMapperSetting();
        return accountRepository.save(modelMapper.map(signUpForm, AccountEntity.class));
    }

    private String passwordEncodeByRawPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    private void initModelMapperSetting() {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);
    }

    /**
     * spring security
     */
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

        return accountContext;
    }

}
