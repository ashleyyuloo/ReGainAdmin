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
-- Table structure for table `userreports`
--

DROP TABLE IF EXISTS `userreports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userreports` (
  `user_report_ID` int NOT NULL AUTO_INCREMENT,
  `reporter` int NOT NULL,
  `reported_user` int NOT NULL,
  `reason_category` int NOT NULL,
  `report_reply` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `time_stamp` datetime NOT NULL,
  `user_report_status` varchar(255) DEFAULT NULL,
  `report_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_report_ID`),
  KEY `ur_to_rc_idx` (`reason_category`),
  KEY `ur_to_reporter` (`reporter`),
  KEY `ur_to_reported_user` (`reported_user`),
  CONSTRAINT `ur_to_rc` FOREIGN KEY (`reason_category`) REFERENCES `reportcategories` (`report_category_id`),
  CONSTRAINT `ur_to_reported_user` FOREIGN KEY (`reported_user`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ur_to_reporter` FOREIGN KEY (`reporter`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userreports`
--

LOCK TABLES `userreports` WRITE;
/*!40000 ALTER TABLE `userreports` DISABLE KEYS */;
INSERT INTO `userreports` VALUES (1,4,1,1,NULL,'looks suspicious to me','2024-03-06 09:32:00','Pending',NULL),(2,3,2,6,'','cursed','2024-07-10 06:05:00','Pending',NULL),(3,4,2,6,'','said a bad word','2024-07-11 08:05:00','Pending',NULL);
/*!40000 ALTER TABLE `userreports` ENABLE KEYS */;
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
