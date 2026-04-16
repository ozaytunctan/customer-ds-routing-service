CREATE TABLE app_live.customers
(
    id         UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    first_name VARCHAR(100) NOT NULL,
    last_name  VARCHAR(150) NOT NULL,
    email      VARCHAR(200) NOT NULL
);

ALTER TABLE users
    ADD CONSTRAINT uk_users_email UNIQUE (email);