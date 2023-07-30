/*
 * This file is generated by jOOQ.
 */
package jooq.dsl;


import java.util.Arrays;
import java.util.List;

import jooq.dsl.tables.Account;
import jooq.dsl.tables.AccountTag;
import jooq.dsl.tables.Comment;
import jooq.dsl.tables.Likes;
import jooq.dsl.tables.Mountain;
import jooq.dsl.tables.MountainLocation;
import jooq.dsl.tables.MountainTag;
import jooq.dsl.tables.Notification;
import jooq.dsl.tables.Post;
import jooq.dsl.tables.Tag;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Jungsuri extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>jungsuri</code>
     */
    public static final Jungsuri JUNGSURI = new Jungsuri();

    /**
     * The table <code>jungsuri.account</code>.
     */
    public final Account ACCOUNT = Account.ACCOUNT;

    /**
     * The table <code>jungsuri.account_tag</code>.
     */
    public final AccountTag ACCOUNT_TAG = AccountTag.ACCOUNT_TAG;

    /**
     * The table <code>jungsuri.comment</code>.
     */
    public final Comment COMMENT = Comment.COMMENT;

    /**
     * The table <code>jungsuri.likes</code>.
     */
    public final Likes LIKES = Likes.LIKES;

    /**
     * The table <code>jungsuri.mountain</code>.
     */
    public final Mountain MOUNTAIN = Mountain.MOUNTAIN;

    /**
     * The table <code>jungsuri.mountain_location</code>.
     */
    public final MountainLocation MOUNTAIN_LOCATION = MountainLocation.MOUNTAIN_LOCATION;

    /**
     * The table <code>jungsuri.mountain_tag</code>.
     */
    public final MountainTag MOUNTAIN_TAG = MountainTag.MOUNTAIN_TAG;

    /**
     * The table <code>jungsuri.notification</code>.
     */
    public final Notification NOTIFICATION = Notification.NOTIFICATION;

    /**
     * The table <code>jungsuri.post</code>.
     */
    public final Post POST = Post.POST;

    /**
     * The table <code>jungsuri.tag</code>.
     */
    public final Tag TAG = Tag.TAG;

    /**
     * No further instances allowed
     */
    private Jungsuri() {
        super("jungsuri", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Account.ACCOUNT,
            AccountTag.ACCOUNT_TAG,
            Comment.COMMENT,
            Likes.LIKES,
            Mountain.MOUNTAIN,
            MountainLocation.MOUNTAIN_LOCATION,
            MountainTag.MOUNTAIN_TAG,
            Notification.NOTIFICATION,
            Post.POST,
            Tag.TAG
        );
    }
}
