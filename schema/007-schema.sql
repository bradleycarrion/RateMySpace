-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: mbarclay_DB
-- ------------------------------------------------------
-- Server version	5.1.73

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
-- Table structure for table `Address`
--

DROP TABLE IF EXISTS `Address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Address` (
  `AddressID` int(11) NOT NULL AUTO_INCREMENT,
  `Street` varchar(45) DEFAULT NULL,
  `City` varchar(45) DEFAULT NULL,
  `State` varchar(45) DEFAULT NULL,
  `Zip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Address`
--

LOCK TABLES `Address` WRITE;
/*!40000 ALTER TABLE `Address` DISABLE KEYS */;
/*!40000 ALTER TABLE `Address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cost`
--

DROP TABLE IF EXISTS `Cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Cost` (
  `CostID` int(11) NOT NULL AUTO_INCREMENT,
  `HouseID` int(11) NOT NULL,
  `DateIN` datetime DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `DateOUT` datetime DEFAULT NULL,
  PRIMARY KEY (`CostID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cost`
--

LOCK TABLES `Cost` WRITE;
/*!40000 ALTER TABLE `Cost` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `House`
--

DROP TABLE IF EXISTS `House`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `House` (
  `HouseID` int(11) NOT NULL AUTO_INCREMENT,
  `AddressID` int(11) NOT NULL,
  `LandlordID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`HouseID`),
  CONSTRAINT `Address_ID` FOREIGN KEY (`AddressID`) REFERENCES `Address` (`AddressID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `House`
--

LOCK TABLES `House` WRITE;
/*!40000 ALTER TABLE `House` DISABLE KEYS */;
/*!40000 ALTER TABLE `House` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Landlord`
--

DROP TABLE IF EXISTS `Landlord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Landlord` (
  `LandlordID` int(11) NOT NULL AUTO_INCREMENT,
  `LordName` varchar(45) DEFAULT NULL UNIQUE,
  PRIMARY KEY (`LandlordID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Landlord`
--

LOCK TABLES `Landlord` WRITE;
/*!40000 ALTER TABLE `Landlord` DISABLE KEYS */;
/*!40000 ALTER TABLE `Landlord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Place`
--

DROP TABLE IF EXISTS `Place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Place` (
  `PlaceID` int(11) NOT NULL AUTO_INCREMENT,
  `LandlordID` int(11) DEFAULT NULL,
  `HouseID` int(11) DEFAULT NULL,
  `Date` DATETIME DEFAULT NULL,
  PRIMARY KEY (`PlaceID`),
  CONSTRAINT `Landlord_ID` FOREIGN KEY (`LandlordID`) REFERENCES `Landlord` (`LandlordID`),
  CONSTRAINT `House_ID` FOREIGN KEY (`HouseID`) REFERENCES `House` (`HouseID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Place`
--

LOCK TABLES `Place` WRITE;
/*!40000 ALTER TABLE `Place` DISABLE KEYS */;
/*!40000 ALTER TABLE `Place` ENABLE KEYS */;
UNLOCK TABLES;


DROP TABLE IF EXISTS `HouseReview`;
DROP TABLE IF EXISTS `Review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `HouseReview` (
  `HouseReviewID` int(11) NOT NULL AUTO_INCREMENT,
  `UserID` int(11) NOT NULL,
  `HouseID` int(11) NOT NULL,
  `Review` text DEFAULT NULL,
  PRIMARY KEY (`HouseReviewID`),
  CONSTRAINT `User_ID_hreview`  FOREIGN KEY (`UserID`)  REFERENCES `User` (`UserID`),
  CONSTRAINT `House_ID_hreview` FOREIGN KEY (`HouseID`) REFERENCES `House` (`HouseID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

LOCK TABLES `HouseReview` WRITE;
/*!40000 ALTER TABLE `HouseReview` DISABLE KEYS */;
/*!40000 ALTER TABLE `HouseReview` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS `LandlordReview`;

CREATE TABLE `LandlordReview`(
    `LandlordReviewID` int(11) NOT NULL AUTO_INCREMENT,
    `UserID` int(11) NOT NULL,
    `LandlordID` int(11) NOT NULL,
    `Review` text DEFAULT NULL,
    PRIMARY KEY (`LandlordReviewID`),
    CONSTRAINT `User_ID_lreview` FOREIGN KEY (`UserID`) REFERENCES `User` (`UserID`),
    CONSTRAINT `Landlord_ID_lreview` FOREIGN KEY (`LandlordID`) REFERENCES `Landlord` (`LandlordID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

LOCK TABLES `LandlordReview` WRITE;
/*!40000 ALTER TABLE `LandlordReview` DISABLE KEYS */;
/*!40000 ALTER TABLE `LandlordReview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `User` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-01 17:19:45
