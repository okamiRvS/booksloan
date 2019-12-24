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
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autore`
--

LOCK TABLES `autore` WRITE;
/*!40000 ALTER TABLE `autore` DISABLE KEYS */;
INSERT INTO `autore` VALUES (1,'Andrea','Camilleri'),(3,'J.R.R','Tolkien'),(4,'J.K','Rowling'),(5,'Jane','Austen'),(6,'Donna','Tartt'),(7,'Douglas','Adams'),(8,'Joël',' Dicker'),(11,'Elena','Ferrante'),(12,'Stefania','Auci'),(13,'Immanuel','Kant'),(14,'Isabel','Allende');
/*!40000 ALTER TABLE `autore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `copia`
--

DROP TABLE IF EXISTS `copia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `copia` (
  `isbn` int(11) NOT NULL,
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
INSERT INTO `copia` VALUES (9,101,0),(11,101,1),(90,101,1),(98,101,1),(123,101,0),(222,102,1),(333,106,1),(335,101,1),(353,103,1),(456,101,1),(789,101,1),(790,101,1),(791,101,1),(792,101,1),(793,101,1),(1239,101,1),(1234545667,101,1);
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
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (101,'Guida galattica per gli autostoppisti',1981),(102,'Il cardellino',2013),(103,'L\'amica geniale',2011),(104,'Quindici giorni con Montalbano',1999),(105,'Orgoglio e pregiudizio',1813),(106,'Lo hobbit',1937),(107,'Emma',1915),(108,'Harry Potter e la pietra filosofale',1997),(109,'Dio di illusioni',1992),(110,'Harry Potter e la camera dei segreti',1998),(113,'La verità sul caso Harry Quebert',2012),(116,'I Leoni di Sicilia',2017),(118,'Critica della ragion pura',1781),(126,'Il gioco di Ripper',2013),(127,'Storia del nuovo cognome',2012),(130,'Storia della bambina perduta',2014),(131,'Harry Potter e il prigioniero di Azkaban',1999);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestito`
--

DROP TABLE IF EXISTS `prestito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestito` (
  `data_inizio` datetime DEFAULT CURRENT_TIMESTAMP,
  `data_consegna` datetime DEFAULT NULL,
  `n_tessera` int(11) NOT NULL,
  `isbn` int(11) NOT NULL,
  PRIMARY KEY (`n_tessera`,`isbn`),
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
INSERT INTO `prestito` VALUES ('2019-12-17 10:38:48','2020-01-17 10:38:48',123,333),('2019-12-22 00:17:48','2020-01-22 00:17:48',321,9);
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
INSERT INTO `scritto` VALUES (101,7),(102,6),(103,11),(104,1),(105,5),(106,3),(107,5),(108,4),(109,6),(110,4),(113,8),(116,12),(118,13),(126,14),(127,11),(130,11);
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
INSERT INTO `sequel` VALUES (103,127),(108,110),(110,131),(127,130);
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
  `nome` text,
  `cognome` text,
  `e_mail` text,
  `ruolo` text NOT NULL,
  PRIMARY KEY (`n_tessera`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (123,'$2a$04$.nMS0e9Lq9D.LYWpVhwMkuIwOAwwYPjhGWFCy4Hxmx5qZA4E.0GmS','umbe95','Umberto','Cocca',NULL,'ROLE_ADMIN'),(321,'$2a$04$.nMS0e9Lq9D.LYWpVhwMkuIwOAwwYPjhGWFCy4Hxmx5qZA4E.0GmS','Pino74','Pino','Fumagalli',NULL,'ROLE_USER');
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

-- Dump completed on 2019-12-24 19:05:24
