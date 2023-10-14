/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables.records;


import java.time.LocalDateTime;

import jooq.dsl.tables.Comment;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CommentRecord extends UpdatableRecordImpl<CommentRecord> implements Record8<Integer, Long, LocalDateTime, Long, Long, LocalDateTime, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>jungsuri.comment.like_count</code>.
     */
    public CommentRecord setLikeCount(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.comment.like_count</code>.
     */
    public Integer getLikeCount() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>jungsuri.comment.account_entity_id</code>.
     */
    public CommentRecord setAccountEntityId(Long value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.comment.account_entity_id</code>.
     */
    public Long getAccountEntityId() {
        return (Long) get(1);
    }

    /**
     * Setter for <code>jungsuri.comment.created_at</code>.
     */
    public CommentRecord setCreatedAt(LocalDateTime value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.comment.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(2);
    }

    /**
     * Setter for <code>jungsuri.comment.id</code>.
     */
    public CommentRecord setId(Long value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.comment.id</code>.
     */
    public Long getId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>jungsuri.comment.post_entity_id</code>.
     */
    public CommentRecord setPostEntityId(Long value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.comment.post_entity_id</code>.
     */
    public Long getPostEntityId() {
        return (Long) get(4);
    }

    /**
     * Setter for <code>jungsuri.comment.updated_at</code>.
     */
    public CommentRecord setUpdatedAt(LocalDateTime value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.comment.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>jungsuri.comment.author</code>.
     */
    public CommentRecord setAuthor(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.comment.author</code>.
     */
    public String getAuthor() {
        return (String) get(6);
    }

    /**
     * Setter for <code>jungsuri.comment.content</code>.
     */
    public CommentRecord setContent(String value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>jungsuri.comment.content</code>.
     */
    public String getContent() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<Integer, Long, LocalDateTime, Long, Long, LocalDateTime, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<Integer, Long, LocalDateTime, Long, Long, LocalDateTime, String, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Comment.COMMENT.LIKE_COUNT;
    }

    @Override
    public Field<Long> field2() {
        return Comment.COMMENT.ACCOUNT_ENTITY_ID;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return Comment.COMMENT.CREATED_AT;
    }

    @Override
    public Field<Long> field4() {
        return Comment.COMMENT.ID;
    }

    @Override
    public Field<Long> field5() {
        return Comment.COMMENT.POST_ENTITY_ID;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return Comment.COMMENT.UPDATED_AT;
    }

    @Override
    public Field<String> field7() {
        return Comment.COMMENT.AUTHOR;
    }

    @Override
    public Field<String> field8() {
        return Comment.COMMENT.CONTENT;
    }

    @Override
    public Integer component1() {
        return getLikeCount();
    }

    @Override
    public Long component2() {
        return getAccountEntityId();
    }

    @Override
    public LocalDateTime component3() {
        return getCreatedAt();
    }

    @Override
    public Long component4() {
        return getId();
    }

    @Override
    public Long component5() {
        return getPostEntityId();
    }

    @Override
    public LocalDateTime component6() {
        return getUpdatedAt();
    }

    @Override
    public String component7() {
        return getAuthor();
    }

    @Override
    public String component8() {
        return getContent();
    }

    @Override
    public Integer value1() {
        return getLikeCount();
    }

    @Override
    public Long value2() {
        return getAccountEntityId();
    }

    @Override
    public LocalDateTime value3() {
        return getCreatedAt();
    }

    @Override
    public Long value4() {
        return getId();
    }

    @Override
    public Long value5() {
        return getPostEntityId();
    }

    @Override
    public LocalDateTime value6() {
        return getUpdatedAt();
    }

    @Override
    public String value7() {
        return getAuthor();
    }

    @Override
    public String value8() {
        return getContent();
    }

    @Override
    public CommentRecord value1(Integer value) {
        setLikeCount(value);
        return this;
    }

    @Override
    public CommentRecord value2(Long value) {
        setAccountEntityId(value);
        return this;
    }

    @Override
    public CommentRecord value3(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public CommentRecord value4(Long value) {
        setId(value);
        return this;
    }

    @Override
    public CommentRecord value5(Long value) {
        setPostEntityId(value);
        return this;
    }

    @Override
    public CommentRecord value6(LocalDateTime value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public CommentRecord value7(String value) {
        setAuthor(value);
        return this;
    }

    @Override
    public CommentRecord value8(String value) {
        setContent(value);
        return this;
    }

    @Override
    public CommentRecord values(Integer value1, Long value2, LocalDateTime value3, Long value4, Long value5, LocalDateTime value6, String value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CommentRecord
     */
    public CommentRecord() {
        super(Comment.COMMENT);
    }

    /**
     * Create a detached, initialised CommentRecord
     */
    public CommentRecord(Integer likeCount, Long accountEntityId, LocalDateTime createdAt, Long id, Long postEntityId, LocalDateTime updatedAt, String author, String content) {
        super(Comment.COMMENT);

        setLikeCount(likeCount);
        setAccountEntityId(accountEntityId);
        setCreatedAt(createdAt);
        setId(id);
        setPostEntityId(postEntityId);
        setUpdatedAt(updatedAt);
        setAuthor(author);
        setContent(content);
    }

    /**
     * Create a detached, initialised CommentRecord
     */
    public CommentRecord(jooq.dsl.tables.pojos.Comment value) {
        super(Comment.COMMENT);

        if (value != null) {
            setLikeCount(value.getLikeCount());
            setAccountEntityId(value.getAccountEntityId());
            setCreatedAt(value.getCreatedAt());
            setId(value.getId());
            setPostEntityId(value.getPostEntityId());
            setUpdatedAt(value.getUpdatedAt());
            setAuthor(value.getAuthor());
            setContent(value.getContent());
        }
    }
}
