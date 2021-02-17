-- CREATE SCHEMA exprod;
-- SET SEARCH_PATH = exprod;
-- CREATE ROLE exprod PASSWORD 'exprod' LOGIN;

-- GRANT ALL PRIVILEGES ON SCHEMA exprod TO exprod;
-- GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA exprod TO exprod;
-- GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA exprod TO exprod;
-- ALTER ROLE exprod SET search_path = exprod;




CREATE TABLE manager (
    manager_id serial not null primary key,
    manager_firstname varchar(128) not null,
    manager_lastname varchar(128) not null,
    email varchar(128) not null,
    password varchar(256) not null,
    created_on timestamp not null default now()
);

CREATE TABLE role (
    role_id serial not null primary key,
    alias varchar(32) not null,
    created_on timestamp not null default now()
);

INSERT INTO role (alias) values ('ADMIN'), ('SALES_NEZAMETNO');

CREATE TABLE manager_has_role (
    manager_id int not null references manager(manager_id),
    role_id int not null references role(role_id),
    created_on timestamp not null default now()
);
CREATE UNIQUE INDEX manager_has_role_primary on manager_has_role(manager_id, role_id);

CREATE TABLE unit (
    unit_id serial not null primary key,
    alias varchar(128) not null,
    store_login varchar(128) not null,
    store_password varchar(128) not null,
    created_on timestamp not null default now()
);

INSERT INTO unit (alias, store_login, store_password) VALUES ('nezametno-shop', '', '');

CREATE TABLE role_has_unit (
    role_id int not null references role(role_id),
    unit_id int not null references unit(unit_id),
    created_on timestamp not null default now()
);
CREATE UNIQUE INDEX role_has_unit_primary on role_has_unit(role_id, unit_id);

INSERT INTO role_has_unit (role_id, unit_id)
VALUES ((select role_id from role where alias = 'ADMIN'), (select unit_id from unit where alias = 'nezametno-shop')),
       ((select role_id from role where alias = 'SALES_NEZAMETNO'), (select unit_id from unit where alias = 'nezametno-shop'));
