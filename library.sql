-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.40-log

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `UserName` varchar(20) NOT NULL,
  `AccountNumber` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `Identity` varchar(3) NOT NULL,
  UNIQUE KEY `UserName_UNIQUE` (`UserName`),
  UNIQUE KEY `AccountNumber_UNIQUE` (`AccountNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('1','1','1','A'),('2','22','22','F'),('333','333','333','S'),('Cordy','cordy20030416','Cordy@920416','S'),('鄧同恩','cordy','cordy','S'),('鄧鄧','DengDeng','Deng','F');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booklist`
--

DROP TABLE IF EXISTS `booklist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `booklist` (
  `BookName` varchar(50) NOT NULL,
  `ISBN` varchar(50) NOT NULL,
  `Author` varchar(30) NOT NULL,
  `Publisher` varchar(30) NOT NULL,
  `Status` varchar(1) NOT NULL,
  UNIQUE KEY `ISBN_UNIQUE` (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booklist`
--

LOCK TABLES `booklist` WRITE;
/*!40000 ALTER TABLE `booklist` DISABLE KEYS */;
INSERT INTO `booklist` VALUES ('C how to program','001','cordy','cordy.c','1'),('C++ how to program','002','cordy','cordy.c++','0'),('Java how to program','003','cordy','cordy.java','1'),('Python how to program','004','cordy','cordy.python','1'),('會計學','005','會計師',' 會計師事務所','1'),('統計學','006','數據分析師',' 數據分析公司','1'),('經濟學','007','經濟學家',' 經濟公司','1'),('Unix作業系統','008','作業系統工程師',' 作業系統開發商','1'),('企業概論','009','企業家',' 大企業','1'),('微積分','010','微積分學家',' 微積分有限公司','1'),('計算機概論','011','計算機工程師','計算機有限公司','1'),('zzz','012','zzz','zzz','1'),('zxc','013','zxc','zxc','1');
/*!40000 ALTER TABLE `booklist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record`
--

DROP TABLE IF EXISTS `record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `record` (
  `BookName` varchar(50) COLLATE utf8_bin NOT NULL,
  `Borrower` varchar(50) COLLATE utf8_bin NOT NULL,
  `BorrowDate` datetime NOT NULL,
  `ReturnDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record`
--

LOCK TABLES `record` WRITE;
/*!40000 ALTER TABLE `record` DISABLE KEYS */;
INSERT INTO `record` VALUES ('C how to program','2','2023-01-05 17:07:50','2023-01-06 21:42:15'),('Python how to program','333','2023-01-05 17:08:04','2023-01-06 23:15:51'),('C how to program','333','2023-01-06 21:45:17','2023-01-06 21:55:02'),('a','2','2023-01-06 21:46:30','2023-01-06 21:46:41'),('c','333','2023-01-06 23:16:14','2023-01-10 14:57:21'),('會計學','鄧鄧','2023-01-10 14:47:00','2023-01-10 14:53:36'),('Python how to program','鄧鄧','2023-01-10 14:54:09','2023-01-10 14:54:43'),('計算機概論','鄧鄧','2023-01-10 15:08:49','2023-01-10 15:10:16'),('C++ how to program','Cordy','2023-01-10 15:11:18',NULL);
/*!40000 ALTER TABLE `record` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-27 10:47:40
