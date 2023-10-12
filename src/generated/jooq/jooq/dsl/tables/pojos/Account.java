/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String bio;
    private final String email;
    private final LocalDateTime emailCheckTokenGeneratedAt;
    private final String emailToken;
    private final Boolean emailVerified;
    private final LocalDateTime joinedAt;
    private final String location;
    private final String loginId;
    private final Integer mountainExp;
    private final String name;
    private final String occupation;
    private final String password;
    private final Boolean postCreatedChecked;
    private final String profileImage;
    private final String role;
    private final Byte userRole;

    public Account(Account value) {
        this.id = value.id;
        this.bio = value.bio;
        this.email = value.email;
        this.emailCheckTokenGeneratedAt = value.emailCheckTokenGeneratedAt;
        this.emailToken = value.emailToken;
        this.emailVerified = value.emailVerified;
        this.joinedAt = value.joinedAt;
        this.location = value.location;
        this.loginId = value.loginId;
        this.mountainExp = value.mountainExp;
        this.name = value.name;
        this.occupation = value.occupation;
        this.password = value.password;
        this.postCreatedChecked = value.postCreatedChecked;
        this.profileImage = value.profileImage;
        this.role = value.role;
        this.userRole = value.userRole;
    }

    public Account(
        Long id,
        String bio,
        String email,
        LocalDateTime emailCheckTokenGeneratedAt,
        String emailToken,
        Boolean emailVerified,
        LocalDateTime joinedAt,
        String location,
        String loginId,
        Integer mountainExp,
        String name,
        String occupation,
        String password,
        Boolean postCreatedChecked,
        String profileImage,
        String role,
        Byte userRole
    ) {
        this.id = id;
        this.bio = bio;
        this.email = email;
        this.emailCheckTokenGeneratedAt = emailCheckTokenGeneratedAt;
        this.emailToken = emailToken;
        this.emailVerified = emailVerified;
        this.joinedAt = joinedAt;
        this.location = location;
        this.loginId = loginId;
        this.mountainExp = mountainExp;
        this.name = name;
        this.occupation = occupation;
        this.password = password;
        this.postCreatedChecked = postCreatedChecked;
        this.profileImage = profileImage;
        this.role = role;
        this.userRole = userRole;
    }

    /**
     * Getter for <code>jungsuri.account.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>jungsuri.account.bio</code>.
     */
    public String getBio() {
        return this.bio;
    }

    /**
     * Getter for <code>jungsuri.account.email</code>.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Getter for <code>jungsuri.account.email_check_token_generated_at</code>.
     */
    public LocalDateTime getEmailCheckTokenGeneratedAt() {
        return this.emailCheckTokenGeneratedAt;
    }

    /**
     * Getter for <code>jungsuri.account.email_token</code>.
     */
    public String getEmailToken() {
        return this.emailToken;
    }

    /**
     * Getter for <code>jungsuri.account.email_verified</code>.
     */
    public Boolean getEmailVerified() {
        return this.emailVerified;
    }

    /**
     * Getter for <code>jungsuri.account.joined_at</code>.
     */
    public LocalDateTime getJoinedAt() {
        return this.joinedAt;
    }

    /**
     * Getter for <code>jungsuri.account.location</code>.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Getter for <code>jungsuri.account.login_id</code>.
     */
    public String getLoginId() {
        return this.loginId;
    }

    /**
     * Getter for <code>jungsuri.account.mountain_exp</code>.
     */
    public Integer getMountainExp() {
        return this.mountainExp;
    }

    /**
     * Getter for <code>jungsuri.account.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for <code>jungsuri.account.occupation</code>.
     */
    public String getOccupation() {
        return this.occupation;
    }

    /**
     * Getter for <code>jungsuri.account.password</code>.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter for <code>jungsuri.account.post_created_checked</code>.
     */
    public Boolean getPostCreatedChecked() {
        return this.postCreatedChecked;
    }

    /**
     * Getter for <code>jungsuri.account.profile_image</code>.
     */
    public String getProfileImage() {
        return this.profileImage;
    }

    /**
     * Getter for <code>jungsuri.account.role</code>.
     */
    public String getRole() {
        return this.role;
    }

    /**
     * Getter for <code>jungsuri.account.user_role</code>.
     */
    public Byte getUserRole() {
        return this.userRole;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Account (");

        sb.append(id);
        sb.append(", ").append(bio);
        sb.append(", ").append(email);
        sb.append(", ").append(emailCheckTokenGeneratedAt);
        sb.append(", ").append(emailToken);
        sb.append(", ").append(emailVerified);
        sb.append(", ").append(joinedAt);
        sb.append(", ").append(location);
        sb.append(", ").append(loginId);
        sb.append(", ").append(mountainExp);
        sb.append(", ").append(name);
        sb.append(", ").append(occupation);
        sb.append(", ").append(password);
        sb.append(", ").append(postCreatedChecked);
        sb.append(", ").append(profileImage);
        sb.append(", ").append(role);
        sb.append(", ").append(userRole);

        sb.append(")");
        return sb.toString();
    }
}
