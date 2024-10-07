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
-- Table structure for table `eqlistingreports`
--

DROP TABLE IF EXISTS `eqlistingreports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `eqlistingreports` (
  `report_id` int NOT NULL AUTO_INCREMENT,
  `reporter` int NOT NULL,
  `reported_eqlisting` int NOT NULL,
  `reason_category` int NOT NULL,
  `report_reply` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `time_stamp` datetime NOT NULL,
  `report_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  UNIQUE KEY `reported_eqlisting_UNIQUE` (`reported_eqlisting`),
  UNIQUE KEY `reporter_UNIQUE` (`reporter`),
  UNIQUE KEY `reason_category_UNIQUE` (`reason_category`),
  CONSTRAINT `eqlr_to_equipment` FOREIGN KEY (`reported_eqlisting`) REFERENCES `equipments` (`equipment_id`),
  CONSTRAINT `eqlr_to_rc` FOREIGN KEY (`reason_category`) REFERENCES `reportcategories` (`report_category_id`),
  CONSTRAINT `eqlr_to_user` FOREIGN KEY (`reporter`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `eqlistingreports`
--

LOCK TABLES `eqlistingreports` WRITE;
/*!40000 ALTER TABLE `eqlistingreports` DISABLE KEYS */;
INSERT INTO `eqlistingreports` VALUES (1,2,2,5,NULL,'item is so expensive','2024-07-11 12:30:00','In Progress'),(2,1,3,2,'This is to inform you that your account or listing has been reported, and corresponding penalty points will be added to your account as a violation.','location not showing in google maps','2024-07-11 12:30:00','Resolved');
/*!40000 ALTER TABLE `eqlistingreports` ENABLE KEYS */;
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
