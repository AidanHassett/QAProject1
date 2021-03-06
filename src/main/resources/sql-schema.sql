DROP SCHEMA IF EXISTS ims;

CREATE SCHEMA IF NOT EXISTS ims;

USE ims;

CREATE TABLE IF NOT EXISTS ims.customers (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(321) NOT NULL,
  firstName VARCHAR(64) DEFAULT NULL,
  surname VARCHAR(64) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ims.items (
  id INT NOT NULL AUTO_INCREMENT,
  title VARCHAR(64) DEFAULT NULL,
  price DECIMAL(11, 2) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS ims.orders (
  id INT NOT NULL AUTO_INCREMENT,
  customerId INT NOT NULL,
  timePlaced TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (customerId) REFERENCES customers(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS ims.orderItems (
  orderId INT NOT NULL,
  itemId INT NOT NULL,
  quantity INT NOT NULL DEFAULT 1,
  PRIMARY KEY (orderId, itemId),
  FOREIGN KEY (orderId) REFERENCES orders(id) ON DELETE CASCADE,
  FOREIGN KEY (itemId) REFERENCES items(id)
);
