# ************************************************************
# Sequel Ace SQL dump
# Version 20025
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# Host: localhost (MySQL 8.0.23)
# Database: bp_accelerator
# Generation Time: 2022-06-04 13:33:26 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table applicant
# ------------------------------------------------------------

DROP TABLE IF EXISTS `applicant`;

CREATE TABLE `applicant` (
  `id` int NOT NULL,
  `github_user` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `work_email_address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2khn3wnj8t659td9f7fl5jqk2` (`work_email_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `applicant` WRITE;
/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;

INSERT INTO `applicant` (`id`, `github_user`, `name`, `work_email_address`)
VALUES
	(60,'sanjoy-sust','Sanjoy','sanjoyd.cse@gmail.com');

/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table hibernate_sequence
# ------------------------------------------------------------

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;

INSERT INTO `hibernate_sequence` (`next_val`)
VALUES
	(62);

/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table project
# ------------------------------------------------------------

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `id` int NOT NULL,
  `duration_in_days` int NOT NULL,
  `employment_capacity` varchar(255) NOT NULL,
  `employment_mode` varchar(255) NOT NULL,
  `live_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `repository_link` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `start_year` int NOT NULL,
  `team_size` int NOT NULL,
  `applicant_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1jaulk818oqndrk4u4sxlij3t` (`applicant_id`),
  CONSTRAINT `FK1jaulk818oqndrk4u4sxlij3t` FOREIGN KEY (`applicant_id`) REFERENCES `applicant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;

INSERT INTO `project` (`id`, `duration_in_days`, `employment_capacity`, `employment_mode`, `live_url`, `name`, `repository_link`, `role`, `start_year`, `team_size`, `applicant_id`)
VALUES
	(61,1223,'PART_TIME','EMPLOYED','sanjoy.com','EMVs','github.com','Jr. Engineer',2011,12,60);

/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
