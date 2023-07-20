/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import jooq.dsl.Jungsuri;
import jooq.dsl.Keys;
import jooq.dsl.tables.records.MountainEntityRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
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
public class MountainEntity extends TableImpl<MountainEntityRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>jungsuri.mountain_entity</code>
     */
    public static final MountainEntity MOUNTAIN_ENTITY = new MountainEntity();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MountainEntityRecord> getRecordType() {
        return MountainEntityRecord.class;
    }

    /**
     * The column <code>jungsuri.mountain_entity.height</code>.
     */
    public final TableField<MountainEntityRecord, Integer> HEIGHT = createField(DSL.name("height"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>jungsuri.mountain_entity.id</code>.
     */
    public final TableField<MountainEntityRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>jungsuri.mountain_entity.image_url</code>.
     */
    public final TableField<MountainEntityRecord, String> IMAGE_URL = createField(DSL.name("image_url"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.mountain_entity.name</code>.
     */
    public final TableField<MountainEntityRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    private MountainEntity(Name alias, Table<MountainEntityRecord> aliased) {
        this(alias, aliased, null);
    }

    private MountainEntity(Name alias, Table<MountainEntityRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>jungsuri.mountain_entity</code> table reference
     */
    public MountainEntity(String alias) {
        this(DSL.name(alias), MOUNTAIN_ENTITY);
    }

    /**
     * Create an aliased <code>jungsuri.mountain_entity</code> table reference
     */
    public MountainEntity(Name alias) {
        this(alias, MOUNTAIN_ENTITY);
    }

    /**
     * Create a <code>jungsuri.mountain_entity</code> table reference
     */
    public MountainEntity() {
        this(DSL.name("mountain_entity"), null);
    }

    public <O extends Record> MountainEntity(Table<O> child, ForeignKey<O, MountainEntityRecord> key) {
        super(child, key, MOUNTAIN_ENTITY);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Jungsuri.JUNGSURI;
    }

    @Override
    public Identity<MountainEntityRecord, Long> getIdentity() {
        return (Identity<MountainEntityRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<MountainEntityRecord> getPrimaryKey() {
        return Keys.KEY_MOUNTAIN_ENTITY_PRIMARY;
    }

    @Override
    public List<UniqueKey<MountainEntityRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_MOUNTAIN_ENTITY_UK_6239SLP58J6LFRYEN0WS1C69S);
    }

    @Override
    public MountainEntity as(String alias) {
        return new MountainEntity(DSL.name(alias), this);
    }

    @Override
    public MountainEntity as(Name alias) {
        return new MountainEntity(alias, this);
    }

    @Override
    public MountainEntity as(Table<?> alias) {
        return new MountainEntity(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public MountainEntity rename(String name) {
        return new MountainEntity(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MountainEntity rename(Name name) {
        return new MountainEntity(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public MountainEntity rename(Table<?> name) {
        return new MountainEntity(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Long, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super Integer, ? super Long, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super Integer, ? super Long, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}