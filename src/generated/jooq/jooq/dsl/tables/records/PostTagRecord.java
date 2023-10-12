/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables.records;


import java.time.LocalDateTime;

import jooq.dsl.tables.PostTag;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PostTagRecord extends UpdatableRecordImpl<PostTagRecord> implements Record4<Long, LocalDateTime, Long, Long> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>jungsuri.post_tag.id</code>.
     */
    public PostTagRecord setId(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.post_tag.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>jungsuri.post_tag.created_at</code>.
     */
    public PostTagRecord setCreatedAt(LocalDateTime value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.post_tag.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(1);
    }

    /**
     * Setter for <code>jungsuri.post_tag.post_entity_id</code>.
     */
    public PostTagRecord setPostEntityId(Long value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.post_tag.post_entity_id</code>.
     */
    public Long getPostEntityId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>jungsuri.post_tag.tag_id</code>.
     */
    public PostTagRecord setTagId(Long value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.post_tag.tag_id</code>.
     */
    public Long getTagId() {
        return (Long) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Long, LocalDateTime, Long, Long> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Long, LocalDateTime, Long, Long> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return PostTag.POST_TAG.ID;
    }

    @Override
    public Field<LocalDateTime> field2() {
        return PostTag.POST_TAG.CREATED_AT;
    }

    @Override
    public Field<Long> field3() {
        return PostTag.POST_TAG.POST_ENTITY_ID;
    }

    @Override
    public Field<Long> field4() {
        return PostTag.POST_TAG.TAG_ID;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public LocalDateTime component2() {
        return getCreatedAt();
    }

    @Override
    public Long component3() {
        return getPostEntityId();
    }

    @Override
    public Long component4() {
        return getTagId();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public LocalDateTime value2() {
        return getCreatedAt();
    }

    @Override
    public Long value3() {
        return getPostEntityId();
    }

    @Override
    public Long value4() {
        return getTagId();
    }

    @Override
    public PostTagRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public PostTagRecord value2(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public PostTagRecord value3(Long value) {
        setPostEntityId(value);
        return this;
    }

    @Override
    public PostTagRecord value4(Long value) {
        setTagId(value);
        return this;
    }

    @Override
    public PostTagRecord values(Long value1, LocalDateTime value2, Long value3, Long value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PostTagRecord
     */
    public PostTagRecord() {
        super(PostTag.POST_TAG);
    }

    /**
     * Create a detached, initialised PostTagRecord
     */
    public PostTagRecord(Long id, LocalDateTime createdAt, Long postEntityId, Long tagId) {
        super(PostTag.POST_TAG);

        setId(id);
        setCreatedAt(createdAt);
        setPostEntityId(postEntityId);
        setTagId(tagId);
    }

    /**
     * Create a detached, initialised PostTagRecord
     */
    public PostTagRecord(jooq.dsl.tables.pojos.PostTag value) {
        super(PostTag.POST_TAG);

        if (value != null) {
            setId(value.getId());
            setCreatedAt(value.getCreatedAt());
            setPostEntityId(value.getPostEntityId());
            setTagId(value.getTagId());
        }
    }
}
