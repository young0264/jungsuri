/*
 * This file is generated by jOOQ.
 */
package jooq.dsl;


import jooq.dsl.tables.Account;
import jooq.dsl.tables.AccountTag;
import jooq.dsl.tables.Comment;
import jooq.dsl.tables.Likes;
import jooq.dsl.tables.Mountain;
import jooq.dsl.tables.MountainExp;
import jooq.dsl.tables.MountainLocation;
import jooq.dsl.tables.MountainTag;
import jooq.dsl.tables.Notification;
import jooq.dsl.tables.Post;
import jooq.dsl.tables.PostTag;
import jooq.dsl.tables.Tag;
import jooq.dsl.tables.records.AccountRecord;
import jooq.dsl.tables.records.AccountTagRecord;
import jooq.dsl.tables.records.CommentRecord;
import jooq.dsl.tables.records.LikesRecord;
import jooq.dsl.tables.records.MountainExpRecord;
import jooq.dsl.tables.records.MountainLocationRecord;
import jooq.dsl.tables.records.MountainRecord;
import jooq.dsl.tables.records.MountainTagRecord;
import jooq.dsl.tables.records.NotificationRecord;
import jooq.dsl.tables.records.PostRecord;
import jooq.dsl.tables.records.PostTagRecord;
import jooq.dsl.tables.records.TagRecord;

import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;


/**
 * A class modelling foreign key relationships and constraints of tables in
 * jungsuri.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<AccountRecord> KEY_ACCOUNT_PRIMARY = Internal.createUniqueKey(Account.ACCOUNT, DSL.name("KEY_account_PRIMARY"), new TableField[] { Account.ACCOUNT.ID }, true);
    public static final UniqueKey<AccountRecord> KEY_ACCOUNT_UK_Q0UJA26QGU1ATULENWUP9RXYR = Internal.createUniqueKey(Account.ACCOUNT, DSL.name("KEY_account_UK_q0uja26qgu1atulenwup9rxyr"), new TableField[] { Account.ACCOUNT.EMAIL }, true);
    public static final UniqueKey<AccountRecord> KEY_ACCOUNT_UK_TQPUAQXALCAEDDPC0M6MCXOBG = Internal.createUniqueKey(Account.ACCOUNT, DSL.name("KEY_account_UK_tqpuaqxalcaeddpc0m6mcxobg"), new TableField[] { Account.ACCOUNT.LOGIN_ID }, true);
    public static final UniqueKey<AccountTagRecord> KEY_ACCOUNT_TAG_PRIMARY = Internal.createUniqueKey(AccountTag.ACCOUNT_TAG, DSL.name("KEY_account_tag_PRIMARY"), new TableField[] { AccountTag.ACCOUNT_TAG.ID }, true);
    public static final UniqueKey<CommentRecord> KEY_COMMENT_PRIMARY = Internal.createUniqueKey(Comment.COMMENT, DSL.name("KEY_comment_PRIMARY"), new TableField[] { Comment.COMMENT.ID }, true);
    public static final UniqueKey<LikesRecord> KEY_LIKES_PRIMARY = Internal.createUniqueKey(Likes.LIKES, DSL.name("KEY_likes_PRIMARY"), new TableField[] { Likes.LIKES.ID }, true);
    public static final UniqueKey<MountainRecord> KEY_MOUNTAIN_PRIMARY = Internal.createUniqueKey(Mountain.MOUNTAIN, DSL.name("KEY_mountain_PRIMARY"), new TableField[] { Mountain.MOUNTAIN.ID }, true);
    public static final UniqueKey<MountainRecord> KEY_MOUNTAIN_UK_H5IFMKSO2WLXN8L8JFESJKVN5 = Internal.createUniqueKey(Mountain.MOUNTAIN, DSL.name("KEY_mountain_UK_h5ifmkso2wlxn8l8jfesjkvn5"), new TableField[] { Mountain.MOUNTAIN.NAME }, true);
    public static final UniqueKey<MountainExpRecord> KEY_MOUNTAIN_EXP_PRIMARY = Internal.createUniqueKey(MountainExp.MOUNTAIN_EXP, DSL.name("KEY_mountain_exp_PRIMARY"), new TableField[] { MountainExp.MOUNTAIN_EXP.ID }, true);
    public static final UniqueKey<MountainLocationRecord> KEY_MOUNTAIN_LOCATION_PRIMARY = Internal.createUniqueKey(MountainLocation.MOUNTAIN_LOCATION, DSL.name("KEY_mountain_location_PRIMARY"), new TableField[] { MountainLocation.MOUNTAIN_LOCATION.ID }, true);
    public static final UniqueKey<MountainTagRecord> KEY_MOUNTAIN_TAG_PRIMARY = Internal.createUniqueKey(MountainTag.MOUNTAIN_TAG, DSL.name("KEY_mountain_tag_PRIMARY"), new TableField[] { MountainTag.MOUNTAIN_TAG.ID }, true);
    public static final UniqueKey<NotificationRecord> KEY_NOTIFICATION_PRIMARY = Internal.createUniqueKey(Notification.NOTIFICATION, DSL.name("KEY_notification_PRIMARY"), new TableField[] { Notification.NOTIFICATION.ID }, true);
    public static final UniqueKey<PostRecord> KEY_POST_PRIMARY = Internal.createUniqueKey(Post.POST, DSL.name("KEY_post_PRIMARY"), new TableField[] { Post.POST.ID }, true);
    public static final UniqueKey<PostTagRecord> KEY_POST_TAG_PRIMARY = Internal.createUniqueKey(PostTag.POST_TAG, DSL.name("KEY_post_tag_PRIMARY"), new TableField[] { PostTag.POST_TAG.ID }, true);
    public static final UniqueKey<TagRecord> KEY_TAG_PRIMARY = Internal.createUniqueKey(Tag.TAG, DSL.name("KEY_tag_PRIMARY"), new TableField[] { Tag.TAG.ID }, true);
    public static final UniqueKey<TagRecord> KEY_TAG_UK_1WDPSED5KNA2Y38HNBGRNHI5B = Internal.createUniqueKey(Tag.TAG, DSL.name("KEY_tag_UK_1wdpsed5kna2y38hnbgrnhi5b"), new TableField[] { Tag.TAG.NAME }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<AccountTagRecord, TagRecord> FKJYN3BHQGEB2H5G85D7QHEGHCP = Internal.createForeignKey(AccountTag.ACCOUNT_TAG, DSL.name("FKjyn3bhqgeb2h5g85d7qheghcp"), new TableField[] { AccountTag.ACCOUNT_TAG.ACCOUNT_TAG_ID }, Keys.KEY_TAG_PRIMARY, new TableField[] { Tag.TAG.ID }, true);
    public static final ForeignKey<AccountTagRecord, TagRecord> FKT9YC2QDRKD8ISH8T5PH28GKX0 = Internal.createForeignKey(AccountTag.ACCOUNT_TAG, DSL.name("FKt9yc2qdrkd8ish8t5ph28gkx0"), new TableField[] { AccountTag.ACCOUNT_TAG.ID }, Keys.KEY_TAG_PRIMARY, new TableField[] { Tag.TAG.ID }, true);
    public static final ForeignKey<AccountTagRecord, AccountRecord> FKVJ9RA64LQW442NT0TU2X1K0K = Internal.createForeignKey(AccountTag.ACCOUNT_TAG, DSL.name("FKvj9ra64lqw442nt0tu2x1k0k"), new TableField[] { AccountTag.ACCOUNT_TAG.ACCOUNT_ENTITY_ID }, Keys.KEY_ACCOUNT_PRIMARY, new TableField[] { Account.ACCOUNT.ID }, true);
    public static final ForeignKey<CommentRecord, PostRecord> FK9N1H3P614QIGG7SIKIAKMU3PA = Internal.createForeignKey(Comment.COMMENT, DSL.name("FK9n1h3p614qigg7sikiakmu3pa"), new TableField[] { Comment.COMMENT.POST_ENTITY_ID }, Keys.KEY_POST_PRIMARY, new TableField[] { Post.POST.ID }, true);
    public static final ForeignKey<CommentRecord, AccountRecord> FKSTETQKXJYP52LPRDMSWGA4GMI = Internal.createForeignKey(Comment.COMMENT, DSL.name("FKstetqkxjyp52lprdmswga4gmi"), new TableField[] { Comment.COMMENT.ACCOUNT_ENTITY_ID }, Keys.KEY_ACCOUNT_PRIMARY, new TableField[] { Account.ACCOUNT.ID }, true);
    public static final ForeignKey<LikesRecord, AccountRecord> FK7SA8YYGIVOJJDU6CAX4KXY124 = Internal.createForeignKey(Likes.LIKES, DSL.name("FK7sa8yygivojjdu6cax4kxy124"), new TableField[] { Likes.LIKES.ACCOUNT_ENTITY_ID }, Keys.KEY_ACCOUNT_PRIMARY, new TableField[] { Account.ACCOUNT.ID }, true);
    public static final ForeignKey<LikesRecord, PostRecord> FKNJ0VDD299PL2JC1K3F8FQV6AQ = Internal.createForeignKey(Likes.LIKES, DSL.name("FKnj0vdd299pl2jc1k3f8fqv6aq"), new TableField[] { Likes.LIKES.POST_ENTITY_ID }, Keys.KEY_POST_PRIMARY, new TableField[] { Post.POST.ID }, true);
    public static final ForeignKey<LikesRecord, CommentRecord> FKRJMQD34N30OUFBS4UXDJ1YKA7 = Internal.createForeignKey(Likes.LIKES, DSL.name("FKrjmqd34n30oufbs4uxdj1yka7"), new TableField[] { Likes.LIKES.COMMENT_ENTITY_ID }, Keys.KEY_COMMENT_PRIMARY, new TableField[] { Comment.COMMENT.ID }, true);
    public static final ForeignKey<MountainExpRecord, AccountRecord> FK18VHIOD912YSPTJM04RFOP67G = Internal.createForeignKey(MountainExp.MOUNTAIN_EXP, DSL.name("FK18vhiod912ysptjm04rfop67g"), new TableField[] { MountainExp.MOUNTAIN_EXP.ACCOUNT_ENTITY_ID }, Keys.KEY_ACCOUNT_PRIMARY, new TableField[] { Account.ACCOUNT.ID }, true);
    public static final ForeignKey<MountainExpRecord, MountainRecord> FKIEDKMJ00BXRXV9LBY357H71EE = Internal.createForeignKey(MountainExp.MOUNTAIN_EXP, DSL.name("FKiedkmj00bxrxv9lby357h71ee"), new TableField[] { MountainExp.MOUNTAIN_EXP.MOUNTAIN_ENTITY_ID }, Keys.KEY_MOUNTAIN_PRIMARY, new TableField[] { Mountain.MOUNTAIN.ID }, true);
    public static final ForeignKey<MountainLocationRecord, MountainRecord> FKAOY0VAEYLS649L5HB02FKI4K6 = Internal.createForeignKey(MountainLocation.MOUNTAIN_LOCATION, DSL.name("FKaoy0vaeyls649l5hb02fki4k6"), new TableField[] { MountainLocation.MOUNTAIN_LOCATION.MOUNTAIN_ENTITY_ID }, Keys.KEY_MOUNTAIN_PRIMARY, new TableField[] { Mountain.MOUNTAIN.ID }, true);
    public static final ForeignKey<MountainTagRecord, TagRecord> FKNBSYOMU9DSLUBEHRKCOY5GO7A = Internal.createForeignKey(MountainTag.MOUNTAIN_TAG, DSL.name("FKnbsyomu9dslubehrkcoy5go7a"), new TableField[] { MountainTag.MOUNTAIN_TAG.ID }, Keys.KEY_TAG_PRIMARY, new TableField[] { Tag.TAG.ID }, true);
    public static final ForeignKey<MountainTagRecord, TagRecord> FKRUDYQE1F6KXJXAXLJH820HMJ7 = Internal.createForeignKey(MountainTag.MOUNTAIN_TAG, DSL.name("FKrudyqe1f6kxjxaxljh820hmj7"), new TableField[] { MountainTag.MOUNTAIN_TAG.MOUNTAIN_TAG_ID }, Keys.KEY_TAG_PRIMARY, new TableField[] { Tag.TAG.ID }, true);
    public static final ForeignKey<MountainTagRecord, MountainRecord> FKSKNBYMDINHQ0DM6F748CCYAX0 = Internal.createForeignKey(MountainTag.MOUNTAIN_TAG, DSL.name("FKsknbymdinhq0dm6f748ccyax0"), new TableField[] { MountainTag.MOUNTAIN_TAG.MOUNTAIN_ENTITY_ID }, Keys.KEY_MOUNTAIN_PRIMARY, new TableField[] { Mountain.MOUNTAIN.ID }, true);
    public static final ForeignKey<NotificationRecord, AccountRecord> FKN2Q3MSN4YFV43J6M6690CVK75 = Internal.createForeignKey(Notification.NOTIFICATION, DSL.name("FKn2q3msn4yfv43j6m6690cvk75"), new TableField[] { Notification.NOTIFICATION.ACCOUNT_ENTITY_ID }, Keys.KEY_ACCOUNT_PRIMARY, new TableField[] { Account.ACCOUNT.ID }, true);
    public static final ForeignKey<PostRecord, AccountRecord> FKMSSEU6OHWWE5JTPBT8K45VJCG = Internal.createForeignKey(Post.POST, DSL.name("FKmsseu6ohwwe5jtpbt8k45vjcg"), new TableField[] { Post.POST.ACCOUNT_ENTITY_ID }, Keys.KEY_ACCOUNT_PRIMARY, new TableField[] { Account.ACCOUNT.ID }, true);
    public static final ForeignKey<PostTagRecord, TagRecord> FKAC1WDCHD2PNUR3FL225OBMLG0 = Internal.createForeignKey(PostTag.POST_TAG, DSL.name("FKac1wdchd2pnur3fl225obmlg0"), new TableField[] { PostTag.POST_TAG.TAG_ID }, Keys.KEY_TAG_PRIMARY, new TableField[] { Tag.TAG.ID }, true);
    public static final ForeignKey<PostTagRecord, PostRecord> FKEDU8N21PDYWBAFR0KQMB76K6M = Internal.createForeignKey(PostTag.POST_TAG, DSL.name("FKedu8n21pdywbafr0kqmb76k6m"), new TableField[] { PostTag.POST_TAG.POST_ENTITY_ID }, Keys.KEY_POST_PRIMARY, new TableField[] { Post.POST.ID }, true);
}
