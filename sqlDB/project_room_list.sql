-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `room_list`
--

DROP TABLE IF EXISTS `room_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_list` (
  `u_no` int NOT NULL,
  `item_no` int NOT NULL,
  `room_no` int NOT NULL DEFAULT '0',
  `room_name` varchar(20) NOT NULL,
  `room_price` char(10) NOT NULL DEFAULT '0',
  `room_part_price` char(10) DEFAULT NULL,
  `room_part_time` int DEFAULT NULL,
  `room_bed` char(4) DEFAULT NULL,
  `room_bed_cnt` int DEFAULT NULL,
  `room_size` char(8) NOT NULL,
  `room_view` char(16) DEFAULT NULL,
  `room_theme` char(16) DEFAULT NULL,
  `room_extraopt` varchar(200) NOT NULL,
  `basic_men` int NOT NULL DEFAULT '0',
  `max_men` int NOT NULL DEFAULT '0',
  `room_img_path` varchar(300) DEFAULT NULL,
  `room_sts` varchar(2) DEFAULT 't',
  KEY `item_no_fk` (`item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_list`
--

LOCK TABLES `room_list` WRITE;
/*!40000 ALTER TABLE `room_list` DISABLE KEYS */;
INSERT INTO `room_list` VALUES (1,1,1,'슈페리어 더블','87000','2',0,'더블',1,'40','시티','가족','에어컨,TV,냉장고 ,커피포트,욕실용품,드라이기,비데,에어컨,TV,냉장고 ,커피포트,욕실용품,드라이기,비데,',2,2,',../../img/item/item1/룸1-1.jpg,../../img/item/item1/룸1-2.jpg,../../img/item/item1/룸1-3.jpg','y'),(1,1,2,'프리미어 더블','107000','2',0,'퀸',1,'40','시티','가족','에어컨,TV,냉장고 ,커피포트,욕실용품,드라이기,비데,에어컨,TV,냉장고 ,커피포트,욕실용품,드라이기,비데,',2,2,',../../img/item/item1/룸1-1.jpg,../../img/item/item1/룸1-2.jpg,../../img/item/item1/룸1-3.jpg,../../img/item/item1/룸2-1.jpg,../../img/item/item1/룸2-2.jpg','y');
/*!40000 ALTER TABLE `room_list` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-22 18:23:19
