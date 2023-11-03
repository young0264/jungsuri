/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import jooq.dsl.Indexes;
import jooq.dsl.Jungsuri;
import jooq.dsl.Keys;
import jooq.dsl.tables.records.MountainRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function5;
import org.jooq.Index;
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
public class Mountain extends TableImpl<MountainRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>jungsuri.mountain</code>
     */
    public static final Mountain MOUNTAIN = new Mountain();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MountainRecord> getRecordType() {
        return MountainRecord.class;
    }

    /**
     * The column <code>jungsuri.mountain.height</code>.
     */
    public final TableField<MountainRecord, Integer> HEIGHT = createField(DSL.name("height"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>jungsuri.mountain.id</code>.
     */
    public final TableField<MountainRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>jungsuri.mountain.image_url</code>.
     */
    public final TableField<MountainRecord, String> IMAGE_URL = createField(DSL.name("image_url"), SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>jungsuri.mountain.name</code>.
     */
    public final TableField<MountainRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>jungsuri.mountain.short_locations</code>.
     */
    public final TableField<MountainRecord, String> SHORT_LOCATIONS = createField(DSL.name("short_locations"), SQLDataType.VARCHAR(255), this, "");

    private Mountain(Name alias, Table<MountainRecord> aliased) {
        this(alias, aliased, null);
    }

    private Mountain(Name alias, Table<MountainRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>jungsuri.mountain</code> table reference
     */
    public Mountain(String alias) {
        this(DSL.name(alias), MOUNTAIN);
    }

    /**
     * Create an aliased <code>jungsuri.mountain</code> table reference
     */
    public Mountain(Name alias) {
        this(alias, MOUNTAIN);
    }

    /**
     * Create a <code>jungsuri.mountain</code> table reference
     */
    public Mountain() {
        this(DSL.name("mountain"), null);
    }

    public <O extends Record> Mountain(Table<O> child, ForeignKey<O, MountainRecord> key) {
        super(child, key, MOUNTAIN);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Jungsuri.JUNGSURI;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.MOUNTAIN_IDX_HEIGHT);
    }

    @Override
    public UniqueKey<MountainRecord> getPrimaryKey() {
        return Keys.KEY_MOUNTAIN_PRIMARY;
    }

    @Override
    public List<UniqueKey<MountainRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_MOUNTAIN_UK_H5IFMKSO2WLXN8L8JFESJKVN5);
    }

    @Override
    public Mountain as(String alias) {
        return new Mountain(DSL.name(alias), this);
    }

    @Override
    public Mountain as(Name alias) {
        return new Mountain(alias, this);
    }

    @Override
    public Mountain as(Table<?> alias) {
        return new Mountain(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public Mountain rename(String name) {
        return new Mountain(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Mountain rename(Name name) {
        return new Mountain(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public Mountain rename(Table<?> name) {
        return new Mountain(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Long, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function5<? super Integer, ? super Long, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function5<? super Integer, ? super Long, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
