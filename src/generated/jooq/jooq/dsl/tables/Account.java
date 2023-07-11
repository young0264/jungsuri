/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import jooq.dsl.Jungsuri;
import jooq.dsl.Keys;
import jooq.dsl.tables.records.AccountRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function14;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row14;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Account extends TableImpl<AccountRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>jungsuri.account</code>
     */
    public static final Account ACCOUNT = new Account();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountRecord> getRecordType() {
        return AccountRecord.class;
    }

    /**
     * The column <code>jungsuri.account.email_verified</code>.
     */
    public final TableField<AccountRecord, Boolean> EMAIL_VERIFIED = createField(DSL.name("email_verified"), SQLDataType.BIT.nullable(false), this, "");

    /**
     * The column <code>jungsuri.account.email_check_token_generated_at</code>.
     */
    public final TableField<AccountRecord, LocalDateTime> EMAIL_CHECK_TOKEN_GENERATED_AT = createField(DSL.name("email_check_token_generated_at"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>jungsuri.account.id</code>.
     */
    public final TableField<AccountRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>jungsuri.account.joined_at</code>.
     */
    public final TableField<AccountRecord, LocalDateTime> JOINED_AT = createField(DSL.name("joined_at"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>jungsuri.account.bio</code>.
     */
    public final TableField<AccountRecord, String> BIO = createField(DSL.name("bio"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.account.email</code>.
     */
    public final TableField<AccountRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.account.email_token</code>.
     */
    public final TableField<AccountRecord, String> EMAIL_TOKEN = createField(DSL.name("email_token"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.account.location</code>.
     */
    public final TableField<AccountRecord, String> LOCATION = createField(DSL.name("location"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.account.login_id</code>.
     */
    public final TableField<AccountRecord, String> LOGIN_ID = createField(DSL.name("login_id"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.account.name</code>.
     */
    public final TableField<AccountRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.account.occupation</code>.
     */
    public final TableField<AccountRecord, String> OCCUPATION = createField(DSL.name("occupation"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.account.password</code>.
     */
    public final TableField<AccountRecord, String> PASSWORD = createField(DSL.name("password"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.account.profile_image</code>.
     */
    public final TableField<AccountRecord, String> PROFILE_IMAGE = createField(DSL.name("profile_image"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.account.role</code>.
     */
    public final TableField<AccountRecord, String> ROLE = createField(DSL.name("role"), SQLDataType.VARCHAR(255), this, "");

    private Account(Name alias, Table<AccountRecord> aliased) {
        this(alias, aliased, null);
    }

    private Account(Name alias, Table<AccountRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>jungsuri.account</code> table reference
     */
    public Account(String alias) {
        this(DSL.name(alias), ACCOUNT);
    }

    /**
     * Create an aliased <code>jungsuri.account</code> table reference
     */
    public Account(Name alias) {
        this(alias, ACCOUNT);
    }

    /**
     * Create a <code>jungsuri.account</code> table reference
     */
    public Account() {
        this(DSL.name("account"), null);
    }

    public <O extends Record> Account(Table<O> child, ForeignKey<O, AccountRecord> key) {
        super(child, key, ACCOUNT);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Jungsuri.JUNGSURI;
    }

    @Override
    public Identity<AccountRecord, Long> getIdentity() {
        return (Identity<AccountRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<AccountRecord> getPrimaryKey() {
        return Keys.KEY_ACCOUNT_PRIMARY;
    }

    @Override
    public List<UniqueKey<AccountRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_ACCOUNT_UK_Q0UJA26QGU1ATULENWUP9RXYR, Keys.KEY_ACCOUNT_UK_TQPUAQXALCAEDDPC0M6MCXOBG);
    }

    @Override
    public Account as(String alias) {
        return new Account(DSL.name(alias), this);
    }

    @Override
    public Account as(Name alias) {
        return new Account(alias, this);
    }

    @Override
    public Account as(Table<?> alias) {
        return new Account(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(String name) {
        return new Account(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(Name name) {
        return new Account(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(Table<?> name) {
        return new Account(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row14 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row14<Boolean, LocalDateTime, Long, LocalDateTime, String, String, String, String, String, String, String, String, String, String> fieldsRow() {
        return (Row14) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function14<? super Boolean, ? super LocalDateTime, ? super Long, ? super LocalDateTime, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function14<? super Boolean, ? super LocalDateTime, ? super Long, ? super LocalDateTime, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
