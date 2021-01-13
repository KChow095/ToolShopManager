-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: toolshop
-- ------------------------------------------------------
-- Server version	8.0.21

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `CustomerId` int NOT NULL,
  `FName` varchar(20) NOT NULL,
  `LName` varchar(20) NOT NULL,
  `CustomerType` char(1) NOT NULL,
  `PhoneNumber` bigint DEFAULT NULL,
  `PostalCode` varchar(7) DEFAULT NULL,
  `Address` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CustomerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Tim','Tam','R',4033541029,'T3G4Q1',' 123 Sesame St. NE'),(2,'Jim','Tam','C',4035541029,'T3G5Q1',' 124 Sesame St. NE'),(3,'Jim','Jam','R',4033541129,'E3G4Q1',' 155 Baker St. SW'),(4,'Jim','Tam','C',4133541129,'E3G4Q4',' 165 Baker St. SW'),(5,'Kim','Kam','R',4035540029,'T3G9Q1',' 421 Sesame St. SE'),(6,'Kanye','West','C',4035522029,'T3L5Q1',' 14 Sesame St. NE'),(7,'Kanye','East','R',4035332029,'T3L5Q5',' 175 Baker St. SW'),(8,'Kanye','North','C',7035332029,'T3L5L5',' 195 Baker St. SW'),(9,'Kanye','South','R',7032342029,'T3L7L5',' 205 Baker St. SW'),(10,'Donald','Thump','C',7036942029,'T3L7L9',' 1 White Ave. NW'),(99,'Joe','Bison','C',7036942029,'T3L7L9',' 1 White Ave. NW'),(100,'Mike','Fence','C',7036942029,'T3L7L9',' 1 White Ave. NW'),(101,'Jimmy','Tam','C',4133541129,'E3G4Q4',' 165 Baker St. SW');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electrical`
--

DROP TABLE IF EXISTS `electrical`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `electrical` (
  `ItemId` int NOT NULL,
  `PowerType` varchar(20) NOT NULL,
  PRIMARY KEY (`ItemId`),
  CONSTRAINT `electrical_ibfk_1` FOREIGN KEY (`ItemId`) REFERENCES `item` (`ItemId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electrical`
--

LOCK TABLES `electrical` WRITE;
/*!40000 ALTER TABLE `electrical` DISABLE KEYS */;
INSERT INTO `electrical` VALUES (1000,'AC'),(1001,'DC'),(1002,'AC'),(1003,'DC'),(1004,'AC'),(1005,'DC'),(1006,'AC'),(1007,'DC'),(1008,'AC'),(1009,'DC'),(1010,'AC'),(1011,'DC'),(1012,'AC'),(1013,'DC'),(8888,'DC'),(99999,'DC');
/*!40000 ALTER TABLE `electrical` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `international`
--

DROP TABLE IF EXISTS `international`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `international` (
  `SupplierId` int NOT NULL,
  `ImportTax` decimal(3,2) NOT NULL,
  PRIMARY KEY (`SupplierId`),
  CONSTRAINT `international_ibfk_1` FOREIGN KEY (`SupplierId`) REFERENCES `supplier` (`SupplierId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `international`
--

LOCK TABLES `international` WRITE;
/*!40000 ALTER TABLE `international` DISABLE KEYS */;
INSERT INTO `international` VALUES (13,0.20),(8001,0.10),(8003,0.30),(8004,0.40),(8005,0.50);
/*!40000 ALTER TABLE `international` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `item` (
  `ItemId` int NOT NULL,
  `ItemName` varchar(20) NOT NULL,
  `ItemType` char(2) NOT NULL,
  `QuantityInStock` int NOT NULL,
  `Price` double(5,2) NOT NULL,
  `SupplierId` int DEFAULT NULL,
  PRIMARY KEY (`ItemId`),
  KEY `SupplierId` (`SupplierId`),
  CONSTRAINT `item_ibfk_1` FOREIGN KEY (`SupplierId`) REFERENCES `supplier` (`SupplierId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (1000,'Knock Bits','E',86,9.99,8009),(1001,'Widgets','E',7,35.50,8004),(1002,'Grommets','E',16,23.45,8001),(1003,'Wedges','E',10,10.15,8020),(1004,'Wing Bats','E',9,11.25,8003),(1005,'Twinkies','E',75,15.75,8012),(1006,'Wiggles','E',28,12.34,8020),(1007,'Bing Bobs','E',25,2.39,8005),(1008,'Wog Wits','E',300,19.99,8004),(1009,'Barney Bits','E',21,23.59,8006),(1010,'Willie Widgits','E',89,12.99,8003),(1011,'Barge Bogs','E',35,2.99,8011),(1012,'Poggy Pogs','E',99,1.10,13),(1013,'Pardle Pins','E',400,0.69,8013),(1014,'Piddley Wicks','N',54,5.19,8013),(1015,'Iggy Orks','N',21,49.95,8010),(1016,'Crank Cribs','N',888,0.29,8005),(1017,'Thingies','N',67,45.98,8008),(1018,'Orf Dappers','N',32,19.98,8018),(1019,'Piff Knocks','N',82,4.95,13),(1020,'Knit Piks','N',66,6.75,8015),(1021,'Piddley Pins','N',370,0.25,8020),(1022,'Tic Tocs','N',87,1.36,8014),(1023,'Tin Wits','N',23,5.67,8014),(1024,'Thinga-ma-bobs','N',40,10.98,8012),(1025,'Fling Flobs','N',254,2.33,8009),(1026,'Barn Bins','N',45,88.67,8006),(1027,'Flap Wrappers','N',89,44.88,8009),(1028,'Pong Pangs','N',2345,0.10,13),(1029,'Oof Tongs','N',345,8.49,8011),(1030,'Nic Nacs','N',238,2.99,8015),(1031,'Tork Tins','N',376,0.95,8012),(1032,'Lilly Larks','N',56,12.99,8007),(1033,'Minnie Morks','N',800,1.95,8007),(1034,'Cork Handles','N',654,2.66,8016),(1035,'Ding Darns','N',1208,0.15,8019),(1036,'Erk Orks','N',498,3.99,8017),(1037,'Foo Figs','N',234,5.89,8018),(1038,'Googly Eyes','N',756,6.99,8001),(1039,'Handy Pandies','N',321,4.35,8017),(1040,'Inny Outies','N',219,3.45,8010),(8888,'Barney Bits','E',16,23.59,8006),(99999,'Twinkies','E',75,15.75,8012);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordering`
--

DROP TABLE IF EXISTS `ordering`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordering` (
  `OrderId` int NOT NULL,
  `OrderDate` datetime NOT NULL,
  PRIMARY KEY (`OrderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordering`
--

LOCK TABLES `ordering` WRITE;
/*!40000 ALTER TABLE `ordering` DISABLE KEYS */;
INSERT INTO `ordering` VALUES (3073,'2020-11-30 22:00:53'),(7091,'2020-11-28 17:37:22'),(12345,'2020-11-24 00:00:00'),(19283,'2020-11-24 00:00:00'),(19312,'2020-11-29 00:22:04'),(21342,'2020-11-29 11:26:46'),(28250,'2020-11-29 00:33:07'),(31479,'2020-11-29 23:52:48'),(54968,'2020-12-01 00:55:06'),(55358,'2020-11-28 22:09:35'),(61864,'2020-12-01 11:39:26'),(68381,'2020-11-30 00:04:45'),(69616,'2020-11-30 00:12:03'),(76713,'2020-11-30 21:33:18'),(83179,'2020-11-28 17:46:57'),(85425,'2020-11-30 00:17:00'),(88319,'2020-11-28 17:33:06');
/*!40000 ALTER TABLE `ordering` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderline`
--

DROP TABLE IF EXISTS `orderline`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderline` (
  `OrderId` int NOT NULL,
  `ItemId` int NOT NULL,
  `SupplierId` int NOT NULL,
  `OrderQuantity` int NOT NULL,
  PRIMARY KEY (`OrderId`,`ItemId`,`SupplierId`),
  KEY `ItemId` (`ItemId`),
  KEY `SupplierId` (`SupplierId`),
  CONSTRAINT `orderline_ibfk_1` FOREIGN KEY (`ItemId`) REFERENCES `item` (`ItemId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderline_ibfk_2` FOREIGN KEY (`OrderId`) REFERENCES `ordering` (`OrderId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orderline_ibfk_3` FOREIGN KEY (`SupplierId`) REFERENCES `supplier` (`SupplierId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderline`
--

LOCK TABLES `orderline` WRITE;
/*!40000 ALTER TABLE `orderline` DISABLE KEYS */;
INSERT INTO `orderline` VALUES (7091,1003,8020,38),(19283,1001,8004,6),(19312,8888,8006,30),(21342,1004,8003,40),(28250,1003,8020,40),(31479,1002,8001,32),(54968,1006,8020,22),(55358,1003,8020,39),(61864,1001,8004,43),(68381,1004,8003,41),(69616,1002,8001,33),(76713,1002,8001,34),(83179,1006,8020,21),(85425,8888,8006,34),(88319,1003,8020,37);
/*!40000 ALTER TABLE `orderline` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchases`
--

DROP TABLE IF EXISTS `purchases`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `purchases` (
  `CustomerId` int NOT NULL,
  `ItemId` int NOT NULL,
  `PurchaseDate` datetime NOT NULL,
  PRIMARY KEY (`CustomerId`,`ItemId`),
  KEY `ItemId` (`ItemId`),
  CONSTRAINT `purchases_ibfk_1` FOREIGN KEY (`CustomerId`) REFERENCES `customer` (`CustomerId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `purchases_ibfk_2` FOREIGN KEY (`ItemId`) REFERENCES `item` (`ItemId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchases`
--

LOCK TABLES `purchases` WRITE;
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` VALUES (2,1001,'2020-11-11 11:11:11');
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `supplier`
--

DROP TABLE IF EXISTS `supplier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `supplier` (
  `SupplierId` int NOT NULL,
  `CompanyName` varchar(100) NOT NULL,
  `SupplierType` char(2) NOT NULL,
  `SalesContact` varchar(20) NOT NULL,
  `PhoneNumber` bigint DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SupplierId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `supplier`
--

LOCK TABLES `supplier` WRITE;
/*!40000 ALTER TABLE `supplier` DISABLE KEYS */;
INSERT INTO `supplier` VALUES (13,'Pong Works','I','Bart',4031234561,'749 Dufferin Blvd., SE, Calgary'),(8001,'Grommet Builders','I','Fred',4031234567,'788 30th St., SE, Calgary'),(8003,'Wiley Inc.','I','BillyBob',4031234671,'26 40th St., SE, Calgary'),(8004,'Winork Manufacturing Inc.','I','Marty',4031234781,'156 51st Ave., SE, Calgary'),(8005,'Grab Bag Inc.','I','Monty',4031234891,'138 42nd Ave., NE, Calgary'),(8006,'Paddy\'s Manufacturing','L','Barney',4031235001,'311 McCall Way, NE, Calgary'),(8007,'Smickles Industries','L','Lisa',4031235111,'966 Smed Lane, SE, Calgary'),(8008,'Thangs Inc.','L','Thelma',4031235221,'879 37th Ave., NE, Calgary'),(8009,'Flip Works Inc.','L','Rory',4031235331,'472 Ogden Dale Rd., SE, Calgary'),(8010,'Irkle Industries','L','Irma',4031235441,'754 Sunridge Way, NE, Calgary'),(8011,'Biff Builders','L','Borjn',4031235551,'690 19th St., NE, Calgary'),(8012,'Twinkles Inc.','L','Barkley',4031235661,'318 29th St.,NE, Calgary'),(8013,'Piddles Industries','L','Parnell',4031235771,'238 112th Ave., NE, Calgary'),(8014,'Tic Tac Manufacturing','L','Teisha',4031235881,'598 Palmer Rd., NE, Calgary'),(8015,'Knock Knock Inc.','L','Ned',4031235991,'363 42nd Ave., NE, Calgary'),(8016,'Corky Things Inc.','L','Corrine',4031236101,'35 McCall Way, NE, Calgary'),(8017,'E & O Industries','L','Stan',4031236211,'883 44th St., SE, Calgary'),(8018,'Fiddleys Makes Stuff Inc.','L','Fredda',4031236321,'350 27th St., NE, Calgary'),(8019,'Horks and Stuff Manufacturing','L','Harold',4031236431,'997 42nd Ave., NE, Calgary'),(8020,'Wings Works','L','Wing',4031236541,'754 48th St., SE, Calgary');
/*!40000 ALTER TABLE `supplier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'toolshop'
--

--
-- Dumping routines for database 'toolshop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-01-12 21:29:36
