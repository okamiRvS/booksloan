CREATE DATABASE  IF NOT EXISTS `booksloan` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `booksloan`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: booksloan
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `autore`
--

DROP TABLE IF EXISTS `autore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autore` (
  `id_autore` int(11) NOT NULL AUTO_INCREMENT,
  `nome` text,
  `cognome` text,
  PRIMARY KEY (`id_autore`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autore`
--

LOCK TABLES `autore` WRITE;
/*!40000 ALTER TABLE `autore` DISABLE KEYS */;
INSERT INTO `autore` VALUES (1,'Andrea','Camilleri'),(3,'J.R.R','Tolkien'),(4,'J.K','Rowling'),(5,'Jane','Austen'),(6,'Donna','Tartt'),(7,'Douglas','Adams'),(8,'Joël',' Dicker'),(11,'Elena','Ferrante'),(12,'Stefania','Auci'),(13,'Immanuel','Kant'),(14,'Isabel','Allende'),(15,'566','567'),(16,',','.'),(17,'d','456'),(18,'Cassandra','Clare'),(19,'Holly','Black'),(20,'David','Grossman');
/*!40000 ALTER TABLE `autore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `copia`
--

DROP TABLE IF EXISTS `copia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `copia` (
  `isbn` bigint(13) NOT NULL,
  `id` int(11) DEFAULT NULL,
  `disponibilita` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`isbn`),
  UNIQUE KEY `isbn` (`isbn`),
  KEY `fk_copia_libro1_idx` (`id`),
  CONSTRAINT `fk_copia_libro1` FOREIGN KEY (`id`) REFERENCES `libro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copia`
--

LOCK TABLES `copia` WRITE;
/*!40000 ALTER TABLE `copia` DISABLE KEYS */;
INSERT INTO `copia` VALUES (436024268727,102,0),(771274296670,103,1),(1234567890123,101,1),(1234567891012,101,1),(1455240780251,108,1),(1602611801452,105,1),(1869017245280,109,1),(1935041498673,102,1),(1959986746058,102,1),(3066158920110,169,1),(3259114144883,173,1),(4294167724113,102,1),(4422708361574,110,1),(4446832784020,103,1),(4692361282412,109,1),(4796315712032,170,1),(4868390461762,108,1),(4874395246219,166,1),(5131939951667,107,1),(5463433387715,104,1),(5530463648106,106,1),(5665270917065,168,0),(5750069615112,106,1),(5822286195628,170,1),(7022777886240,105,1),(7073226540715,103,1),(7120930491825,168,1),(7740995266307,104,1),(7780140076338,107,1),(7921316079284,113,1),(9206680348715,104,1),(9852096364127,103,1),(9863836736401,105,1),(9876428643556,102,1);
/*!40000 ALTER TABLE `copia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titolo` text,
  `anno` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (101,'Guida galattica per gli autostoppisti',1981),(102,'Il cardellino',2013),(103,'L\'amica geniale',2011),(104,'Quindici giorni con Montalbano',1999),(105,'Orgoglio e pregiudizio',1813),(106,'Lo hobbit',1937),(107,'Emma',1915),(108,'Harry Potter e la pietra filosofale',1997),(109,'Dio di illusioni',1992),(110,'Harry Potter e la camera dei segreti',1998),(113,'La verità sul caso Harry Quebert',2012),(166,'L\'anno di ferro',2014),(167,'Harry Potter e il prigioniero di Azkaban',1999),(168,'Il guanto di rame',2015),(169,'Harry Potter e il calice di fuoco',2003),(170,'Harry Potter e il principe mezzosangue',2005),(172,'Qualcuno con cui correre',2008),(173,'Harry Potter e i Doni della Morte',2007);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestito`
--

DROP TABLE IF EXISTS `prestito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestito` (
  `isbn` bigint(13) NOT NULL,
  `data_inizio` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_consegna` datetime DEFAULT NULL,
  `n_tessera` int(11) NOT NULL,
  PRIMARY KEY (`isbn`),
  UNIQUE KEY `isbn` (`isbn`),
  KEY `n_tessera_idx` (`n_tessera`),
  KEY `fk_copia_isbn_idx` (`isbn`),
  CONSTRAINT `fk_copia_isbn` FOREIGN KEY (`isbn`) REFERENCES `copia` (`isbn`),
  CONSTRAINT `n_tessera` FOREIGN KEY (`n_tessera`) REFERENCES `utente` (`n_tessera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestito`
--

LOCK TABLES `prestito` WRITE;
/*!40000 ALTER TABLE `prestito` DISABLE KEYS */;
INSERT INTO `prestito` VALUES (436024268727,'2020-01-12 14:15:41','2020-02-12 14:15:41',321),(5665270917065,'2020-01-12 14:15:35','2020-02-12 14:15:35',321);
/*!40000 ALTER TABLE `prestito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scritto`
--

DROP TABLE IF EXISTS `scritto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `scritto` (
  `id` int(11) NOT NULL,
  `id_autore` int(11) NOT NULL,
  PRIMARY KEY (`id`,`id_autore`),
  KEY `fk_scritto_libro1_idx` (`id`),
  KEY `fk_scritto_libro2_idx` (`id_autore`),
  CONSTRAINT `fk_scritto_libro1` FOREIGN KEY (`id`) REFERENCES `libro` (`id`),
  CONSTRAINT `fk_scritto_libro2` FOREIGN KEY (`id_autore`) REFERENCES `autore` (`id_autore`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scritto`
--

LOCK TABLES `scritto` WRITE;
/*!40000 ALTER TABLE `scritto` DISABLE KEYS */;
INSERT INTO `scritto` VALUES (101,7),(102,6),(103,11),(104,1),(105,5),(106,3),(107,5),(108,4),(109,6),(110,4),(113,8),(166,18),(166,19),(167,4),(168,18),(168,19),(169,4),(170,4),(172,20),(173,4);
/*!40000 ALTER TABLE `scritto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sequel`
--

DROP TABLE IF EXISTS `sequel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sequel` (
  `id_1` int(11) NOT NULL,
  `id_2` int(11) NOT NULL,
  PRIMARY KEY (`id_1`,`id_2`),
  KEY `fk_libro_has_libro_libro1_idx` (`id_1`),
  KEY `fk_sequel_libro2_idx` (`id_2`),
  CONSTRAINT `fk_sequel_libro1` FOREIGN KEY (`id_1`) REFERENCES `libro` (`id`),
  CONSTRAINT `fk_sequel_libro2` FOREIGN KEY (`id_2`) REFERENCES `libro` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sequel`
--

LOCK TABLES `sequel` WRITE;
/*!40000 ALTER TABLE `sequel` DISABLE KEYS */;
INSERT INTO `sequel` VALUES (108,110),(110,167),(166,168),(167,169),(169,170),(170,173);
/*!40000 ALTER TABLE `sequel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utente` (
  `n_tessera` int(11) NOT NULL,
  `password` text NOT NULL,
  `username` text,
  `e_mail` text,
  `ruolo` text NOT NULL,
  `nome` text,
  `cognome` text,
  `residenza` text,
  `tipologia_contratto` text,
  PRIMARY KEY (`n_tessera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (123,'$2a$04$.nMS0e9Lq9D.LYWpVhwMkuIwOAwwYPjhGWFCy4Hxmx5qZA4E.0GmS','umbe95','umbe95@gmail.com','ROLE_ADMIN','Umberto','Cocca',NULL,'indeterminato'),(321,'$2a$04$.nMS0e9Lq9D.LYWpVhwMkuIwOAwwYPjhGWFCy4Hxmx5qZA4E.0GmS','Pino74','pino74@gmail.com','ROLE_USER','Pino','Fumagalli','Milano',NULL),(456,'$2a$10$nvaef1Kt7muRc6x1auAAnuX8IC2fwwFVR8U/PznhD8G1B2977RO6S','Ciccio10','ciccio10@gmail.com','ROLE_ADMIN','Francesco','Cozza',NULL,'determinato');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-12 14:17:59
