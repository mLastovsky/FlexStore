--liquibase formatted sql

--changeset mlastovsky:3
INSERT INTO category (name, description)
VALUES ('Electronics', 'Devices and gadgets'),
       ('Clothing', 'Apparel and accessories'),
       ('Books', 'Literature and learning materials');
--rollback DELETE FROM category WHERE name IN ('Electronics', 'Clothing', 'Books');

--changeset mlastovsky:4
INSERT INTO product (name, description, available_quantity, price, category_id)
VALUES ('Smartphone', 'Latest smartphone with great features', 100, 699.99, 1),
       ('Laptop', 'Powerful laptop for work and gaming', 50, 1299.99, 1),
       ('T-shirt', 'Comfortable cotton t-shirt', 200, 19.99, 2),
       ('Jeans', 'Stylish denim jeans', 150, 49.99, 2),
       ('Novel', 'Engaging fictional novel', 300, 14.99, 3),
       ('Textbook', 'Educational textbook for students', 100, 59.99, 3);
--rollback DELETE FROM product WHERE name IN ('Smartphone', 'Laptop', 'T-shirt', 'Jeans', 'Novel', 'Textbook');
