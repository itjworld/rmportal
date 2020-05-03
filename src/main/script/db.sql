-- MySQL dump 10.13  Distrib 5.7.24, for macos10.14 (x86_64)
--
-- Host: localhost    Database: rmportal
-- ------------------------------------------------------
-- Server version	5.7.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `rmportal`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `rmportal` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `rmportal`;

--
-- Table structure for table `RM_ADDRESS_INFO`
--

DROP TABLE IF EXISTS `RM_ADDRESS_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RM_ADDRESS_INFO` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTACT` varchar(255) NOT NULL,
  `CONTENT` varchar(255) DEFAULT NULL,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `STREET1` varchar(255) NOT NULL,
  `STREET2` varchar(255) DEFAULT NULL,
  `UPDATED_DATETIME` datetime DEFAULT NULL,
  `LOCATION` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKatlgablt5oxpq5h3a5hmn543j` (`LOCATION`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RM_ADDRESS_INFO`
--

LOCK TABLES `RM_ADDRESS_INFO` WRITE;
/*!40000 ALTER TABLE `RM_ADDRESS_INFO` DISABLE KEYS */;
INSERT INTO `RM_ADDRESS_INFO` VALUES (3,'9717700911',NULL,'2018-05-28 17:47:40','wavespgrooms@gmail.com','Waves PG','H.No. 874, Saraswati Vihar','Near Sahara Mall','2018-05-28 17:47:40',4);
/*!40000 ALTER TABLE `RM_ADDRESS_INFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RM_ELECTRICITY`
--

DROP TABLE IF EXISTS `RM_ELECTRICITY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RM_ELECTRICITY` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  `CURRENT_READING` bigint(20) DEFAULT NULL,
  `LAST_READING` bigint(20) DEFAULT NULL,
  `UNIT_RATE` int(11) DEFAULT NULL,
  `UPDATED_DATETIME` datetime DEFAULT NULL,
  `MAPPING_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKj00irk9w5ldn1urdxrj3bq1w4` (`MAPPING_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RM_ELECTRICITY`
--

LOCK TABLES `RM_ELECTRICITY` WRITE;
/*!40000 ALTER TABLE `RM_ELECTRICITY` DISABLE KEYS */;
/*!40000 ALTER TABLE `RM_ELECTRICITY` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RM_GUEST_PAYMENT`
--

DROP TABLE IF EXISTS `RM_GUEST_PAYMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RM_GUEST_PAYMENT` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  `ELECTRICITY_PAID` int(11) DEFAULT NULL,
  `REMARKS` varchar(255) DEFAULT NULL,
  `RENT` int(11) NOT NULL,
  `UPDATED_DATETIME` datetime DEFAULT NULL,
  `ELECTRICITY_DETAIL_ID` bigint(20) DEFAULT NULL,
  `GUEST_DETAIL_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKmnalgjrn53njesrqcyr10e568` (`ELECTRICITY_DETAIL_ID`),
  KEY `FK7tiardhu2jswcdxb2ywh2nvli` (`GUEST_DETAIL_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=113 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RM_GUEST_PAYMENT`
--

LOCK TABLES `RM_GUEST_PAYMENT` WRITE;
/*!40000 ALTER TABLE `RM_GUEST_PAYMENT` DISABLE KEYS */;
INSERT INTO `RM_GUEST_PAYMENT` VALUES (7,'2018-05-28 18:33:30',0,NULL,5500,'2018-05-28 18:33:30',NULL,7),(8,'2018-05-28 18:46:09',0,NULL,2000,'2018-05-28 19:01:00',NULL,8),(9,'2018-05-28 18:58:41',500,NULL,6200,'2019-01-09 23:04:23',NULL,9),(10,'2018-05-28 19:21:10',0,NULL,7500,'2018-05-28 19:21:10',NULL,10),(11,'2018-05-28 19:38:25',0,NULL,6500,'2018-05-28 19:38:25',NULL,11),(12,'2018-05-28 19:43:48',0,NULL,6500,'2018-05-28 19:43:48',NULL,12),(13,'2018-05-28 20:02:22',500,NULL,6000,'2019-01-08 23:16:27',NULL,13),(14,'2018-05-28 20:06:00',0,NULL,6000,'2018-05-28 20:06:00',NULL,14),(15,'2018-05-28 20:21:19',0,NULL,6500,'2018-05-28 20:21:19',NULL,15),(16,'2018-05-28 21:30:17',0,NULL,5500,'2018-05-28 21:30:17',NULL,16),(17,'2018-06-05 20:23:58',0,NULL,6000,'2018-06-05 20:23:58',NULL,17),(18,'2018-06-05 20:44:26',0,NULL,6200,'2018-06-05 20:44:26',NULL,18),(19,'2018-06-05 20:57:35',0,NULL,7500,'2018-06-05 20:57:35',NULL,19),(20,'2018-06-05 21:10:10',0,NULL,7500,'2018-06-05 21:10:10',NULL,20),(23,'2019-01-11 13:18:17',260,NULL,5500,'2019-01-26 14:09:47',NULL,7),(24,'2019-01-11 13:18:22',269,NULL,5500,'2019-01-26 14:10:29',NULL,8),(25,'2019-01-11 13:18:29',280,NULL,6200,'2019-01-26 14:07:49',NULL,9),(26,'2019-01-11 13:18:34',297,NULL,6500,'2019-01-26 14:10:56',NULL,21),(27,'2019-01-11 13:19:10',342,NULL,4000,'2019-01-26 14:11:55',NULL,22),(28,'2019-01-11 17:28:57',280,NULL,5700,'2019-01-19 15:49:58',NULL,23),(29,'2019-01-11 18:07:48',280,NULL,6200,'2019-01-26 14:07:12',NULL,24),(30,'2019-01-11 18:24:48',260,NULL,5500,'2019-01-26 14:10:00',NULL,25),(31,'2019-01-11 19:29:03',342,NULL,7500,'2019-01-26 14:12:07',NULL,26),(32,'2019-01-11 19:50:10',0,NULL,6500,'2019-01-26 14:11:21',NULL,27),(33,'2019-01-11 19:54:36',320,NULL,6500,'2019-01-26 14:08:39',NULL,28),(34,'2019-01-11 20:00:30',320,NULL,1200,'2019-01-26 14:09:15',NULL,29),(71,'2019-01-26 13:51:44',0,NULL,6200,'2019-01-26 13:51:44',NULL,30),(72,'2019-01-26 14:24:40',300,NULL,7500,'2019-01-26 14:40:10',NULL,31),(73,'2019-01-26 14:38:57',0,NULL,0,'2020-04-22 21:28:15',NULL,32),(74,'2019-01-26 15:35:48',0,NULL,0,'2020-04-23 23:21:20',NULL,33),(75,'2019-01-26 15:42:53',0,NULL,0,'2020-04-23 23:22:41',NULL,34),(76,'2019-01-26 16:06:47',300,NULL,7500,'2019-01-26 16:07:33',NULL,35),(77,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,7),(78,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,8),(79,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,9),(80,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,21),(81,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,22),(82,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,23),(83,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,24),(84,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,25),(85,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,26),(86,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,27),(87,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,28),(88,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,29),(89,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,30),(90,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,31),(91,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,32),(92,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,33),(93,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,34),(94,'2020-04-05 17:52:37',0,NULL,0,'2020-04-05 17:52:37',NULL,35),(95,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,7),(96,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,8),(97,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,9),(98,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,21),(99,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,22),(100,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,23),(101,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,24),(102,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,25),(103,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,26),(104,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,27),(105,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,28),(106,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,29),(107,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,30),(108,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,31),(109,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,32),(110,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,33),(111,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,34),(112,'2020-05-03 11:00:07',0,NULL,0,'2020-05-03 11:00:07',NULL,35);
/*!40000 ALTER TABLE `RM_GUEST_PAYMENT` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RM_MONTH_RECORDS`
--

DROP TABLE IF EXISTS `RM_MONTH_RECORDS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RM_MONTH_RECORDS` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MONTH` int(11) DEFAULT NULL,
  `YEAR` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UKa6smamb5nhy8d1omc2yn4wa0m` (`MONTH`,`YEAR`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RM_MONTH_RECORDS`
--

LOCK TABLES `RM_MONTH_RECORDS` WRITE;
/*!40000 ALTER TABLE `RM_MONTH_RECORDS` DISABLE KEYS */;
INSERT INTO `RM_MONTH_RECORDS` VALUES (1,1,2019),(2,4,2020),(3,5,2020);
/*!40000 ALTER TABLE `RM_MONTH_RECORDS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RM_PORTAL_MAPPING_INFO`
--

DROP TABLE IF EXISTS `RM_PORTAL_MAPPING_INFO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RM_PORTAL_MAPPING_INFO` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `DEPOSIT` int(11) NOT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `OCCUPIED` int(11) NOT NULL,
  `RENT` int(11) NOT NULL,
  `ROOM_NO` int(11) NOT NULL,
  `ADDRESS_ID` bigint(20) NOT NULL,
  `AIR_COND_TYPE` bigint(20) NOT NULL,
  `GENDER_TYPE` bigint(20) NOT NULL,
  `ROOM_TYPE` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKdujf5oo7acdoso873350cgr78` (`ADDRESS_ID`),
  KEY `FK2idfb2o8oudj6q0co4vifyog7` (`AIR_COND_TYPE`),
  KEY `FKemlpqrktf6d9p9owgwp672k2j` (`GENDER_TYPE`),
  KEY `FKtng5utoxkmmebwf4h67ers07u` (`ROOM_TYPE`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RM_PORTAL_MAPPING_INFO`
--

LOCK TABLES `RM_PORTAL_MAPPING_INFO` WRITE;
/*!40000 ALTER TABLE `RM_PORTAL_MAPPING_INFO` DISABLE KEYS */;
INSERT INTO `RM_PORTAL_MAPPING_INFO` VALUES (3,5500,'2 seater room with cooler',1,5500,10,3,7,2,10),(4,5500,'2 seater room with cooler',2,5500,9,3,7,2,10),(5,4000,'3 seater with ac ',2,7500,11,3,6,2,11),(7,6200,'5 seater with ac',4,6200,7,3,6,2,13),(8,7500,'3 seater with ac',0,7500,1,3,6,2,11),(9,6500,'4 seater with ac',3,6500,4,3,6,2,12),(10,6000,'5 seater with ac',0,6000,3,3,6,2,13),(12,7500,'3 seater with ac',2,7500,2,3,6,2,11),(13,7500,'3 seater with ac',0,7500,5,3,6,2,11),(14,7500,'3 seater with ac',0,7500,6,3,6,2,11),(15,7500,'4 seater with ac',2,6500,8,3,6,2,12),(16,7500,'Luxury room with all facilities',2,7500,12,3,6,2,11);
/*!40000 ALTER TABLE `RM_PORTAL_MAPPING_INFO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RM_ROOM_BOOKED`
--

DROP TABLE IF EXISTS `RM_ROOM_BOOKED`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RM_ROOM_BOOKED` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) DEFAULT NULL,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  `Description` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FNAME` varchar(255) DEFAULT NULL,
  `LNAME` varchar(255) DEFAULT NULL,
  `MOBILE` varchar(255) DEFAULT NULL,
  `RELATIVE_RELATION` varchar(255) DEFAULT NULL,
  `RELATIVE_MOBILE` varchar(255) DEFAULT NULL,
  `DEPOSITE` int(11) DEFAULT NULL,
  `UPDATED_DATETIME` datetime DEFAULT NULL,
  `MAPPING_ID` bigint(20) DEFAULT NULL,
  `CHECK_IN_DATE` varchar(255) DEFAULT NULL,
  `PROFESSION_ADDRESS` varchar(255) DEFAULT NULL,
  `PROFESSION` varchar(255) DEFAULT NULL,
  `DOB` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_d7fjv2oengk6elhd3auhgv9d5` (`EMAIL`),
  KEY `FKtiihe40ff1kpnm2hlifnt0bph` (`MAPPING_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RM_ROOM_BOOKED`
--

LOCK TABLES `RM_ROOM_BOOKED` WRITE;
/*!40000 ALTER TABLE `RM_ROOM_BOOKED` DISABLE KEYS */;
INSERT INTO `RM_ROOM_BOOKED` VALUES (7,1,'Guwahati Assam','2018-05-28 18:33:30','Cyber city DLF 2',NULL,'jenny','sinha','9319612737','Father','9957237941',2000,'2019-01-19 15:19:08',4,'20/9/2017','Gurgaon','job',NULL),(8,1,'519/20, gali no. 1, govind nagar, sonipat','2018-05-28 18:46:09','Dancing','mouryamamta1990@gmail.com','Mamta','Maurya','7042745106','father','9991584966',0,'2019-01-09 18:54:58',3,'01/10/2017','not working','web dev.',NULL),(9,1,'x 9/18A street no 9 brhampuri delhi 110053','2018-05-28 18:58:41','watching moviee, reading books etc','chauhananchal69@gmail.com','chanchal','chauhan','9953396372','brother','9599215802',0,'2018-05-28 18:58:41',7,'20/05/2018','sec 48 bestech tower gurgaon','job',NULL),(10,0,'house no 899 ward no 9 MP','2018-05-28 19:21:10','music , dance','julee.sgrl@gmail.com','julee','singh','8447720139','mom','9074321808',0,'2019-01-09 18:50:48',8,'18/05/2017','BK Montessori School','Teacher',NULL),(11,0,'d 616 sarojini nagar new delhi','2018-05-28 19:38:25','watching movies','ramya.rajendra4@gmail.com','Ramya','R','8585950924','father','7042065577',0,'2019-01-09 18:52:23',9,'3/4/2017','Remfry and sagar','secretary',NULL),(12,0,'chhindwara, madhya pradesh','2018-05-28 19:43:48','watching movies, travelling','sanshika1992@gmail.com','Anshika','Sahu','9630930467','friend','8660616014',0,'2019-01-09 18:52:37',9,'20/05/18','none','student',NULL),(13,0,'West bengal, 743144','2018-05-28 20:02:22','Music','shampa.gosh@gipe.ac.in','Shampa','Ghosh','8981987312','brother','8368898886',6000,'2019-01-09 18:51:36',10,'05-22-2018','Sector 31','Service',NULL),(14,0,'Bazar Shambha, Bijnor, U.P','2018-05-28 20:06:00','music, dance','harshitasoti49@gmail.com','Harshita','Soti','7678425381','Father','9719581957',6000,'2019-01-08 21:32:41',10,'05-03-2018','vipul agora,mg road, gurgaon','service',NULL),(15,0,'18-B Krishna Nagar Gole Ka Mandir, Gwalior','2018-05-28 20:21:19','dancing, singing','dikshabhado@gmail.com','Diksha','Bhadoria','8860698336','fiancee','9910340980',6500,'2019-01-09 18:52:51',9,'1/7/2016','Gurgaon','Business Analyst',NULL),(16,0,'Samalkha Panipat','2018-05-28 21:30:17','Drawing,Travelling & Music','varshamahato4@gmail.com','Varsha','Mahato','9996959251','brother','9996326303',3000,'2018-12-24 22:41:34',3,'1/05/2018','Paltech Cooling Towers','HR ',NULL),(17,0,'22 pant vihar saharanpur','2018-06-05 20:23:58','playing basketball','anur4244@gmail.com','Anu','Pal','9958791445','father','8750073990',0,'2019-01-09 18:51:21',10,'1-5-2018','rummy passion','software engg','20 dec 1990'),(18,0,'Gaurisagar Tiniali Near Mitong Bridge, Sivasagar, Assam','2018-06-05 20:44:26','dancing','Prajnyajyti.neog@gmail.com','Prajnya','Neog','9958978851','father','8638977739',0,'2019-01-09 18:54:17',7,'20-5-2018','JMD REgent Square, 5th floor, 513, MG Road','Private Job','22-12-1990'),(19,0,'Arihant Vihar Colony, Raipur','2018-06-05 20:57:35','dancing','neha07869@gmail.com','Neha','Wadhwani','8178427069','Brother','9827851700',3000,'2019-01-09 18:53:25',14,'10/10/2017','Ghitorni','Private Job','04/03/1990'),(20,0,'Bihar','2018-06-05 21:10:10','dancing','test@test.com','Susma','Singh','8810296902','self','8810296902',0,'2019-01-09 18:53:48',14,'13-05-2018','MG Road','Job','1991'),(21,1,'276-C LAVKUSH VIHAR, NAUBASTA, KANPUR-208021','2019-01-09 19:14:47','study','neetusrivastava504@gmail.com','NEETU','SRIVASTAVA','9625524134','FATHER','6306769096',0,'2019-01-09 19:14:47',5,'19-11-2018','PALTECH COOLING TOWERS ','PRIVATE JOB','02-10-1994'),(22,1,'village - narkanda, post office - narkanda, tehsil - kumarsain, distt - shimla (H.P) pin code - 171213','2019-01-09 19:26:12','listening music','0171divya@gmail.com','Divya','Rajput','7018680717','FATHER','9817250045',0,'2019-01-09 19:37:28',16,'13-12-2018','ALS Facility Mgt Behind Sahara Mall','Admin Executive','22-04-1997'),(23,1,'Hno. 104, Mega Dream Homes, Karamchari Nagar, Mini Bypass, Baeilly','2019-01-11 17:28:57','watching dance reality shows','guptakhushbu781@gmail.com','Khushboo','Gupta','9557219640','FATHER','9897348720',0,'2019-01-11 17:28:57',7,'01/07/2016','Concentrix','PRIVATE JOB','24/11/1991'),(24,1,'H.No. i - 956 hindalco colony renukoot sonebhadra','2019-01-11 18:07:48','playing chess','poonam.june91@gmail.com','Poonam','pal','8299258385','FATHER','7905070344',0,'2019-01-11 18:07:48',7,'01-08-2018','PALTECH COOLING TOWERS ','PRIVATE JOB','01-02-91'),(25,1,'AT/PO Singhpur , JAJPUR','2019-01-11 18:24:48','reading and internet surfing','sushmitapadhi904@gmail.com','Sushmita ','Padhi','9536318784','Father','7830682660',4470,'2019-01-11 19:46:44',4,'01/09/18','Globallogic,ASF Insignia','Associate Analyst','10/06/96'),(26,1,'ajanta electics works,redma chowk ','2019-01-11 19:29:03','travelling,dancing','kashish9404@gmail.com','kashish','sharma','8210466990','mom','6202615152',7000,'2019-01-11 19:29:03',16,'01/11/2018','mega city mall','study','07/10/1995'),(27,1,'2111, Sector-1, Rohtak','2019-01-11 19:50:10','Dance & All activities includes of physical & mental creativity','shivani.wadhwa09@gmail.com','Shivani','Wadhwa','9996869687','mother','8950176366',1500,'2019-01-11 19:50:10',5,'30-12-2018','Federal Mogul Motorparts','Assistant HR ','09-12-1994'),(28,1,'665, Basant Vihar, Muzaffarnagar','2019-01-11 19:54:36','travelling','29ayushigupta@gmail.com','Ayushi','Gupta','9045276743','Sister','7906021759',0,'2019-01-11 19:55:23',15,'01-12-2018','sector 44, plot no. 98, gurgaon','Graphic Designer','29-09-1994'),(29,1,'Budhana','2019-01-11 20:00:30','dancing, travelling','gulafshakhanmzn@gmail.com','Gulafsha','Khan','9897684966','self','9897684966',0,'2019-01-11 20:00:30',15,'23-10-2018','Near Gurudrona charya Metro Station','Graphic Designer','01-06-1996'),(30,1,'1-18-3/A Thulluruvari street Vidhyadharapuram Vijayawada-520012','2019-01-26 13:51:44','Singing,dancing &Cooking','pujak6793@gmail.com','Puja','Kumari','9963968891','FATHER','9949839632',0,'2019-01-26 13:51:44',7,'01-01-2019','NA','JOB searching','16-04-1997'),(31,1,'sagar fertilizer,nanded road,vasmat,hingoli,maharashtra','2019-01-26 14:24:40','travelling','patilmandakini25@gmail.com','mandakini','patil','8376049580','brother','9850185231',0,'2019-01-26 14:24:40',12,'13/11/16','american express,golf course road,gurgaon.','auditor','25/03/1990'),(32,1,'2/3044,Praduman Nagar, Saharanpur','2019-01-26 14:38:57','travelling','shrutichauhan742@gmail.com','Shruti','chauhan','8791031861','Mother','9319468757',3000,'2019-01-26 14:38:57',9,'30-12-2018','FabHotels','Associate-Talent acquisition','13-10-1995'),(33,1,'#245/9, VPO, BHOJPUR, TEHSIL , SUNDER NAGAR, DISTT. MANDI175002','2019-01-26 15:35:48','Reading Scriptures','nishiashok1331@gmail.com','NIHAYIKA','Sharma','6280745074','Brother','8860143896',5000,'2019-01-26 15:35:48',9,'03-07-2018','ANTS','PRIVATE JOB','22-11-1987'),(34,1,'GUPTA HARDWARE STORE, CIVIL LINES RAMPUR -244901','2019-01-26 15:42:53','Badminton, Travelling','NISHCHAYGUPTA17@GMAIL.COM','NISCHAY','GUPTA','8810483803','FATHER','9837315165',2500,'2020-04-23 23:24:57',9,'02-06-2018','IIFL 1st FLOOR VIPUL AGORA MG ROAD GURGAON-122002','RELATIONSHIP MANAGER','29-12-1994'),(35,1,'Shastri Nagar , Saharanpur ','2019-01-26 16:06:47','travelling','lavisha3@gmail.com','Lavisha','Arora','7291013393','FATHER','8445906736',1000,'2019-01-26 16:06:47',12,'1-3-2018','Conduent','Software Engineer','03-11-1990');
/*!40000 ALTER TABLE `RM_ROOM_BOOKED` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RM_USER`
--

DROP TABLE IF EXISTS `RM_USER`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RM_USER` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FNAME` varchar(255) DEFAULT NULL,
  `LNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `STATUS` tinyint(1) DEFAULT NULL,
  `UPDATED_DATETIME` datetime DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UKhy46j111na1qa1xxrvqw0w9cy` (`EMAIL`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RM_USER`
--

LOCK TABLES `RM_USER` WRITE;
/*!40000 ALTER TABLE `RM_USER` DISABLE KEYS */;
INSERT INTO `RM_USER` VALUES (1,'2018-05-22 16:53:12','wavespgrooms@gmail.com','admin','admin','12345678',1,'2018-05-22 16:53:12','admin'),(3,'2018-05-29 13:05:01','mouryamamta1990@gmail.com','mamta','morya','12345678',1,'2020-04-23 23:20:38','mouryamamta');
/*!40000 ALTER TABLE `RM_USER` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rm_enquiry`
--

DROP TABLE IF EXISTS `rm_enquiry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rm_enquiry` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `active` tinyint(1) DEFAULT NULL,
  `CREATED_DATETIME` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rm_enquiry`
--

LOCK TABLES `rm_enquiry` WRITE;
/*!40000 ALTER TABLE `rm_enquiry` DISABLE KEYS */;
INSERT INTO `rm_enquiry` VALUES (4,1,'2018-12-24 22:39:17','p@gmail.com','8787987678','preti'),(5,1,'2020-04-05 21:44:31','Rapali@test.com','1234556789','Rupali'),(6,1,'2020-04-05 21:50:51','kundu.anilkumar@gmail.com','9205209622','Raju Kumar');
/*!40000 ALTER TABLE `rm_enquiry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rm_portal_info`
--

DROP TABLE IF EXISTS `rm_portal_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rm_portal_info` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CODE` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) NOT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `VALUE` int(11) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rm_portal_info`
--

LOCK TABLES `rm_portal_info` WRITE;
/*!40000 ALTER TABLE `rm_portal_info` DISABLE KEYS */;
INSERT INTO `rm_portal_info` VALUES (1,'GEN01','Male',1,'GENDER',0),(2,'GEN02','Female',1,'GENDER',0),(3,'LOC01','Delhi',1,'LOCATION',0),(4,'LOC02','Gurgaon',1,'LOCATION',0),(5,'LOC03','Noida',1,'LOCATION',0),(6,'AC01','AC',1,'AC',0),(7,'AC02','Non AC',1,'AC',0),(9,'RM01','Single',1,'ROOM',1),(10,'RM02','Double',1,'ROOM',2),(11,'RM03','Tripple',1,'ROOM',3),(12,'RM04','Four',1,'ROOM',4),(13,'RM05','Five',1,'ROOM',5);
/*!40000 ALTER TABLE `rm_portal_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rm_role`
--

DROP TABLE IF EXISTS `rm_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rm_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rm_role`
--

LOCK TABLES `rm_role` WRITE;
/*!40000 ALTER TABLE `rm_role` DISABLE KEYS */;
INSERT INTO `rm_role` VALUES (1,'admin','admin'),(2,'user','user');
/*!40000 ALTER TABLE `rm_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_roles` (
  `userId` bigint(20) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FKgj4qhjn4pkrp1skesh0c3ll37` (`roleId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1),(3,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-03 11:07:43
