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
-- Table structure for table `listingreports`
--

DROP TABLE IF EXISTS `listingreports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `listingreports` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `reporter` int NOT NULL,
  `reported_listing` int NOT NULL,
  `reason_category` int NOT NULL,
  `report_reply` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `time_stamp` datetime NOT NULL,
  `report_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `lr_to_user` (`reporter`),
  KEY `lr_to_product` (`reported_listing`),
  KEY `lr_to_rc` (`reason_category`),
  CONSTRAINT `lr_to_product` FOREIGN KEY (`reported_listing`) REFERENCES `products` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lr_to_rc` FOREIGN KEY (`reason_category`) REFERENCES `reportcategories` (`report_category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `lr_to_user` FOREIGN KEY (`reporter`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listingreports`
--

LOCK TABLES `listingreports` WRITE;
/*!40000 ALTER TABLE `listingreports` DISABLE KEYS */;
INSERT INTO `listingreports` VALUES (1,1,3,2,'NULL','location not showing in google maps','2024-07-09 11:30:00','Pending'),(3,4,1,4,'This is to inform you that your account or listing has been reported, and corresponding penalty points will be added to your account as a violation.','item is prohibited','2024-04-02 08:30:00','Resolved'),(10,4,1,4,NULL,'item is prohibited','2024-04-02 08:30:00','Pending'),(11,2,3,2,NULL,'location not showing in google maps','2024-07-09 11:30:00','Pending');
/*!40000 ALTER TABLE `listingreports` ENABLE KEYS */;
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
