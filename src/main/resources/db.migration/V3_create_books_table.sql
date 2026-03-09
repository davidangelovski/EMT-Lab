create table books (
    id bigserial primary key,
    created_at timestamp not null,
    updated_at timestamp not null,
    name varchar(255) not null,
    category varchar(255) not null,
    author_id bigint not null references authors(id) on delete cascade,
    state varchar(255) not null,
    available_copies int not null,
);