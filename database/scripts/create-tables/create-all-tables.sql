create table users
(
    id         serial
        primary key,
    username   varchar(32)  not null
        unique,
    password   varchar(128) not null,
    email      varchar(128) not null
        unique,
    birthday   date         not null,
    role       varchar(5)   not null,
    gender     varchar(6)   not null,
    created_at timestamp default now() not null 
);


create table review
(
    id          serial
        primary key,
    author      integer
        references users,
    song        integer
        references song,
    rating      integer
        constraint review_rating_check
            check ((rating >= 1) AND (rating <= 10)),
    comment     varchar(2000) default ''::character varying not null,
    review_date timestamp     default now()
);

create table song
(
    id           serial
        primary key,
    title        varchar(100) default 'unknown'::character varying not null,
    artist       integer
        references users,
    album        varchar(100) default 'unknown'::character varying,
    genre        varchar(50)                                       not null,
    release_date date                                              not null,
    uploaded_by  integer
        references users,
    upload_date  timestamp    default now() not null
);

