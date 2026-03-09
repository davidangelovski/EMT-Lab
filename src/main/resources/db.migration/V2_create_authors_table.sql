create table authors (
    id bigserial primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    name varchar(255) not null,
    surname varchar(255) not null,
    country_id  bigint not null references countries(id) on delete cascade
);