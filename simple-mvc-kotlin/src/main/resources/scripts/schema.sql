drop schema if exists `db-rinhabackend`;
create schema if not exists `db-rinhabackend` character set utf8mb4 collate utf8mb4_unicode_ci;
use `db-rinhabackend`;
create table if not exists `person` (
    id char(36),
    born_at VARCHAR(10),
    name VARCHAR(100),
    nickname VARCHAR(32) unique,
    stack VARCHAR(32),
    primary key (id)
) engine=InnoDB

