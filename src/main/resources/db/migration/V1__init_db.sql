

create table account
(
    email_verified                 bit          not null,
    mountain_exp                   int          not null,
    post_created_checked           bit          not null,
    user_role                      tinyint      null,
    email_check_token_generated_at datetime(6)  null,
    id                             bigint auto_increment primary key,
    joined_at                      datetime(6)  null,
    bio                            varchar(255) null,
    email                          varchar(255) null,
    email_token                    varchar(255) null,
    location                       varchar(255) null,
    login_id                       varchar(255) null,
    name                           varchar(255) null,
    occupation                     varchar(255) null,
    password                       varchar(255) null,
    profile_image                  varchar(255) null,
    role                           varchar(255) null,
    constraint UK_q0uja26qgu1atulenwup9rxyr
        unique (email),
    constraint UK_tqpuaqxalcaeddpc0m6mcxobg
        unique (login_id),
    check (`user_role` between 0 and 1)
);


create table post
(
    comment_count     int          not null,
    like_count        int          not null,
    view_count        int          not null,
    account_entity_id bigint       null,
    created_at        datetime(6)  not null,
    id                bigint auto_increment
        primary key,
    updated_at        datetime(6)  null,
    author            varchar(255) not null,
    content           varchar(255) not null,
    image_path        varchar(255) null,
    login_id          varchar(255) not null,
    title             varchar(255) not null,
    constraint FKmsseu6ohwwe5jtpbt8k45vjcg
        foreign key (account_entity_id) references account (id)
);


create table comment
(
    like_count        int          null,
    account_entity_id bigint       null,
    created_at        datetime(6)  null,
    id                bigint auto_increment
        primary key,
    post_entity_id    bigint       null,
    updated_at        datetime(6)  null,
    author            varchar(255) null,
    content           varchar(255) null,
    constraint FK9n1h3p614qigg7sikiakmu3pa
        foreign key (post_entity_id) references post (id),
    constraint FKstetqkxjyp52lprdmswga4gmi
        foreign key (account_entity_id) references account (id)
);



create table tag
(
    used_count int          not null,
    created_at datetime(6)  null,
    id         bigint auto_increment
        primary key,
    name       varchar(255) null
);




create table post_tag
(
    created_at     datetime(6) null,
    id             bigint auto_increment
        primary key,
    post_entity_id bigint      null,
    tag_id         bigint      null,
    constraint FKac1wdchd2pnur3fl225obmlg0
        foreign key (tag_id) references tag (id),
    constraint FKedu8n21pdywbafr0kqmb76k6m
        foreign key (post_entity_id) references post (id)
);



create table account_tag
(
    account_entity_id bigint null,
    id                bigint not null primary key,
    constraint FKt9yc2qdrkd8ish8t5ph28gkx0 foreign key (id) references tag (id),
    constraint FKvj9ra64lqw442nt0tu2x1k0k foreign key (account_entity_id) references account (id)
);

create table likes
(
    account_entity_id bigint                   not null,
    comment_entity_id bigint                   null,
    created_at        datetime(6)              not null,
    id                bigint auto_increment
        primary key,
    post_entity_id    bigint                   null,
    type              enum ('COMMENT', 'POST') not null,
    constraint FK7sa8yygivojjdu6cax4kxy124
        foreign key (account_entity_id) references account (id),
    constraint FKnj0vdd299pl2jc1k3f8fqv6aq
        foreign key (post_entity_id) references post (id),
    constraint FKrjmqd34n30oufbs4uxdj1yka7
        foreign key (comment_entity_id) references comment (id)
);


create table mountain
(
    height          int          not null,
    id              bigint auto_increment
        primary key,
    image_url       varchar(255) null,
    name            varchar(255) not null,
    short_locations varchar(255) null,
    constraint UK_h5ifmkso2wlxn8l8jfesjkvn5
        unique (name)
);



create table mountain_location
(
    id                 bigint auto_increment
        primary key,
    mountain_entity_id bigint       null,
    location           varchar(255) null,
    constraint FKaoy0vaeyls649l5hb02fki4k6
        foreign key (mountain_entity_id) references mountain (id)
);



create table mountain_tag
(
    id                 bigint not null
        primary key,
    mountain_entity_id bigint null,
    constraint FKnbsyomu9dslubehrkcoy5go7a
        foreign key (id) references tag (id),
    constraint FKsknbymdinhq0dm6f748ccyax0
        foreign key (mountain_entity_id) references mountain (id)
);


create table notification
(
    checked           bit                                                        not null,
    account_entity_id bigint                                                     null,
    created_at        datetime(6)                                                null,
    id                bigint auto_increment
        primary key,
    link              varchar(255)                                               null,
    message           varchar(255)                                               not null,
    title             varchar(255)                                               not null,
    type              enum ('MEMBER_ENROLLMENT', 'POST_CREATED', 'POST_UPDATED') null,
    constraint FKn2q3msn4yfv43j6m6690cvk75
        foreign key (account_entity_id) references account (id)
);

