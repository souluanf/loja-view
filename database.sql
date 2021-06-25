CREATE DATABASE IF NOT EXISTS loja_virtual;

CREATE USER IF NOT EXISTS 'loja'@'localhost'IDENTIFIED WITH caching_sha2_password BY 'Loj@V1rtu4l';
GRANT ALL ON loja_virtual.* TO 'loja'@'localhost';

create table if not exists category
(
    id   int auto_increment primary key,
    name varchar(50) not null
);

create table if not exists product
(
    id          int auto_increment primary key,
    name        varchar(50)  not null,
    description varchar(255) null,
    category_id int          null,
    constraint product_category_id_fk foreign key (category_id) references category (id)
);

insert into category (name)
values ('electronics'),
       ('books'),
       ('computers'),
       ('headphones'),
       ('television'),
       ('smartphones');


insert into product (name, description, category_id)
values ('Poco X3 NFC', 'Smartphone Poco X3', 6),
       ('Anker', 'Anker Q30', 4);