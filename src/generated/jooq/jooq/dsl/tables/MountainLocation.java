/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables;


import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import jooq.dsl.Jungsuri;
import jooq.dsl.Keys;
import jooq.dsl.tables.records.MountainLocationRecord;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
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
public class MountainLocation extends TableImpl<MountainLocationRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>jungsuri.mountain_location</code>
     */
    public static final MountainLocation MOUNTAIN_LOCATION = new MountainLocation();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MountainLocationRecord> getRecordType() {
        return MountainLocationRecord.class;
    }

    /**
     * The column <code>jungsuri.mountain_location.id</code>.
     */
    public final TableField<MountainLocationRecord, Long> ID = createField(DSL.name("id"), SQLDataType.BIGINT.nullable(false).identity(true), this, "");

    /**
     * The column <code>jungsuri.mountain_location.mountain_entity_id</code>.
     */
    public final TableField<MountainLocationRecord, Long> MOUNTAIN_ENTITY_ID = createField(DSL.name("mountain_entity_id"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>jungsuri.mountain_location.location</code>.
     */
    public final TableField<MountainLocationRecord, String> LOCATION = createField(DSL.name("location"), SQLDataType.VARCHAR(255), this, "");

    private MountainLocation(Name alias, Table<MountainLocationRecord> aliased) {
        this(alias, aliased, null);
    }

    private MountainLocation(Name alias, Table<MountainLocationRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>jungsuri.mountain_location</code> table reference
     */
    public MountainLocation(String alias) {
        this(DSL.name(alias), MOUNTAIN_LOCATION);
    }

    /**
     * Create an aliased <code>jungsuri.mountain_location</code> table reference
     */
    public MountainLocation(Name alias) {
        this(alias, MOUNTAIN_LOCATION);
    }

    /**
     * Create a <code>jungsuri.mountain_location</code> table reference
     */
    public MountainLocation() {
        this(DSL.name("mountain_location"), null);
    }

    public <O extends Record> MountainLocation(Table<O> child, ForeignKey<O, MountainLocationRecord> key) {
        super(child, key, MOUNTAIN_LOCATION);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : Jungsuri.JUNGSURI;
    }

    @Override
    public Identity<MountainLocationRecord, Long> getIdentity() {
        return (Identity<MountainLocationRecord, Long>) super.getIdentity();
    }

    @Override
    public UniqueKey<MountainLocationRecord> getPrimaryKey() {
        return Keys.KEY_MOUNTAIN_LOCATION_PRIMARY;
    }

    @Override
    public List<ForeignKey<MountainLocationRecord, ?>> getReferences() {
        return Arrays.asList(Keys.FKAOY0VAEYLS649L5HB02FKI4K6);
    }

    private transient Mountain _mountain;

    /**
     * Get the implicit join path to the <code>jungsuri.mountain</code> table.
     */
    public Mountain mountain() {
        if (_mountain == null)
            _mountain = new Mountain(this, Keys.FKAOY0VAEYLS649L5HB02FKI4K6);

        return _mountain;
    }

    @Override
    public MountainLocation as(String alias) {
        return new MountainLocation(DSL.name(alias), this);
    }

    @Override
    public MountainLocation as(Name alias) {
        return new MountainLocation(alias, this);
    }

    @Override
    public MountainLocation as(Table<?> alias) {
        return new MountainLocation(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public MountainLocation rename(String name) {
        return new MountainLocation(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public MountainLocation rename(Name name) {
        return new MountainLocation(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public MountainLocation rename(Table<?> name) {
        return new MountainLocation(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, Long, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super Long, ? super Long, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super Long, ? super Long, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
