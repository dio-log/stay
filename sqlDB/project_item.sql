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
  `item_name` char(40) DEFAULT NULL,
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
INSERT INTO `item` VALUES (1,2,'더 디자이너스 리즈 강남 프리미어','호텔','서울 강남구 봉은사로 113','',' (논현동)','준비중입니다',',../../img/item/item1/m5743fc7f3803a.jpg,../../img/item/item1/m5743fc51c9d2b.jpg,../../img/item/item1/m5743fc730e885.jpg,../../img/item/item1/m5743fc905cd01.jpg,../../img/item/item1/m5743fccb614f7.jpg','· 신논현역 도보 1분\r\n· 강남역 도보 5분','· 체크인 : 15시~ | 체크아웃 : ~11시\r\n· 22시 이후 체크인 시 호텔 프론트 문의\r\n· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 주차 가능 (발렛파킹만 가능 / 박 당 10,000원)','· 기준인원 외 추가 시, 1인 33,000원 (만 12세 이상~성인), 만 12세 미만 무료\r\n· 영유아 인원수 포함, 최대인원 초과 불가\r\n· 현장 결제','· 체크인일 기준 7일 전 : 100% 환불\r\n· 체크인일 기준 6 ~ 4일 전 : 50% 환불\r\n· 체크인일 기준 3일 전 ~ 당일 및 No-Show : 환불 불가\r\n· 취소, 환불 시 수수료가 발생할 수 있습니다','0'),(1,3,'호텔 인 나인 강남','호텔','서울 강남구 영동대로 618','',' (삼성동)','준비중입니다',',../../img/item/item1/m5f1fef6ac910a.jpg,../../img/item/item1/m5f1fef6be2eb8.jpg,../../img/item/item1/m5f1fef6e8bdf3.jpg,../../img/item/item1/m5f1fef81b2715.jpg,../../img/item/item1/m5f1fef8232c3e.jpg,../../img/item/item1/m5f44c7eb31023.jpg','· 신논현역 도보 1분\r\n· 강남역 도보 5분','· 체크인 : 15시~ | 체크아웃 : ~11시\r\n· 22시 이후 체크인 시 호텔 프론트 문의\r\n· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 주차 가능 (발렛파킹만 가능 / 박 당 10,000원)','· 기준인원 외 추가 시, 1인 33,000원 (만 12세 이상~성인), 만 12세 미만 무료\r\n· 영유아 인원수 포함, 최대인원 초과 불가\r\n· 현장 결제','· 체크인일 기준 7일 전 : 100% 환불\r\n· 체크인일 기준 6 ~ 4일 전 : 50% 환불\r\n· 체크인일 기준 3일 전 ~ 당일 및 No-Show : 환불 불가\r\n· 취소, 환불 시 수수료가 발생할 수 있습니다','0'),(1,4,'노보텔 앰배서더 서울 강남','호텔','서울 강남구 봉은사로 130','',' (역삼동)','준비중입니다',',../../img/item/item1/m5e994464f3d0d.jpg,../../img/item/item1/m595b4f6cec59f.jpg,../../img/item/item1/m5743fa2f96ac8.jpg,../../img/item/item1/m5743fa8baf934.jpg,../../img/item/item1/m5743fa5688a64.jpg,../../img/item/item1/m576735f55e25b.jpg','· 신논현역 도보 1분\r\n· 강남역 도보 5분','· 체크인 : 15시~ | 체크아웃 : ~11시\r\n· 22시 이후 체크인 시 호텔 프론트 문의\r\n· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 주차 가능 (발렛파킹만 가능 / 박 당 10,000원)','· 기준인원 외 추가 시, 1인 33,000원 (만 12세 이상~성인), 만 12세 미만 무료\r\n· 영유아 인원수 포함, 최대인원 초과 불가\r\n· 현장 결제','· 체크인일 기준 7일 전 : 100% 환불\r\n· 체크인일 기준 6 ~ 4일 전 : 50% 환불\r\n· 체크인일 기준 3일 전 ~ 당일 및 No-Show : 환불 불가\r\n· 취소, 환불 시 수수료가 발생할 수 있습니다','0'),(1,5,'역삼 아르누보씨티 호텔앤레지던스','호텔','서울 강남구 언주로 506','',' (역삼동)','준비중입니다',',../../img/item/item1/m61e7b76d9ed1b.jpg,../../img/item/item1/m620cb2d548cc1.jpg,../../img/item/item1/m620cb4f802c65.jpg,../../img/item/item1/m620cb40f4e81d.jpg,../../img/item/item1/m620cb4139fc01.jpg,../../img/item/item1/m620cb335112cd.jpg','· 신논현역 도보 1분\r\n· 강남역 도보 5분','· 체크인 : 15시~ | 체크아웃 : ~11시\r\n· 22시 이후 체크인 시 호텔 프론트 문의\r\n· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 주차 가능 (발렛파킹만 가능 / 박 당 10,000원)','· 기준인원 외 추가 시, 1인 33,000원 (만 12세 이상~성인), 만 12세 미만 무료\r\n· 영유아 인원수 포함, 최대인원 초과 불가\r\n· 현장 결제','· 체크인일 기준 7일 전 : 100% 환불\r\n· 체크인일 기준 6 ~ 4일 전 : 50% 환불\r\n· 체크인일 기준 3일 전 ~ 당일 및 No-Show : 환불 불가\r\n· 취소, 환불 시 수수료가 발생할 수 있습니다','0'),(1,6,'조선 팰리스 서울 강남 럭셔리 컬렉션','호텔','서울 강남구 테헤란로 231','',' (역삼동)','준비중입니다',',../../img/item/item1/m61c941b09383b.jpg,../../img/item/item1/m61c941bb068b0.jpg,../../img/item/item1/m61c941d3089da.jpg,../../img/item/item1/m61c942a7a2f40.jpg,../../img/item/item1/m61c942566f930.jpg,../../img/item/item1/m61c9485177c56.jpg,../../img/item/item1/m61c94360820fc.jpg,../../img/item/item1/m61c9471223352.jpg','· 신논현역 도보 1분\r\n· 강남역 도보 5분','· 체크인 : 15시~ | 체크아웃 : ~11시\r\n· 22시 이후 체크인 시 호텔 프론트 문의\r\n· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 주차 가능 (발렛파킹만 가능 / 박 당 10,000원)','· 기준인원 외 추가 시, 1인 33,000원 (만 12세 이상~성인), 만 12세 미만 무료\r\n· 영유아 인원수 포함, 최대인원 초과 불가\r\n· 현장 결제','· 체크인일 기준 7일 전 : 100% 환불\r\n· 체크인일 기준 6 ~ 4일 전 : 50% 환불\r\n· 체크인일 기준 3일 전 ~ 당일 및 No-Show : 환불 불가\r\n· 취소, 환불 시 수수료가 발생할 수 있습니다','0'),(1,7,' 포포인츠 바이 쉐라톤 서울 강남','호텔','서울 강남구 도산대로 203','',' (신사동)','준비중입니다',',../../img/item/item1/m5f364a7c5f563.jpg,../../img/item/item1/m5f364b2e638ad.jpg,../../img/item/item1/m61c5268758dd3.jpg,../../img/item/item1/m607faa322bbcc.jpg,../../img/item/item1/m607fab0dd64fb.jpg','· 신논현역 도보 1분\r\n· 강남역 도보 5분','· 체크인 : 15시~ | 체크아웃 : ~11시\r\n· 22시 이후 체크인 시 호텔 프론트 문의\r\n· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 보안을 위하여 투숙객 본인 외에 외부인 동반 출입을 금지합니다\r\n· 전 객실 금연 (흡연 적발 시 300,000원 위약금이 발생됩니다)\r\n· 애완동물 출입 금지입니다\r\n· 시설물을 파손하거나 침구류 훼손 및 오염 (세탁 불가능) 시 전액 배상해야 합니다\r\n· 보호자 동반 없는 미성년자 입실 불가 (업체 문의 필수)','· 주차 가능 (발렛파킹만 가능 / 박 당 10,000원)','· 기준인원 외 추가 시, 1인 33,000원 (만 12세 이상~성인), 만 12세 미만 무료\r\n· 영유아 인원수 포함, 최대인원 초과 불가\r\n· 현장 결제','· 체크인일 기준 7일 전 : 100% 환불\r\n· 체크인일 기준 6 ~ 4일 전 : 50% 환불\r\n· 체크인일 기준 3일 전 ~ 당일 및 No-Show : 환불 불가\r\n· 취소, 환불 시 수수료가 발생할 수 있습니다','0');
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

-- Dump completed on 2022-08-24 18:25:53
