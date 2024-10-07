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
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booking` (
  `booking_id` int NOT NULL AUTO_INCREMENT,
  `equipment` int NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `rentee_id` int NOT NULL,
  `is_accepted` tinyint(1) NOT NULL,
  `equipment_id` int DEFAULT NULL,
  `renter_id` int DEFAULT NULL,
  `address_id` int NOT NULL,
  `barangay` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `unit_number` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`booking_id`),
  UNIQUE KEY `rentee_id_UNIQUE` (`rentee_id`),
  KEY `booking_to_equipment_idx` (`equipment`),
  KEY `FKerawy5lm1od206jvv8th4imyk` (`equipment_id`),
  KEY `FK96n30cdtf7fyclhd4es2jmbqm` (`renter_id`),
  CONSTRAINT `booking_to_equipment` FOREIGN KEY (`equipment`) REFERENCES `equipments` (`equipment_id`),
  CONSTRAINT `booking_to_user` FOREIGN KEY (`rentee_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FK96n30cdtf7fyclhd4es2jmbqm` FOREIGN KEY (`renter_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKerawy5lm1od206jvv8th4imyk` FOREIGN KEY (`equipment_id`) REFERENCES `equipments` (`equipment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booking`
--

LOCK TABLES `booking` WRITE;
/*!40000 ALTER TABLE `booking` DISABLE KEYS */;
INSERT INTO `booking` VALUES (1,3,'2024-07-12','2024-07-16',4,1,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0),(2,1,'2024-07-11','2024-07-13',1,1,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0),(3,1,'2024-06-12','2024-06-13',3,1,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `booking` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-07 17:42:58
