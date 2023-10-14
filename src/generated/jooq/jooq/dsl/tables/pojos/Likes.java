/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import jooq.dsl.enums.LikesType;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Likes implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long accountEntityId;
    private final Long commentEntityId;
    private final LocalDateTime createdAt;
    private final Long id;
    private final Long postEntityId;
    private final LikesType type;

    public Likes(Likes value) {
        this.accountEntityId = value.accountEntityId;
        this.commentEntityId = value.commentEntityId;
        this.createdAt = value.createdAt;
        this.id = value.id;
        this.postEntityId = value.postEntityId;
        this.type = value.type;
    }

    public Likes(
        Long accountEntityId,
        Long commentEntityId,
        LocalDateTime createdAt,
        Long id,
        Long postEntityId,
        LikesType type
    ) {
        this.accountEntityId = accountEntityId;
        this.commentEntityId = commentEntityId;
        this.createdAt = createdAt;
        this.id = id;
        this.postEntityId = postEntityId;
        this.type = type;
    }

    /**
     * Getter for <code>jungsuri.likes.account_entity_id</code>.
     */
    public Long getAccountEntityId() {
        return this.accountEntityId;
    }

    /**
     * Getter for <code>jungsuri.likes.comment_entity_id</code>.
     */
    public Long getCommentEntityId() {
        return this.commentEntityId;
    }

    /**
     * Getter for <code>jungsuri.likes.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Getter for <code>jungsuri.likes.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>jungsuri.likes.post_entity_id</code>.
     */
    public Long getPostEntityId() {
        return this.postEntityId;
    }

    /**
     * Getter for <code>jungsuri.likes.type</code>.
     */
    public LikesType getType() {
        return this.type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Likes (");

        sb.append(accountEntityId);
        sb.append(", ").append(commentEntityId);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(id);
        sb.append(", ").append(postEntityId);
        sb.append(", ").append(type);

        sb.append(")");
        return sb.toString();
    }
}
