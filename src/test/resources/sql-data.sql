INSERT INTO ims.customers (email, firstName, surname)
VALUES ('jharrison@qa.com', 'Jordan', 'Harrison');

INSERT INTO items (title, price)
VALUES ('Olives', 12.86);

INSERT INTO orders (customerId, timePlaced)
VALUES (1, '1970-01-01 01:00:00');

INSERT INTO orderItems (orderId, itemId, quantity)
VALUES (1, 1, 1);
