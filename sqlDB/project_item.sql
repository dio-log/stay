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
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `u_no` int DEFAULT NULL,
  `item_no` int DEFAULT NULL,
  `item_name` char(16) DEFAULT NULL,
  `item_div` char(8) DEFAULT NULL,
  `item_addr` varchar(60) DEFAULT NULL,
  `item_addr_detail` varchar(30) DEFAULT NULL,
  `item_addr_extra` varchar(20) DEFAULT NULL,
  `item_event` varchar(200) DEFAULT NULL,
  `item_imgpath` varchar(400) DEFAULT NULL,
  `item_arround` varchar(400) DEFAULT NULL,
  `item_basic` varchar(400) DEFAULT NULL,
  `item_notice` varchar(400) DEFAULT NULL,
  `item_parking` varchar(400) DEFAULT NULL,
  `item_addpeople` varchar(400) DEFAULT NULL,
  `item_cancel_refund` varchar(400) DEFAULT NULL,
  `item_grade` varchar(4) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1,1,'호텔 바이 메리어트 서울 강남','호텔','서울 강남구 테헤란로25길 10','',' (역삼동)','준비중입니다',',../../img/item/item1/메인1.jpg,../../img/item/item1/메인2.jpg,../../img/item/item1/메인3.jpg,../../img/item/item1/메인4.jpg,../../img/item/item1/메인5.jpg,../../img/item/item1/메인6.jpg','· 강남역 도보 3분','· 체크인 : 15시~ | 체크아웃 : ~11시\r\n· 22시 이후 체크인 시 호텔 프론트 문의\r\n· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 주차 가능 (발렛파킹만 가능 / 박 당 10,000원)',' · 기준인원 외 추가 시, 1인 33,000원 (만 12세 이상~성인), 만 12세 미만 무료\r\n· 영유아 인원수 포함, 최대인원 초과 불가\r\n· 현장 결제','· 체크인일 기준 7일 전 : 100% 환불\r\n· 체크인일 기준 6 ~ 4일 전 : 50% 환불\r\n· 체크인일 기준 3일 전 ~ 당일 및 No-Show : 환불 불가\r\n· 취소, 환불 시 수수료가 발생할 수 있습니다','0');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
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
