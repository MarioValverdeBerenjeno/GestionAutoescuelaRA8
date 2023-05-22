CREATE DATABASE  IF NOT EXISTS `gestionra8` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestionra8`;
-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: gestionra8
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `asistencia`
--

DROP TABLE IF EXISTS `asistencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asistencia` (
  `id_clase` int NOT NULL,
  `dni_alumno` varchar(9) NOT NULL,
  `evaluacion` float DEFAULT NULL,
  PRIMARY KEY (`id_clase`,`dni_alumno`),
  KEY `dni_alumno_idx` (`dni_alumno`),
  CONSTRAINT `dni_alumno` FOREIGN KEY (`dni_alumno`) REFERENCES `estudiante` (`dni`),
  CONSTRAINT `id_clase` FOREIGN KEY (`id_clase`) REFERENCES `claseconducir` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistencia`
--

LOCK TABLES `asistencia` WRITE;
/*!40000 ALTER TABLE `asistencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `asistencia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `claseconducir`
--

DROP TABLE IF EXISTS `claseconducir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `claseconducir` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `hora` time DEFAULT NULL,
  `id_vehiculo` int DEFAULT NULL,
  `dni_instructor` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_vehiculo_idx` (`id_vehiculo`),
  KEY `dni_instructor_idx` (`dni_instructor`),
  CONSTRAINT `dni_instructor` FOREIGN KEY (`dni_instructor`) REFERENCES `instructor` (`dni`),
  CONSTRAINT `id_vehiculo` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `claseconducir`
--

LOCK TABLES `claseconducir` WRITE;
/*!40000 ALTER TABLE `claseconducir` DISABLE KEYS */;
INSERT INTO `claseconducir` VALUES (1,'2023-05-27','13:30:00',1,'44444444S'),(2,'2023-07-13','12:25:00',1,'44444444S'),(3,'2023-06-24','10:45:00',2,'55555555X'),(4,'2023-08-05','18:30:00',2,'55555555X'),(5,'2023-09-30','14:00:00',3,'66666666V'),(6,'2023-10-13','15:25:00',3,'66666666V');
/*!40000 ALTER TABLE `claseconducir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiante` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `id_alumno` int DEFAULT NULL,
  `activado` tinyint DEFAULT '1',
  PRIMARY KEY (`dni`),
  KEY `id_usuario_idx` (`id_alumno`),
  CONSTRAINT `id_alumno` FOREIGN KEY (`id_alumno`) REFERENCES `usuario` (`idUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES ('11111111A','Juan Manuel','Colarte',5,1),('22222222D','Mario','Mentidero',6,1),('33333333L','Lorena','Cortadura',7,1);
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructor`
--

DROP TABLE IF EXISTS `instructor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructor` (
  `dni` varchar(9) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `id_instructor` int DEFAULT NULL,
  PRIMARY KEY (`dni`),
  KEY `id_usuario_idx` (`id_instructor`),
  CONSTRAINT `id_instructor` FOREIGN KEY (`id_instructor`) REFERENCES `usuario` (`idUsuario`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructor`
--

LOCK TABLES `instructor` WRITE;
/*!40000 ALTER TABLE `instructor` DISABLE KEYS */;
INSERT INTO `instructor` VALUES ('44444444S','Felix','Caleta',1),('55555555X','Raul Reyes','Marinero',2),('66666666V','Rafa','Gas',3);
/*!40000 ALTER TABLE `instructor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parteaveria`
--

DROP TABLE IF EXISTS `parteaveria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parteaveria` (
  `id_parte` int NOT NULL AUTO_INCREMENT,
  `id_vehiculo_averiado` int DEFAULT NULL,
  `dni_instructor_informante` varchar(9) DEFAULT NULL,
  `datos_averia` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_parte`),
  KEY `id_vehiculo_averiado_idx` (`id_vehiculo_averiado`),
  KEY `dni_instructor_informante_idx` (`dni_instructor_informante`),
  CONSTRAINT `dni_instructor_informante` FOREIGN KEY (`dni_instructor_informante`) REFERENCES `instructor` (`dni`),
  CONSTRAINT `id_vehiculo_averiado` FOREIGN KEY (`id_vehiculo_averiado`) REFERENCES `vehiculo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parteaveria`
--

LOCK TABLES `parteaveria` WRITE;
/*!40000 ALTER TABLE `parteaveria` DISABLE KEYS */;
INSERT INTO `parteaveria` VALUES (1,4,'44444444S','Fallo en el mototr');
/*!40000 ALTER TABLE `parteaveria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud`
--

DROP TABLE IF EXISTS `solicitud`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud` (
  `dni_estudiante` varchar(9) NOT NULL,
  `dni_instructor_clase` varchar(9) NOT NULL,
  `id_clase_conducir` int NOT NULL,
  PRIMARY KEY (`dni_estudiante`,`dni_instructor_clase`,`id_clase_conducir`),
  KEY `dni_instructor_idx` (`dni_instructor_clase`),
  KEY `id_clase_idx` (`id_clase_conducir`),
  CONSTRAINT `dni_estudiante` FOREIGN KEY (`dni_estudiante`) REFERENCES `estudiante` (`dni`),
  CONSTRAINT `dni_instructor_clase` FOREIGN KEY (`dni_instructor_clase`) REFERENCES `instructor` (`dni`),
  CONSTRAINT `id_clase_conducir` FOREIGN KEY (`id_clase_conducir`) REFERENCES `claseconducir` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud`
--

LOCK TABLES `solicitud` WRITE;
/*!40000 ALTER TABLE `solicitud` DISABLE KEYS */;
INSERT INTO `solicitud` VALUES ('11111111A','44444444S',1),('22222222D','44444444S',1),('11111111A','44444444S',2),('22222222D','66666666V',5);
/*!40000 ALTER TABLE `solicitud` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idUsuario` int NOT NULL AUTO_INCREMENT,
  `rol` varchar(45) NOT NULL,
  `contrasenya` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'INSTRUCTOR','pass','Instructor1'),(2,'INSTRUCTOR','pass','Instructor2'),(3,'INSTRUCTOR','pass','Instructor3'),(4,'ADMIN','Admin','Admin'),(5,'ALUMNO','pass','Juanma'),(6,'ALUMNO','pass','Mario'),(7,'ALUMNO','pass','Lorena');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehiculo`
--

DROP TABLE IF EXISTS `vehiculo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imagen` varchar(45) DEFAULT NULL,
  `modelo` varchar(45) DEFAULT NULL,
  `tipo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculo`
--

LOCK TABLES `vehiculo` WRITE;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` VALUES (1,'imagen/vehiculos/toyotacorola.png','Toyota corola','COCHE'),(2,'imagen/vehiculos/bmwr1000.png','BMW R1000','MOTO'),(3,'imagen/vehiculos/seatpanda.jpeg','Seat Panda','COCHE'),(4,'imagen/vehiculos/moto1.jpg','Audi R8','CAMION');
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-22  0:41:56
