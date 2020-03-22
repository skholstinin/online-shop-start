create table users
(
    id       serial not null,
    email    varchar,
    password varchar,
    name     varchar,
    phone    varchar,
    address  varchar,
    active   varchar default true,
    role     varchar default 'ROLE_CUSTOMER'::character varying
);

alter table users
    owner to uhyilhmkkpcius;

create table product_info
(
    product_id          varchar,
    product_name        varchar,
    product_price       numeric,
    product_stock       integer,
    product_description varchar,
    product_icon        varchar,
    product_status      integer,
    category_type       integer,
    create_time         timestamp,
    update_time         timestamp
);

alter table product_info
    owner to uhyilhmkkpcius;

create table product_category
(
    id          integer,
    name        varchar,
    category    integer,
    create_time timestamp,
    update_time varchar
);

alter table product_category
    owner to uhyilhmkkpcius;

create table product_in_order
(
    id                  serial not null
        constraint product_in_order_pk
            primary key,
    product_category    integer,
    product_quantity    integer,
    product_description varchar,
    product_icon        varchar,
    product_id          varchar,
    product_name        varchar,
    product_price       varchar,
    prodcut_stock       integer,
    order_id            integer,
    customer_id         integer
);

alter table product_in_order
    owner to uhyilhmkkpcius;

create unique index product_in_order_id_uindex
    on product_in_order (id);

