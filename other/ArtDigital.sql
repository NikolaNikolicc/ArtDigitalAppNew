CREATE SCHEMA IF NOT EXISTS `artdigital`;

DROP TABLE IF EXISTS `artdigital`.`ordertable`;

CREATE TABLE `artdigital`.`ordertable` (
  `orderID` INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  `name` VARCHAR(16),
  `surname` VARCHAR(16),
  `mail` VARCHAR(32),
  `phone` VARCHAR(16),
  `comment` VARCHAR(512),
  `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `postal` VARCHAR(8),
  `city` VARCHAR(16),
  `address` VARCHAR(32),
  `paperBacking` VARCHAR(4),
  `promoCode` varchar(32),
  `payment` varchar(8)
);
