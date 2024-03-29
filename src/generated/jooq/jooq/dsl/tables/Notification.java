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
import jooq.dsl.enums.NotificationType;
import jooq.dsl.tables.records.NotificationRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function8;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row8;
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
public class Notification extends TableImpl<NotificationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>jungsuri.notification</code>
     */
    public static final Notification NOTIFICATION = new Notification();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<NotificationRecord> getRecordType() {
        return NotificationRecord.class;
    }

    /**
     * The column <code>jungsuri.notification.checked</code>.
     */
    public final TableField<NotificationRecord, Boolean> CHECKED = createField(DSL.name("checked"), SQLDataType.BIT.nullable(false), this, "");

    /**
     * The column <code>jungsuri.notification.account_entity_id</code>.
     */
    public final TableField<NotificationRecord, Long> ACCOUNT_ENTITY_ID = createField(DSL.name("account_entity_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>jungsuri.notification.created_at</code>.
     */
    public final TableField<NotificationRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>jungsuri.notification.id</code>.
     */
    public final TableField<NotificationRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>jungsuri.notification.link</code>.
     */
    public final TableField<NotificationRecord, String> LINK = createField(DSL.name("link"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.notification.message</code>.
     */
    public final TableField<NotificationRecord, String> MESSAGE = createField(DSL.name("message"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>jungsuri.notification.title</code>.
     */
    public final TableField<NotificationRecord, String> TITLE = createField(DSL.name("title"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>jungsuri.notification.type</code>.
     */
    public final TableField<NotificationRecord, NotificationType> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(17).asEnumDataType(jooq.dsl.enums.NotificationType.class), this, "");

    private Notification(Name alias, Table<NotificationRecord> aliased) {
        this(alias, aliased, null);
    }

    private Notification(Name alias, Table<NotificationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>jungsuri.notification</code> table reference
     */
    public Notification(String alias) {
        this(DSL.name(alias), NOTIFICATION);
    }

    /**
     * Create an aliased <code>jungsuri.notification</code> table reference
     */
    public Notification(Name alias) {
        this(alias, NOTIFICATION);
    }

    /**
     * Create a <code>jungsuri.notification</code> table reference
     */
    public Notification() {
        this(DSL.name("notification"), null);
    }

    public <O extends Record> Notification(Table<O> child, ForeignKey<O, NotificationRecord> key) {
        super(child, key, NOTIFICATION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Jungsuri.JUNGSURI;
    }

    @Override
    public Identity<NotificationRecord, Long> getIdentity() {
        return (Identity<NotificationRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<NotificationRecord> getPrimaryKey() {
        return Keys.KEY_NOTIFICATION_PRIMARY;
    }

    @Override
    public List<ForeignKey<NotificationRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FKN2Q3MSN4YFV43J6M6690CVK75);
    }

    private transient Account _account;

    /**
     * Get the implicit join path to the <code>jungsuri.account</code> table.
     */
    public Account account() {
        if (_account == null)
            _account = new Account(this, Keys.FKN2Q3MSN4YFV43J6M6690CVK75);

        return _account;
    }

    @Override
    public Notification as(String alias) {
        return new Notification(DSL.name(alias), this);
    }

    @Override
    public Notification as(Name alias) {
        return new Notification(alias, this);
    }

    @Override
    public Notification as(Table<?> alias) {
        return new Notification(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Notification rename(String name) {
        return new Notification(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Notification rename(Name name) {
        return new Notification(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Notification rename(Table<?> name) {
        return new Notification(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<Boolean, Long, LocalDateTime, Long, String, String, String, NotificationType> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function8<? super Boolean, ? super Long, ? super LocalDateTime, ? super Long, ? super String, ? super String, ? super String, ? super NotificationType, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function8<? super Boolean, ? super Long, ? super LocalDateTime, ? super Long, ? super String, ? super String, ? super String, ? super NotificationType, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
