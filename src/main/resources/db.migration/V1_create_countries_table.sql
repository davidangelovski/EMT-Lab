create table countries(
    id bigserial primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    name varchar(255) not null,
    continent varchar(255) not null,
);