/*
 * This file is generated by jOOQ.
 */
package jooq.dsl.tables.pojos;


import java.io.Serializable;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long accountEntityId;
    private final Long id;
    private final String dtype;
    private final String name;

    public Tag(Tag value) {
        this.accountEntityId = value.accountEntityId;
        this.id = value.id;
        this.dtype = value.dtype;
        this.name = value.name;
    }

    public Tag(
        Long accountEntityId,
        Long id,
        String dtype,
        String name
    ) {
        this.accountEntityId = accountEntityId;
        this.id = id;
        this.dtype = dtype;
        this.name = name;
    }

    /**
     * Getter for <code>jungsuri.tag.account_entity_id</code>.
     */
    public Long getAccountEntityId() {
        return this.accountEntityId;
    }

    /**
     * Getter for <code>jungsuri.tag.id</code>.
     */
    public Long getId() {
        return this.id;
    }

    /**
     * Getter for <code>jungsuri.tag.dtype</code>.
     */
    public String getDtype() {
        return this.dtype;
    }

    /**
     * Getter for <code>jungsuri.tag.name</code>.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tag (");

        sb.append(accountEntityId);
        sb.append(", ").append(id);
        sb.append(", ").append(dtype);
        sb.append(", ").append(name);

        sb.append(")");
        return sb.toString();
    }
}