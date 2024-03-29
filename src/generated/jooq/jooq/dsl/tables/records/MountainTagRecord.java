/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables.records;


import jooq.dsl.tables.MountainTag;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MountainTagRecord extends UpdatableRecordImpl<MountainTagRecord> implements Record2<Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>jungsuri.mountain_tag.mountain_entity_id</code>.
     */
    public MountainTagRecord setMountainEntityId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.mountain_tag.mountain_entity_id</code>.
     */
    public Long getMountainEntityId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>jungsuri.mountain_tag.mountain_tag_id</code>.
     */
    public MountainTagRecord setMountainTagId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.mountain_tag.mountain_tag_id</code>.
     */
    public Long getMountainTagId() {
        return (Long) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Long, Long> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Long, Long> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return MountainTag.MOUNTAIN_TAG.MOUNTAIN_ENTITY_ID;
    }

    @Override
    public Field<Long> field2() {
        return MountainTag.MOUNTAIN_TAG.MOUNTAIN_TAG_ID;
    }

    @Override
    public Long component1() {
        return getMountainEntityId();
    }

    @Override
    public Long component2() {
        return getMountainTagId();
    }

    @Override
    public Long value1() {
        return getMountainEntityId();
    }

    @Override
    public Long value2() {
        return getMountainTagId();
    }

    @Override
    public MountainTagRecord value1(Long value) {
        setMountainEntityId(value);
        return this;
    }

    @Override
    public MountainTagRecord value2(Long value) {
        setMountainTagId(value);
        return this;
    }

    @Override
    public MountainTagRecord values(Long value1, Long value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MountainTagRecord
     */
    public MountainTagRecord() {
        super(MountainTag.MOUNTAIN_TAG);
    }

    /**
     * Create a detached, initialised MountainTagRecord
     */
    public MountainTagRecord(Long mountainEntityId, Long mountainTagId) {
        super(MountainTag.MOUNTAIN_TAG);

        setMountainEntityId(mountainEntityId);
        setMountainTagId(mountainTagId);
    }

    /**
     * Create a detached, initialised MountainTagRecord
     */
    public MountainTagRecord(jooq.dsl.tables.pojos.MountainTag value) {
        super(MountainTag.MOUNTAIN_TAG);

        if (value != null) {
            setMountainEntityId(value.getMountainEntityId());
            setMountainTagId(value.getMountainTagId());
        }
    }
}
