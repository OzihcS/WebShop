DROP DATABASE IF EXISTS BeerShopDB;

CREATE DATABASE IF NOT EXISTS BeerShopDB;

USE BeerShopDB;

CREATE TABLE IF NOT EXISTS user (
  id         INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(25) NOT NULL,
  last_name  VARCHAR(25) NOT NULL,
  email      VARCHAR(45) NOT NULL,
  password   VARCHAR(25) NOT NULL,
  image      VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS product (
  id           INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name         VARCHAR(25) NOT NULL,
  category     VARCHAR(25) NOT NULL,
  description  VARCHAR(75) NOT NULL,
  price        REAL        NOT NULL,
  manufacturer VARCHAR(25) NOT NULL,
  image        VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS `order` (
  id              INT PRIMARY KEY AUTO_INCREMENT                                            NOT NULL,
  status          ENUM ('ACCEPTED', 'CONFIRMED', 'FORMING', 'SENT', 'COMPLETE', 'CANCELED') NOT NULL,
  statusDetailing VARCHAR(50),
  date            DATETIME                                                                  NOT NULL,
  user_id         INT                                                                       NOT NULL
);

CREATE TABLE IF NOT EXISTS products_order (
  order_id   INT NOT NULL,
  product_id INT NOT NULL,
  quantity   INT NOT NULL DEFAULT 0,
  PRIMARY KEY (order_id, product_id)
);
