package com.app.jungsuri.domain.account.persistence;

import com.app.jungsuri.domain.account.web.dto.MountainExpUpdateDto;
import com.app.jungsuri.domain.account.web.dto.PasswordUpdateDto;
import com.app.jungsuri.domain.mountain.persistence.MountainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SettingsService {

    private final PasswordEncoder passwordEncoder;
    private final AccountRepository accountRepository;
    private final MountainRepository mountainRepository;

    public void updatePassword(PasswordUpdateDto passwordUpdateDto) {
        AccountEntity accountEntity = accountRepository.findByLoginId(passwordUpdateDto.getLoginId()).orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));
        isMatchPassword(passwordUpdateDto, accountEntity);
        accountEntity.updatePassword(passwordEncoder.encode(passwordUpdateDto.getNewPassword()));
    }

    private void isMatchPassword(PasswordUpdateDto passwordUpdateDto, AccountEntity accountEntity) {
        isMatchCurrentPassword(passwordUpdateDto, accountEntity);
        isMatchNewPassword(passwordUpdateDto);
    }

    private void isMatchCurrentPassword(PasswordUpdateDto passwordUpdateDto, AccountEntity accountEntity) {
        if (!passwordEncoder.matches(passwordUpdateDto.getCurrentPassword(), accountEntity.getPassword())) {
            log.info("isMatchNewPassword :현재 비밀번호가 일치하지 않습니다. ");
            throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
        }
    }

    private void isMatchNewPassword(PasswordUpdateDto passwordUpdateDto) {
        if (!passwordUpdateDto.getNewPassword().equals(passwordUpdateDto.getNewPasswordConfirm())) {
            log.info("isMatchNewPassword :새로운 비밀번호가 일치하지 않습니다. ");
            throw new IllegalArgumentException("새로운 비밀번호가 서로 일치하지 않습니다.");
        }
    }

    public void updateMountainExp(MountainExpUpdateDto mountainExpUpdateDto) {
        String mountainName = mountainExpUpdateDto.getMountainName();

        for (String loginId : mountainExpUpdateDto.getLoginIdArr()) {
            AccountEntity accountEntity = accountRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));
            int mountainHeight = mountainRepository.findMountainHeightByName(mountainName);
            accountEntity.updateMountainExp(mountainHeight);
        }
    }
}
