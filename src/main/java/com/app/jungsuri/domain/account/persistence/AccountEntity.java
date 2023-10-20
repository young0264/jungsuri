package com.app.jungsuri.domain.account.persistence;

import com.app.jungsuri.domain.account.web.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
@EqualsAndHashCode(of = "id")
public class AccountEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;

    @Column(name = "name", nullable = true)
    private String name;

    private String role; //TODO ENUM으로 -> 관리자가 부여해주는 방식

    @Column(unique = true)
//    @NotBlank
    private String email;

    private String emailToken;

    private LocalDateTime emailCheckTokenGeneratedAt;

    private boolean emailVerified;

    private String location; //TODO 대략적인 시 or 구까지.

    private String occupation;//

    @Column(unique = true)
    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    private String profileImage;

    private int mountainExp; //등산한 산 경험치 (높이)

    private UserRole userRole;

    private LocalDateTime joinedAt;

    @Builder.Default
    private boolean postCreatedChecked = false;

    @Transient
    private Collection<SimpleGrantedAuthority> authorities;

    public boolean isValidToken(String token) {
        return this.emailToken.equals(token);
    }

    public void completeSignUp() {
        this.emailVerified = true;
        joinedAt = LocalDateTime.now();
    }

    public void generateEmailCheckToken() {
        this.emailVerified = false;
        this.emailToken = UUID.randomUUID().toString();
        this.emailCheckTokenGeneratedAt = LocalDateTime.now();
    }

    public boolean canSendConfirmEmail() {
        return this.emailCheckTokenGeneratedAt.isBefore(LocalDateTime.now().minusHours(1)); //1시간 이전에 만들었는지
    }

    public void updateEmail(String email) {
        this.email = email;
        generateEmailCheckToken();
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void updateMountainExp(int mountainHeight) {
        this.mountainExp += mountainHeight;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", bio='" + bio + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                ", emailToken='" + emailToken + '\'' +
                ", emailCheckTokenGeneratedAt=" + emailCheckTokenGeneratedAt +
                ", emailVerified=" + emailVerified +
                ", location='" + location + '\'' +
                ", occupation='" + occupation + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", mountainExp=" + mountainExp +
                ", userRole=" + userRole +
                ", joinedAt=" + joinedAt +
                ", postCreatedChecked=" + postCreatedChecked +
                ", authorities=" + authorities +
                '}';
    }
}
