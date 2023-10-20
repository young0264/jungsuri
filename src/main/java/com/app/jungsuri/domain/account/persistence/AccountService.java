package com.app.jungsuri.domain.account.persistence;

import com.app.jungsuri.domain.account.web.form.SignUpForm;
import com.app.jungsuri.domain.aws.AWSService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountService {
    private final AccountRepository accountRepository;
    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    private final AWSService awsService;


    public void login(AccountEntity accountEntity) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                accountEntity.getLoginId(),
                accountEntity.getPassword(),
                List.of(new SimpleGrantedAuthority(accountEntity.getUserRole().toString()))
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

    public AccountEntity findByLoginId(String loginId) {
        return accountRepository.findByLoginId(loginId).orElseThrow(() -> new UsernameNotFoundException("로그인 아이디에 해당하는 유저는 존재하지 않습니다."));
    }

    /**이메일 인증 후*/
    public void completeSignUp(AccountEntity accountEntity) {
        accountEntity.completeSignUp();
        login(accountEntity);
    }

    public boolean isEmailValid(Principal principal) {
        if (principal != null) {
            AccountEntity accountEntity = findByLoginId(principal.getName());
            return accountEntity.isEmailVerified();
        }
        return false;
    }

    public void sendSignUpConfirmEmail(AccountEntity accountEntity) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setReplyTo(accountEntity.getEmail());
            simpleMailMessage.setTo(accountEntity.getEmail());
            simpleMailMessage.setSubject("[정수리 꼬순내] - 회원 가입 인증");
            simpleMailMessage.setText("[ " + accountEntity.getEmailToken() + " ]\n\n [ ]안의 인증코드를 사이트에서 이메일 인증을 해주세요.\n\n 인증완료시 회원가입이 완료됩니다.");
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllUsersLoginId() {
        return accountRepository.findAllUsersLoginId();
    }

    public void updateEmail(String loginId, String email) {
        AccountEntity accountEntity = findByLoginId(loginId);
        accountEntity.updateEmail(email);
    }

    public void updateProfileImg(String name, MultipartFile multipartFile) {
        AccountEntity accountEntity = findByLoginId(name);

        String s3Url = awsService.uploadFile(multipartFile);
        accountEntity.updateProfileImg(s3Url);

    }
}
