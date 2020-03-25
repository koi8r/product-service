/*
 * Structure
 */

DROP TABLE IF EXISTS product;

CREATE TABLE product (
    `id` bigint(20) unsigned NOT NULL auto_increment,
    `name` varchar(100) NOT NULL,
    `current_price` decimal(10,2) NOT NULL,
    `last_update` datetime NOT NULL,
    PRIMARY KEY(`id`),
    CONSTRAINT `uq_product_name` UNIQUE (`name`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
