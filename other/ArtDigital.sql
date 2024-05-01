CREATE SCHEMA IF NOT EXISTS `artdigital`;

DROP TABLE IF EXISTS `artdigital`.`ordertable`;

CREATE TABLE `artdigital`.`ordertable` (
  `orderID` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  `name` VARCHAR(32),
  `surname` VARCHAR(32),
  `mail` VARCHAR(32),
  `phone` VARCHAR(32),
  `comment` VARCHAR(512),
  `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `postal` VARCHAR(32),
  `city` VARCHAR(32),
  `address` VARCHAR(32),
  `paperBacking` VARCHAR(4),
  `promoCode` VARCHAR(32),
  `payment` VARCHAR(8)
);
