-- CREATE SCHEMA exprod;
-- SET SEARCH_PATH = exprod;
-- CREATE ROLE exprod PASSWORD 'exprod' LOGIN;

-- GRANT ALL PRIVILEGES ON SCHEMA exprod TO exprod;
-- GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA exprod TO exprod;
-- GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA exprod TO exprod;
-- ALTER ROLE exprod SET search_path = exprod;




CREATE TABLE manager (
    manager_id serial not null primary key,
    firstname varchar(128) not null,
    lastname varchar(128) not null,
    email varchar(128) not null,
    password varchar(256) not null,
    created_on timestamp not null default now()
);

CREATE INDEX manager_email_idx ON manager(email);

INSERT INTO manager (firstname, lastname, email, password)
values ('admin', 'admin', 'admin', '$2y$13$u/yVGgDH0RTgJ5VMedv7seffQiZNs.pH1lVQpoRsY19oUUi2akrpa'),
       ('system', 'system', 'system', '');

CREATE TABLE role (
    role_id serial not null primary key,
    alias varchar(32) not null,
    created_on timestamp not null default now()
);

INSERT INTO role (alias) values ('ADMIN'), ('SALES_TEST');

CREATE TABLE manager_has_role (
    manager_id int not null references manager(manager_id),
    role_id int not null references role(role_id),
    created_on timestamp not null default now()
);
CREATE UNIQUE INDEX manager_has_role_primary on manager_has_role(manager_id, role_id);

INSERT INTO manager_has_role (manager_id, role_id)
values ((select manager_id from manager where email = 'admin'), (select role_id from role where alias = 'ADMIN'));


CREATE TABLE unit (
    unit_id serial not null primary key,
    alias varchar(128) not null,
    moysklad_token varchar(128) not null,
    default_manager_id int not null references manager(manager_id),

    created_on timestamp not null default now()
);

INSERT INTO unit (alias, moysklad_token, default_manager_id) VALUES
        ('test-shop', '86adb04f75a493a941e356d6bc3895f42d63de7f', (SELECT manager_id from manager where email = 'system'));

CREATE TABLE unit_moysklad_id(
    unit_id int not null references unit(unit_id) primary key,
    order_state_new varchar(36) not null,
    order_state_confirmed varchar(36) not null,
    attribute_name varchar(36) not null,
    attribute_instagram_name varchar(36) not null,
    attribute_phone varchar(36) not null,
    attribute_zip varchar(36) not null,
    attribute_city  varchar(36) not null,
    attribute_address  varchar(36) not null,
    attribute_sdek_address  varchar(36) not null,
    attribute_paid  varchar(36) not null,
    attribute_to_pay  varchar(36) not null,
    attribute_delivery_cost varchar(36) not null,
    attribute_manager varchar(36) not null,
    default_counterparty varchar(36) not null,
    default_organization varchar(36) not null,
    default_storage varchar(36) not null
);

INSERT INTO unit_moysklad_id(unit_id,
                             order_state_new, order_state_confirmed,
                             attribute_name, attribute_instagram_name, attribute_phone,
                             attribute_zip, attribute_city, attribute_address,
                             attribute_sdek_address, attribute_paid, attribute_to_pay,
                             attribute_delivery_cost, attribute_manager,
                             default_counterparty, default_organization, default_storage) VALUES
((SELECT unit_id FROM unit WHERE alias = 'test-shop'),
 'de0e5b27-2d6b-11eb-0a80-02f30035f90d', 'de0e5c2f-2d6b-11eb-0a80-02f30035f90e',
 'aca92da4-746f-11eb-0a80-07ad0027d7b9', '7c609881-746f-11eb-0a80-062100266df7', '7c609df0-746f-11eb-0a80-062100266dfa',
 '9c6724e1-746f-11eb-0a80-07ad0027d725', '9c671f47-746f-11eb-0a80-07ad0027d723', '7c609bcd-746f-11eb-0a80-062100266df8',
 '7c609cf9-746f-11eb-0a80-062100266df9', 'aca930ff-746f-11eb-0a80-07ad0027d7ba', '2c2b6b2c-7470-11eb-0a80-03460025c0b8',
 'd5ed31eb-746f-11eb-0a80-00ab0024efc6', '9c6723c8-746f-11eb-0a80-07ad0027d724',
 'cc3f3a0e-3406-11eb-0a80-0964000103cf', 'ddf53945-2d6b-11eb-0a80-02f30035f8e9','ddf69980-2d6b-11eb-0a80-02f30035f8eb');

CREATE TABLE role_has_unit (
    role_id int not null references role(role_id),
    unit_id int not null references unit(unit_id),
    created_on timestamp not null default now()
);
CREATE UNIQUE INDEX role_has_unit_primary on role_has_unit(role_id, unit_id);

INSERT INTO role_has_unit (role_id, unit_id)
VALUES ((select role_id from role where alias = 'ADMIN'), (select unit_id from unit where alias = 'test-shop')),
       ((select role_id from role where alias = 'SALES_TEST'), (select unit_id from unit where alias = 'test-shop'));

CREATE TABLE image_group (
    image_group_id serial not null primary key,
    created_on timestamp not null default now()
);

CREATE TABLE variant (
    variant_id serial not null primary key,
    unit_id int not null references unit(unit_id),
    moysklad_id varchar(36) not null,
    name varchar(1024) not null,
    code varchar(128) not null,
    external_code varchar(128) not null,
    archived boolean not null,
    price decimal(12,2) not null,
    quantity int not null,
    image_group_id int not null references image_group(image_group_id),
    created_on timestamp not null default now()
);
CREATE INDEX variant_unit_id_idx on variant(unit_id);
CREATE UNIQUE INDEX variant_moysklad_id_idx on variant(moysklad_id);

CREATE TABLE image(
    image_id serial not null primary key,
    image_group_id int not null references image_group(image_group_id),
    moysklad_url_hash varchar(36) not null,
    original_path varchar(128) not null,
    thumbnail_path varchar(128) not null,
    created_on timestamp not null default now()
);

CREATE TABLE customer(
    customer_id serial not null primary key,
    name varchar(128) not null,
    phone varchar(32) not null,
    username varchar(64) not null,
    city varchar(32) not null,
    zip varchar(8) not null,
    address varchar(128) not null,
    created_on timestamp not null default now()
);

CREATE TABLE customer_order(
    customer_order_id serial not null primary key,
    moysklad_id varchar(36) not null,
    unit_id int not null references unit(unit_id),
    name varchar(256) not null,
    positions int not null,
    amount decimal(12,2) not null,
    state varchar(32) not null,
    customer_id int not null references customer(customer_id),
    manager_id int not null references manager(manager_id),
    delivery_cost decimal(12,2) not null,
    paid decimal(12,2) not null,
    comment varchar(4096) not null,
    created_on timestamp not null default now()
);

CREATE INDEX order_counterparty_id_idx ON customer_order(customer_id);

CREATE TABLE customer_order_position (
    customer_order_position_id serial not null primary key,
    moysklad_id varchar(36) not null,
    order_id int not null references customer_order(customer_order_id),
    variant_id int not null references variant(variant_id),
    count int not null
);
