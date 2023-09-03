create table person (
    id char(36),
    born_at VARCHAR(10),
    name VARCHAR(100),
    nickname VARCHAR(32) unique,
    stack VARCHAR(32),
    primary key (id)
) engine=InnoDB