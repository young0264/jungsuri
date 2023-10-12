/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables.records;


import jooq.dsl.tables.Mountain;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MountainRecord extends UpdatableRecordImpl<MountainRecord> implements Record5<Long, Integer, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>jungsuri.mountain.id</code>.
     */
    public MountainRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.mountain.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>jungsuri.mountain.height</code>.
     */
    public MountainRecord setHeight(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.mountain.height</code>.
     */
    public Integer getHeight() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>jungsuri.mountain.image_url</code>.
     */
    public MountainRecord setImageUrl(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.mountain.image_url</code>.
     */
    public String getImageUrl() {
        return (String) get(2);
    }

    /**
     * Setter for <code>jungsuri.mountain.name</code>.
     */
    public MountainRecord setName(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.mountain.name</code>.
     */
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>jungsuri.mountain.short_locations</code>.
     */
    public MountainRecord setShortLocations(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.mountain.short_locations</code>.
     */
    public String getShortLocations() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Long, Integer, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Long, Integer, String, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Mountain.MOUNTAIN.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Mountain.MOUNTAIN.HEIGHT;
    }

    @Override
    public Field<String> field3() {
        return Mountain.MOUNTAIN.IMAGE_URL;
    }

    @Override
    public Field<String> field4() {
        return Mountain.MOUNTAIN.NAME;
    }

    @Override
    public Field<String> field5() {
        return Mountain.MOUNTAIN.SHORT_LOCATIONS;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getHeight();
    }

    @Override
    public String component3() {
        return getImageUrl();
    }

    @Override
    public String component4() {
        return getName();
    }

    @Override
    public String component5() {
        return getShortLocations();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getHeight();
    }

    @Override
    public String value3() {
        return getImageUrl();
    }

    @Override
    public String value4() {
        return getName();
    }

    @Override
    public String value5() {
        return getShortLocations();
    }

    @Override
    public MountainRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public MountainRecord value2(Integer value) {
        setHeight(value);
        return this;
    }

    @Override
    public MountainRecord value3(String value) {
        setImageUrl(value);
        return this;
    }

    @Override
    public MountainRecord value4(String value) {
        setName(value);
        return this;
    }

    @Override
    public MountainRecord value5(String value) {
        setShortLocations(value);
        return this;
    }

    @Override
    public MountainRecord values(Long value1, Integer value2, String value3, String value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MountainRecord
     */
    public MountainRecord() {
        super(Mountain.MOUNTAIN);
    }

    /**
     * Create a detached, initialised MountainRecord
     */
    public MountainRecord(Long id, Integer height, String imageUrl, String name, String shortLocations) {
        super(Mountain.MOUNTAIN);

        setId(id);
        setHeight(height);
        setImageUrl(imageUrl);
        setName(name);
        setShortLocations(shortLocations);
    }

    /**
     * Create a detached, initialised MountainRecord
     */
    public MountainRecord(jooq.dsl.tables.pojos.Mountain value) {
        super(Mountain.MOUNTAIN);

        if (value != null) {
            setId(value.getId());
            setHeight(value.getHeight());
            setImageUrl(value.getImageUrl());
            setName(value.getName());
            setShortLocations(value.getShortLocations());
        }
    }
}
