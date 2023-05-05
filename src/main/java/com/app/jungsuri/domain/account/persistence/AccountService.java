package com.app.jungsuri.domain.account.persistence;

import com.app.jungsuri.domain.account.web.form.SignUpForm;
import com.app.jungsuri.domain.account.web.form.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    //TODO login method 권한인증 안됨.()
    public void login(AccountEntity accountEntity) {
        System.out.println("로그인 시작");

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                accountEntity.getLoginId(),
                accountEntity.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER"))
        );
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(token);
    }

    public AccountEntity createNewAccount(SignUpForm signUpForm) {
        signUpForm.setPassword(passwordEncodeByRawPassword(signUpForm.getPassword()));
        AccountEntity accountEntity = accountRepository.save(modelMapper.map(signUpForm, AccountEntity.class));
        accountEntity.generateEmailCheckToken();
        sendSignUpConfirmEmail(accountEntity);

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

        return new UserAccount(accountEntity);
    }


    public AccountEntity findByLoginId(String loginId) {
        return accountRepository.findByLoginId(loginId);
    }

    public void completeSignUp(AccountEntity accountEntity) {
        accountEntity.completeSignUp();
        login(accountEntity);
    }


    public void sendSignUpConfirmEmail(AccountEntity accountEntity) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setReplyTo(accountEntity.getEmail());
        simpleMailMessage.setTo(accountEntity.getEmail());
        simpleMailMessage.setSubject("[정수리 꼬순내] - 회원 가입 인증");
        simpleMailMessage.setText("[ " + accountEntity.getEmailToken() +" ]\n\n [ ]안의 인증코드를 사이트에서 이메일 인증을 해주세요.\n\n 인증완료시 회원가입이 완료됩니다." );
        javaMailSender.send(simpleMailMessage);
    }


    public boolean isEmailValid(Principal principal) {
        if (principal != null) {
            AccountEntity accountEntity = findByLoginId(principal.getName());
            return accountEntity.isEmailVerified();
        }
        return false;
    }

}
