/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PostTag implements Serializable {

    private static final long serialVersionUID = 1L;

    private final LocalDateTime createdAt;
    private final Long id;
    private final Long postEntityId;
    private final Long tagId;
    private final Long postTagId;

    public PostTag(PostTag value) {
        this.createdAt = value.createdAt;
        this.id = value.id;
        this.postEntityId = value.postEntityId;
        this.tagId = value.tagId;
        this.postTagId = value.postTagId;
    }

    public PostTag(
        LocalDateTime createdAt,
        Long id,
        Long postEntityId,
        Long tagId,
        Long postTagId
    ) {
        this.createdAt = createdAt;
        this.id = id;
        this.postEntityId = postEntityId;
        this.tagId = tagId;
        this.postTagId = postTagId;
    }

    /**
     * Getter for <code>jungsuri.post_tag.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Getter for <code>jungsuri.post_tag.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>jungsuri.post_tag.post_entity_id</code>.
     */
    public Long getPostEntityId() {
        return this.postEntityId;
    }

    /**
     * Getter for <code>jungsuri.post_tag.tag_id</code>.
     */
    public Long getTagId() {
        return this.tagId;
    }

    /**
     * Getter for <code>jungsuri.post_tag.post_tag_id</code>.
     */
    public Long getPostTagId() {
        return this.postTagId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PostTag (");

        sb.append(createdAt);
        sb.append(", ").append(id);
        sb.append(", ").append(postEntityId);
        sb.append(", ").append(tagId);
        sb.append(", ").append(postTagId);

        sb.append(")");
        return sb.toString();
    }
}
