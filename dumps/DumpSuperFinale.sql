CREATE DATABASE  IF NOT EXISTS `booksloan` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `booksloan`;
-- MySQL dump 10.13  Distrib 8.0.18, for macos10.14 (x86_64)
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
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autore`
--

LOCK TABLES `autore` WRITE;
/*!40000 ALTER TABLE `autore` DISABLE KEYS */;
INSERT INTO `autore` VALUES (21,'Douglas','Adams'),(22,'J.K.','Rowling'),(23,'Donna','Tartt'),(24,'Elena','Ferrante'),(25,'Andrea','Camilleri'),(26,'Jane','Austen'),(27,'J. R. R.','Tolkien'),(28,'Jane','Austin'),(29,'Donna','Tartt'),(31,'Joël','Dicker'),(32,'Cassandra','Claire'),(33,'Holly','Black'),(40,'David','Grossman');
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
INSERT INTO `copia` VALUES (9780582186552,179,1),(9780642556233,181,0),(9781447297017,174,1),(9788683591732,174,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=192 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (174,'Guida galattica per gli autostoppisti',1981),(175,'Il cardellino',2013),(176,'L\'amica geniale',2011),(177,'Quindici giorni con Montalbano',1999),(178,'Orgoglio e pregiudizio',1813),(179,'Lo hobbit',1937),(180,'Emma',1915),(181,'Harry Potter e la pietra filosofale',1997),(182,'Dio di illusioni',1992),(183,'Harry Potter e la camera dei segreti',1998),(184,'La verità sul caso Harry Quebert',2012),(185,'L\'anno di ferro',2014),(186,'Harry Potter e il prigioniero di Azkaban',1999),(187,'Harry Potter e il calice di fuoco',2003),(188,'Harry Potter e il principe mezzo sangue',2005),(189,'Harry Potter e i doni della morte',2007),(190,'Qualcuno con cui correre',2008),(191,'Il guanto di rame',2015);
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
INSERT INTO `prestito` VALUES (9780642556233,'2020-01-12 21:41:08','2020-02-12 21:41:08',321);
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
INSERT INTO `scritto` VALUES (174,21),(175,23),(176,24),(177,25),(178,26),(179,27),(180,28),(181,22),(182,29),(183,30),(184,31),(185,32),(185,33),(186,22),(186,34),(187,22),(187,35),(188,22),(188,36),(189,22),(189,37),(190,40),(191,32),(191,33),(191,38),(191,39);
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
INSERT INTO `sequel` VALUES (181,183),(183,186),(185,191),(186,187),(187,188),(188,189);
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

-- Dump completed on 2020-01-12 22:02:35
