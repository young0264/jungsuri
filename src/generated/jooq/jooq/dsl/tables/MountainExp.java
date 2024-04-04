/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import jooq.dsl.Jungsuri;
import jooq.dsl.Keys;
import jooq.dsl.tables.records.MountainExpRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row5;
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
public class MountainExp extends TableImpl<MountainExpRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>jungsuri.mountain_exp</code>
     */
    public static final MountainExp MOUNTAIN_EXP = new MountainExp();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MountainExpRecord> getRecordType() {
        return MountainExpRecord.class;
    }

    /**
     * The column <code>jungsuri.mountain_exp.id</code>.
     */
    public final TableField<MountainExpRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>jungsuri.mountain_exp.hiking_date</code>.
     */
    public final TableField<MountainExpRecord, String> HIKING_DATE = createField(DSL.name("hiking_date"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.mountain_exp.register_id</code>.
     */
    public final TableField<MountainExpRecord, Long> REGISTER_ID = createField(DSL.name("register_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>jungsuri.mountain_exp.account_entity_id</code>.
     */
    public final TableField<MountainExpRecord, Long> ACCOUNT_ENTITY_ID = createField(DSL.name("account_entity_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>jungsuri.mountain_exp.mountain_entity_id</code>.
     */
    public final TableField<MountainExpRecord, Long> MOUNTAIN_ENTITY_ID = createField(DSL.name("mountain_entity_id"), SQLDataType.BIGINT, this, "");

    private MountainExp(Name alias, Table<MountainExpRecord> aliased) {
        this(alias, aliased, null);
    }

    private MountainExp(Name alias, Table<MountainExpRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>jungsuri.mountain_exp</code> table reference
     */
    public MountainExp(String alias) {
        this(DSL.name(alias), MOUNTAIN_EXP);
    }

    /**
     * Create an aliased <code>jungsuri.mountain_exp</code> table reference
     */
    public MountainExp(Name alias) {
        this(alias, MOUNTAIN_EXP);
    }

    /**
     * Create a <code>jungsuri.mountain_exp</code> table reference
     */
    public MountainExp() {
        this(DSL.name("mountain_exp"), null);
    }

    public <O extends Record> MountainExp(Table<O> child, ForeignKey<O, MountainExpRecord> key) {
        super(child, key, MOUNTAIN_EXP);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Jungsuri.JUNGSURI;
    }

    @Override
    public Identity<MountainExpRecord, Long> getIdentity() {
        return (Identity<MountainExpRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<MountainExpRecord> getPrimaryKey() {
        return Keys.KEY_MOUNTAIN_EXP_PRIMARY;
    }

    @Override
    public List<ForeignKey<MountainExpRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FK18VHIOD912YSPTJM04RFOP67G, Keys.FKIEDKMJ00BXRXV9LBY357H71EE);
    }

    private transient Account _account;
    private transient Mountain _mountain;

    /**
     * Get the implicit join path to the <code>jungsuri.account</code> table.
     */
    public Account account() {
        if (_account == null)
            _account = new Account(this, Keys.FK18VHIOD912YSPTJM04RFOP67G);

        return _account;
    }

    /**
     * Get the implicit join path to the <code>jungsuri.mountain</code> table.
     */
    public Mountain mountain() {
        if (_mountain == null)
            _mountain = new Mountain(this, Keys.FKIEDKMJ00BXRXV9LBY357H71EE);

        return _mountain;
    }

    @Override
    public MountainExp as(String alias) {
        return new MountainExp(DSL.name(alias), this);
    }

    @Override
    public MountainExp as(Name alias) {
        return new MountainExp(alias, this);
    }

    @Override
    public MountainExp as(Table<?> alias) {
        return new MountainExp(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public MountainExp rename(String name) {
        return new MountainExp(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MountainExp rename(Name name) {
        return new MountainExp(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public MountainExp rename(Table<?> name) {
        return new MountainExp(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, String, Long, Long, Long> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Long, ? super String, ? super Long, ? super Long, ? super Long, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super Long, ? super String, ? super Long, ? super Long, ? super Long, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
