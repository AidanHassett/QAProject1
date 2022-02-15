DROP SCHEMA ims;

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
  fk_customer INT NOT NULL,
  timePlaced TIMESTAMP,
  PRIMARY KEY (id),
  FOREIGN KEY (fk_customer) REFERENCES customers(id)
);

CREATE TABLE IF NOT EXISTS ims.orderItems (
  fk_order INT NOT NULL,
  fk_item INT NOT NULL,
  quantity INT NOT NULL DEFAULT 1,
  PRIMARY KEY (fk_order, fk_item),
  FOREIGN KEY (fk_order) REFERENCES orders(id),
  FOREIGN KEY (fk_item) REFERENCES items(id)
);
