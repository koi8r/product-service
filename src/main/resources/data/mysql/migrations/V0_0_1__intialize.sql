/*
 * Engine: MySQL
 * Version: 0.0.1
 * Description: Initial database structure and data.
 */

/*
 * Structure
 */

CREATE TABLE product (
    `id` bigint(20) unsigned NOT NULL auto_increment,
    `name` varchar(100) NOT NULL,
    `current_price` decimal(10,2) NOT NULL,
    `last_update` datetime NOT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT `uq_product_name` UNIQUE (`name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*
 * Data
 */

INSERT INTO product (name, current_price, last_update) VALUES ('Apple EarPods', '16.44', NOW());
INSERT INTO product (name, current_price, last_update) VALUES ('Apple Macbook Air (2019) MVFK2', '1005.88', NOW());
INSERT INTO product (name, current_price, last_update) VALUES ('BenQ TK800M 4K DLP Beamer', '1110', NOW());
INSERT INTO product (name, current_price, last_update) VALUES ('LG PH550G HD Mini Beamer', '369', NOW());
INSERT INTO product (name, current_price, last_update) VALUES ('Canon EOS 2000D', '375.99', NOW());