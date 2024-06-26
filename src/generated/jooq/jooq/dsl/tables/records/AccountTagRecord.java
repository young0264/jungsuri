/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables.records;


import jooq.dsl.tables.AccountTag;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountTagRecord extends UpdatableRecordImpl<AccountTagRecord> implements Record3<Long, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>jungsuri.account_tag.account_entity_id</code>.
     */
    public AccountTagRecord setAccountEntityId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account_tag.account_entity_id</code>.
     */
    public Long getAccountEntityId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>jungsuri.account_tag.id</code>.
     */
    public AccountTagRecord setId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account_tag.id</code>.
     */
    public Long getId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>jungsuri.account_tag.account_tag_id</code>.
     */
    public AccountTagRecord setAccountTagId(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.account_tag.account_tag_id</code>.
     */
    public Long getAccountTagId() {
        return (Long) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, Long> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, Long, Long> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return AccountTag.ACCOUNT_TAG.ACCOUNT_ENTITY_ID;
    }

    @Override
    public Field<Long> field2() {
        return AccountTag.ACCOUNT_TAG.ID;
    }

    @Override
    public Field<Long> field3() {
        return AccountTag.ACCOUNT_TAG.ACCOUNT_TAG_ID;
    }

    @Override
    public Long component1() {
        return getAccountEntityId();
    }

    @Override
    public Long component2() {
        return getId();
    }

    @Override
    public Long component3() {
        return getAccountTagId();
    }

    @Override
    public Long value1() {
        return getAccountEntityId();
    }

    @Override
    public Long value2() {
        return getId();
    }

    @Override
    public Long value3() {
        return getAccountTagId();
    }

    @Override
    public AccountTagRecord value1(Long value) {
        setAccountEntityId(value);
        return this;
    }

    @Override
    public AccountTagRecord value2(Long value) {
        setId(value);
        return this;
    }

    @Override
    public AccountTagRecord value3(Long value) {
        setAccountTagId(value);
        return this;
    }

    @Override
    public AccountTagRecord values(Long value1, Long value2, Long value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountTagRecord
     */
    public AccountTagRecord() {
        super(AccountTag.ACCOUNT_TAG);
    }

    /**
     * Create a detached, initialised AccountTagRecord
     */
    public AccountTagRecord(Long accountEntityId, Long id, Long accountTagId) {
        super(AccountTag.ACCOUNT_TAG);

        setAccountEntityId(accountEntityId);
        setId(id);
        setAccountTagId(accountTagId);
    }

    /**
     * Create a detached, initialised AccountTagRecord
     */
    public AccountTagRecord(jooq.dsl.tables.pojos.AccountTag value) {
        super(AccountTag.ACCOUNT_TAG);

        if (value != null) {
            setAccountEntityId(value.getAccountEntityId());
            setId(value.getId());
            setAccountTagId(value.getAccountTagId());
        }
    }
}
