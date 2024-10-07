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
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chat` (
  `chat_id` int NOT NULL AUTO_INCREMENT,
  `listing_id` int DEFAULT NULL,
  `user_sender_id` int NOT NULL,
  `user_receiver_id` int NOT NULL,
  `message` varchar(2048) NOT NULL,
  `sent_timestamp` datetime NOT NULL,
  `chat_status` varchar(20) NOT NULL,
  `status_timestamp` datetime DEFAULT NULL,
  `image` blob,
  `eqlisting_id` int DEFAULT NULL,
  PRIMARY KEY (`chat_id`),
  UNIQUE KEY `user_sender_id_UNIQUE` (`user_sender_id`),
  UNIQUE KEY `user_receiver_id_UNIQUE` (`user_receiver_id`),
  UNIQUE KEY `listing_id_UNIQUE` (`listing_id`),
  UNIQUE KEY `eqlisting_id_UNIQUE` (`eqlisting_id`),
  CONSTRAINT `chat_to_equipment` FOREIGN KEY (`eqlisting_id`) REFERENCES `equipments` (`equipment_id`),
  CONSTRAINT `chat_to_product` FOREIGN KEY (`listing_id`) REFERENCES `products` (`product_id`),
  CONSTRAINT `chat_to_user1` FOREIGN KEY (`user_sender_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `chat_to_user2` FOREIGN KEY (`user_receiver_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
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
