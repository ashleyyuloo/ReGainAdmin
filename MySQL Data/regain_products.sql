-- MySQL dump 10.13  Distrib 8.0.36, for macos14 (x86_64)
--
-- Host: localhost    Database: regain
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `seller_id` int NOT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `descript` varchar(255) DEFAULT NULL,
  `weight` float NOT NULL,
  `location` int NOT NULL,
  `category` int NOT NULL,
  `price` double DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `can_deliver` tinyint(1) NOT NULL,
  `bought_price` double DEFAULT NULL,
  `category_name` int DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `seller_to_user_idx` (`seller_id`),
  KEY `product_to_address_idx` (`location`),
  KEY `product_to_category_idx` (`category`),
  KEY `FK3q6bhd2suvi7a6734s3yddjfg` (`category_name`),
  CONSTRAINT `FK3q6bhd2suvi7a6734s3yddjfg` FOREIGN KEY (`category_name`) REFERENCES `category` (`category_id`),
  CONSTRAINT `product_to_address` FOREIGN KEY (`location`) REFERENCES `address` (`address_id`),
  CONSTRAINT `product_to_category` FOREIGN KEY (`category`) REFERENCES `category` (`category_id`),
  CONSTRAINT `product_to_user` FOREIGN KEY (`seller_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,1,'Copper A','I am selling Copper A',10,1,1,5000,NULL,1,NULL,NULL),(2,1,'Iron rod','This is an iron rod',5,2,1,2100,NULL,1,NULL,NULL),(3,2,'Scrap paper','Papers that have been used',2,4,2,200,NULL,0,NULL,NULL);
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-07 17:42:59
