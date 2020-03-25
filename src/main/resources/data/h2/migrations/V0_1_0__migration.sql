/*
 * Engine: H2
 * Version: 0.1.0
 * Description:
 *   Database changes for version 0.1.0
 */

/*
 * Structure
 */

ALTER TABLE product
    ADD description varchar(255);

/*
 * Data
 */

UPDATE product SET description = 'description';