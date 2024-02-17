-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.4.0-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.3.0.6589
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for shop
CREATE DATABASE IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `shop`;

-- Dumping structure for table shop.invoice
CREATE TABLE IF NOT EXISTS `invoice` (
  `invoice_id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`invoice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table shop.invoice: ~0 rows (approximately)

-- Dumping structure for table shop.item
CREATE TABLE IF NOT EXISTS `item` (
  `item_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_info_fk` int(11) DEFAULT NULL,
  `invoice_fk` int(11) DEFAULT NULL,
    `quantity` int(11) DEFAULT NULL,
    `discount_on_bought` int(11) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `item_item_info_fk` (`item_info_fk`),
  KEY `item_invoice` (`invoice_fk`),
  CONSTRAINT `item_invoice` FOREIGN KEY (`invoice_fk`) REFERENCES `invoice` (`invoice_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `item_item_info_fk` FOREIGN KEY (`item_info_fk`) REFERENCES `item_info` (`item_info_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table shop.item: ~0 rows (approximately)

-- Dumping structure for table shop.item_info
CREATE TABLE IF NOT EXISTS `item_info` (
  `item_info_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
    `discount` int(11) DEFAULT 0,
  PRIMARY KEY (`item_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table shop.item_info: ~4 rows (approximately)
INSERT INTO `item_info` (`item_info_id`, `name`, `price`) VALUES
	(1, 'apple', 200),
	(2, 'cucumbers', 120),
	(3, 'beer', 280),
	(4, 'icecream', 50);

-- Dumping structure for table shop.stock
CREATE TABLE IF NOT EXISTS `stock` (
  `stock_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_info_fk` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT 0,
  PRIMARY KEY (`stock_id`),
  KEY `stock_item_fk` (`item_info_fk`) USING BTREE,
  CONSTRAINT `FK_stock_item_info` FOREIGN KEY (`item_info_fk`) REFERENCES `item_info` (`item_info_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Dumping data for table shop.stock: ~4 rows (approximately)
INSERT INTO `stock` (`stock_id`, `item_info_fk`, `quantity`) VALUES
	(1, 1, 200),
	(2, 3, 100),
	(3, 2, 40),
	(4, 4, 20);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
