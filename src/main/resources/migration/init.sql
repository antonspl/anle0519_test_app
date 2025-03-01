create table users (
    id bigserial primary key,
    username varchar(20) not null,
    password varchar(100) not null
);
create table user_roles (
    user_id bigserial,
    role varchar(20) not null
);
alter table users add constraint unique_username unique(username);
insert into public.users (username, password) values ('admin', '$2a$12$KotdOWAMbbgdorWp.wOrqOxpuotiV6dmb7jbII4/sLex0ZqbeUst.');
insert into public.user_roles (user_id, role) values (1, 'ADMIN');
insert into public.user_roles (user_id, role) values (1, 'USER');

create table customers (
    id bigserial primary key,
    name varchar(20) not null,
    email varchar(20) not null,
    description text null,
    date_created timestamp with time zone default current_timestamp
);
insert into public.customers (name, email, description) values ('Anton', 'antonspl@yandex.ru', 'its me');
insert into public.customers (name, email, description) values ('Sergio', 'other@gmail.com', 'its not me');
insert into public.customers (name, email, description) values ('Amorim', 'amorim@gmail.com', 'who is him');
