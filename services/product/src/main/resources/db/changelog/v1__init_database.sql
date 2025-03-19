--liquibase formatted sql

--changeset mlastovsky:1
CREATE TABLE IF NOT EXISTS category
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255),
    description VARCHAR(255)
);
--rollback DROP TABLE category

--changeset mlastovsky:2
CREATE TABLE IF NOT EXISTS product
(
    id                 BIGSERIAL PRIMARY KEY,
    name               VARCHAR(255),
    description        VARCHAR(255),
    available_quantity DOUBLE PRECISION NOT NULL,
    price              NUMERIC(38, 2),
    category_id        BIGINT
        CONSTRAINT fk_category REFERENCES category (id) ON DELETE CASCADE
);
--rollback DROP TABLE product
