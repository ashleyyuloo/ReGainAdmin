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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `role_type` int NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `pass` varchar(128) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `acc_status` varchar(255) DEFAULT NULL,
  `penalty_points` int NOT NULL,
  `commission_balance` double DEFAULT NULL,
  `profile_picture` varbinary(255) DEFAULT NULL,
  `js_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `user_to_role_idx` (`role_type`),
  CONSTRAINT `user_to_role` FOREIGN KEY (`role_type`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,1,'Lactao','Rae','lactaorae','09233453244','pass123','raelactao@gmail.com','Restricted',5,40,NULL,NULL),(2,1,'Que','Simon','simonque','09233453499','123pass','simonque@gmail.com','Active',4,10,NULL,NULL),(3,1,'Huerto','Stan','stanhuerto','09333456554','password','stanhuerto@gmail.com','Active',5,5,NULL,NULL),(4,2,'Yulo','Ashley','ashleyyuloo','09399387804','testingpass','ashyulo@gmail.com','Active',0,12,NULL,'ashley\'s junkshop'),(5,1,'Owens','Nobody','Owens232','09237894321','mypass','johndoe@hotmail.com','Active',0,0,NULL,NULL),(7,2,NULL,NULL,'JJunkshop','09228136769','pwdword',NULL,'Active',0,0,NULL,'Second Junkshop'),(8,2,'Dela Cruz','Juan','HJuan','09175398740','mypassword','juandelacruz@mail.com','Active',0,0,NULL,NULL),(9,2,'Salvador','Mia','MiaJS',NULL,'mypassword',NULL,'Active',0,0,NULL,'Mia\'s Junkshop');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
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
