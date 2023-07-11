/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables.records;


import java.time.LocalDateTime;

import jooq.dsl.tables.Account;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record14;
import org.jooq.Row14;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountRecord extends UpdatableRecordImpl<AccountRecord> implements Record14<Boolean, LocalDateTime, Long, LocalDateTime, String, String, String, String, String, String, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>jungsuri.account.email_verified</code>.
     */
    public AccountRecord setEmailVerified(Boolean value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.email_verified</code>.
     */
    public Boolean getEmailVerified() {
        return (Boolean) get(0);
    }

    /**
     * Setter for <code>jungsuri.account.email_check_token_generated_at</code>.
     */
    public AccountRecord setEmailCheckTokenGeneratedAt(LocalDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.email_check_token_generated_at</code>.
     */
    public LocalDateTime getEmailCheckTokenGeneratedAt() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>jungsuri.account.id</code>.
     */
    public AccountRecord setId(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.id</code>.
     */
    public Long getId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>jungsuri.account.joined_at</code>.
     */
    public AccountRecord setJoinedAt(LocalDateTime value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.joined_at</code>.
     */
    public LocalDateTime getJoinedAt() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>jungsuri.account.bio</code>.
     */
    public AccountRecord setBio(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.bio</code>.
     */
    public String getBio() {
        return (String) get(4);
    }

    /**
     * Setter for <code>jungsuri.account.email</code>.
     */
    public AccountRecord setEmail(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.email</code>.
     */
    public String getEmail() {
        return (String) get(5);
    }

    /**
     * Setter for <code>jungsuri.account.email_token</code>.
     */
    public AccountRecord setEmailToken(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.email_token</code>.
     */
    public String getEmailToken() {
        return (String) get(6);
    }

    /**
     * Setter for <code>jungsuri.account.location</code>.
     */
    public AccountRecord setLocation(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.location</code>.
     */
    public String getLocation() {
        return (String) get(7);
    }

    /**
     * Setter for <code>jungsuri.account.login_id</code>.
     */
    public AccountRecord setLoginId(String value) {
        set(8, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.login_id</code>.
     */
    public String getLoginId() {
        return (String) get(8);
    }

    /**
     * Setter for <code>jungsuri.account.name</code>.
     */
    public AccountRecord setName(String value) {
        set(9, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.name</code>.
     */
    public String getName() {
        return (String) get(9);
    }

    /**
     * Setter for <code>jungsuri.account.occupation</code>.
     */
    public AccountRecord setOccupation(String value) {
        set(10, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.occupation</code>.
     */
    public String getOccupation() {
        return (String) get(10);
    }

    /**
     * Setter for <code>jungsuri.account.password</code>.
     */
    public AccountRecord setPassword(String value) {
        set(11, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.password</code>.
     */
    public String getPassword() {
        return (String) get(11);
    }

    /**
     * Setter for <code>jungsuri.account.profile_image</code>.
     */
    public AccountRecord setProfileImage(String value) {
        set(12, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.profile_image</code>.
     */
    public String getProfileImage() {
        return (String) get(12);
    }

    /**
     * Setter for <code>jungsuri.account.role</code>.
     */
    public AccountRecord setRole(String value) {
        set(13, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account.role</code>.
     */
    public String getRole() {
        return (String) get(13);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record14 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row14<Boolean, LocalDateTime, Long, LocalDateTime, String, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    @Override
    public Row14<Boolean, LocalDateTime, Long, LocalDateTime, String, String, String, String, String, String, String, String, String, String> valuesRow() {
        return (Row14) super.valuesRow();
    }

    @Override
    public Field<Boolean> field1() {
        return Account.ACCOUNT.EMAIL_VERIFIED;
    }

    @Override
    public Field<LocalDateTime> field2() {
        return Account.ACCOUNT.EMAIL_CHECK_TOKEN_GENERATED_AT;
    }

    @Override
    public Field<Long> field3() {
        return Account.ACCOUNT.ID;
    }

    @Override
    public Field<LocalDateTime> field4() {
        return Account.ACCOUNT.JOINED_AT;
    }

    @Override
    public Field<String> field5() {
        return Account.ACCOUNT.BIO;
    }

    @Override
    public Field<String> field6() {
        return Account.ACCOUNT.EMAIL;
    }

    @Override
    public Field<String> field7() {
        return Account.ACCOUNT.EMAIL_TOKEN;
    }

    @Override
    public Field<String> field8() {
        return Account.ACCOUNT.LOCATION;
    }

    @Override
    public Field<String> field9() {
        return Account.ACCOUNT.LOGIN_ID;
    }

    @Override
    public Field<String> field10() {
        return Account.ACCOUNT.NAME;
    }

    @Override
    public Field<String> field11() {
        return Account.ACCOUNT.OCCUPATION;
    }

    @Override
    public Field<String> field12() {
        return Account.ACCOUNT.PASSWORD;
    }

    @Override
    public Field<String> field13() {
        return Account.ACCOUNT.PROFILE_IMAGE;
    }

    @Override
    public Field<String> field14() {
        return Account.ACCOUNT.ROLE;
    }

    @Override
    public Boolean component1() {
        return getEmailVerified();
    }

    @Override
    public LocalDateTime component2() {
        return getEmailCheckTokenGeneratedAt();
    }

    @Override
    public Long component3() {
        return getId();
    }

    @Override
    public LocalDateTime component4() {
        return getJoinedAt();
    }

    @Override
    public String component5() {
        return getBio();
    }

    @Override
    public String component6() {
        return getEmail();
    }

    @Override
    public String component7() {
        return getEmailToken();
    }

    @Override
    public String component8() {
        return getLocation();
    }

    @Override
    public String component9() {
        return getLoginId();
    }

    @Override
    public String component10() {
        return getName();
    }

    @Override
    public String component11() {
        return getOccupation();
    }

    @Override
    public String component12() {
        return getPassword();
    }

    @Override
    public String component13() {
        return getProfileImage();
    }

    @Override
    public String component14() {
        return getRole();
    }

    @Override
    public Boolean value1() {
        return getEmailVerified();
    }

    @Override
    public LocalDateTime value2() {
        return getEmailCheckTokenGeneratedAt();
    }

    @Override
    public Long value3() {
        return getId();
    }

    @Override
    public LocalDateTime value4() {
        return getJoinedAt();
    }

    @Override
    public String value5() {
        return getBio();
    }

    @Override
    public String value6() {
        return getEmail();
    }

    @Override
    public String value7() {
        return getEmailToken();
    }

    @Override
    public String value8() {
        return getLocation();
    }

    @Override
    public String value9() {
        return getLoginId();
    }

    @Override
    public String value10() {
        return getName();
    }

    @Override
    public String value11() {
        return getOccupation();
    }

    @Override
    public String value12() {
        return getPassword();
    }

    @Override
    public String value13() {
        return getProfileImage();
    }

    @Override
    public String value14() {
        return getRole();
    }

    @Override
    public AccountRecord value1(Boolean value) {
        setEmailVerified(value);
        return this;
    }

    @Override
    public AccountRecord value2(LocalDateTime value) {
        setEmailCheckTokenGeneratedAt(value);
        return this;
    }

    @Override
    public AccountRecord value3(Long value) {
        setId(value);
        return this;
    }

    @Override
    public AccountRecord value4(LocalDateTime value) {
        setJoinedAt(value);
        return this;
    }

    @Override
    public AccountRecord value5(String value) {
        setBio(value);
        return this;
    }

    @Override
    public AccountRecord value6(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public AccountRecord value7(String value) {
        setEmailToken(value);
        return this;
    }

    @Override
    public AccountRecord value8(String value) {
        setLocation(value);
        return this;
    }

    @Override
    public AccountRecord value9(String value) {
        setLoginId(value);
        return this;
    }

    @Override
    public AccountRecord value10(String value) {
        setName(value);
        return this;
    }

    @Override
    public AccountRecord value11(String value) {
        setOccupation(value);
        return this;
    }

    @Override
    public AccountRecord value12(String value) {
        setPassword(value);
        return this;
    }

    @Override
    public AccountRecord value13(String value) {
        setProfileImage(value);
        return this;
    }

    @Override
    public AccountRecord value14(String value) {
        setRole(value);
        return this;
    }

    @Override
    public AccountRecord values(Boolean value1, LocalDateTime value2, Long value3, LocalDateTime value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12, String value13, String value14) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountRecord
     */
    public AccountRecord() {
        super(Account.ACCOUNT);
    }

    /**
     * Create a detached, initialised AccountRecord
     */
    public AccountRecord(Boolean emailVerified, LocalDateTime emailCheckTokenGeneratedAt, Long id, LocalDateTime joinedAt, String bio, String email, String emailToken, String location, String loginId, String name, String occupation, String password, String profileImage, String role) {
        super(Account.ACCOUNT);

        setEmailVerified(emailVerified);
        setEmailCheckTokenGeneratedAt(emailCheckTokenGeneratedAt);
        setId(id);
        setJoinedAt(joinedAt);
        setBio(bio);
        setEmail(email);
        setEmailToken(emailToken);
        setLocation(location);
        setLoginId(loginId);
        setName(name);
        setOccupation(occupation);
        setPassword(password);
        setProfileImage(profileImage);
        setRole(role);
    }

    /**
     * Create a detached, initialised AccountRecord
     */
    public AccountRecord(jooq.dsl.tables.pojos.Account value) {
        super(Account.ACCOUNT);

        if (value != null) {
            setEmailVerified(value.getEmailVerified());
            setEmailCheckTokenGeneratedAt(value.getEmailCheckTokenGeneratedAt());
            setId(value.getId());
            setJoinedAt(value.getJoinedAt());
            setBio(value.getBio());
            setEmail(value.getEmail());
            setEmailToken(value.getEmailToken());
            setLocation(value.getLocation());
            setLoginId(value.getLoginId());
            setName(value.getName());
            setOccupation(value.getOccupation());
            setPassword(value.getPassword());
            setProfileImage(value.getProfileImage());
            setRole(value.getRole());
        }
    }
}
