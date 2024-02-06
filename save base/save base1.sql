-- --------------------------------------------------------
-- Hôte:                         127.0.0.1
-- Version du serveur:           10.6.3-MariaDB - mariadb.org binary distribution
-- SE du serveur:                Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Listage de la structure de la base pour jardin
CREATE DATABASE IF NOT EXISTS `jardin` /*!40100 DEFAULT CHARACTER SET utf16 */;
USE `jardin`;

-- Listage de la structure de la table jardin. arrosage
CREATE TABLE IF NOT EXISTS `arrosage` (
  `idLegume` int(11) DEFAULT NULL,
  `frequence` int(11) DEFAULT NULL,
  `uniteFrequence` char(1) DEFAULT NULL,
  `quantitee` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- Listage des données de la table jardin.arrosage : ~0 rows (environ)
DELETE FROM `arrosage`;
/*!40000 ALTER TABLE `arrosage` DISABLE KEYS */;
/*!40000 ALTER TABLE `arrosage` ENABLE KEYS */;

-- Listage de la structure de la table jardin. enrichissement
CREATE TABLE IF NOT EXISTS `enrichissement` (
  `idParcelle` int(11) DEFAULT NULL,
  `engrais` varchar(50) DEFAULT NULL,
  `amendement` varchar(50) DEFAULT NULL,
  `dateEnrichissement` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- Listage des données de la table jardin.enrichissement : ~0 rows (environ)
DELETE FROM `enrichissement`;
/*!40000 ALTER TABLE `enrichissement` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrichissement` ENABLE KEYS */;

-- Listage de la structure de la table jardin. histo_culture
CREATE TABLE IF NOT EXISTS `histo_culture` (
  `idHisto` int(11) DEFAULT NULL,
  `idParcelle` int(11) DEFAULT NULL,
  `idLegume` int(11) DEFAULT NULL,
  `encombrement` varchar(7) DEFAULT NULL COMMENT 'complet ou demi',
  `dateDebut` varchar(8) DEFAULT NULL,
  `dateFin` varchar(8) DEFAULT NULL,
  `observation` varchar(150) DEFAULT NULL,
  `rendement` varchar(20) DEFAULT NULL,
  UNIQUE KEY `idHisto` (`idHisto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COMMENT='historique culture';

-- Listage des données de la table jardin.histo_culture : ~5 rows (environ)
DELETE FROM `histo_culture`;
/*!40000 ALTER TABLE `histo_culture` DISABLE KEYS */;
INSERT INTO `histo_culture` (`idHisto`, `idParcelle`, `idLegume`, `encombrement`, `dateDebut`, `dateFin`, `observation`, `rendement`) VALUES
	(1, 1, 7, 'DEMI', '20220801', '20221231', '', 'Pas terrible'),
	(2, 1, 9, 'DEMI', '20220601', '20230228', 'peu etre trop tassé', 'Moyen'),
	(3, 1, 2, 'PLEIN', '20230301', '20230630', 'pris un coup gel , semé trop tot', 'Moyen'),
	(4, 2, 2, 'PLEIN', '20220401', '20220630', '', ''),
	(5, 2, 9, 'PLEIN', '20220801', '20221231', '', '');
/*!40000 ALTER TABLE `histo_culture` ENABLE KEYS */;

-- Listage de la structure de la table jardin. histo_npk_ph
CREATE TABLE IF NOT EXISTS `histo_npk_ph` (
  `idParcelle` int(11) NOT NULL,
  `dateReleve` varchar(8) NOT NULL,
  `azote` double DEFAULT NULL,
  `potassium` double DEFAULT NULL,
  `phosphore` double DEFAULT NULL,
  `ph` double DEFAULT NULL,
  PRIMARY KEY (`idParcelle`,`dateReleve`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- Listage des données de la table jardin.histo_npk_ph : ~5 rows (environ)
DELETE FROM `histo_npk_ph`;
/*!40000 ALTER TABLE `histo_npk_ph` DISABLE KEYS */;
INSERT INTO `histo_npk_ph` (`idParcelle`, `dateReleve`, `azote`, `potassium`, `phosphore`, `ph`) VALUES
	(1, '20240131', 5, 3.1, 4.5, 4),
	(1, '20240216', 3, 4.5, 4, 3.5),
	(1, '20240331', 1, 2, 3, 4),
	(2, '20240125', 11, 9, 8, 8),
	(3, '20240202', 3, 2, 1, 1);
/*!40000 ALTER TABLE `histo_npk_ph` ENABLE KEYS */;

-- Listage de la structure de la table jardin. legume
CREATE TABLE IF NOT EXISTS `legume` (
  `idLegume` int(11) NOT NULL DEFAULT 0,
  `nom` varchar(50) NOT NULL DEFAULT '',
  `famille` varchar(50) NOT NULL DEFAULT '' COMMENT 'solanacée,crucifere ...',
  `type` varchar(50) DEFAULT NULL COMMENT 'legumineuse/feuille/racine',
  `exposition` varchar(50) NOT NULL DEFAULT '',
  `nbAnneeRotation` int(11) DEFAULT NULL,
  UNIQUE KEY `idLegume` (`idLegume`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COMMENT='fiche legume';

-- Listage des données de la table jardin.legume : ~10 rows (environ)
DELETE FROM `legume`;
/*!40000 ALTER TABLE `legume` DISABLE KEYS */;
INSERT INTO `legume` (`idLegume`, `nom`, `famille`, `type`, `exposition`, `nbAnneeRotation`) VALUES
	(1, 'tomate', 'Solanacées', 'FRUIT', 'Ensoleillé', 0),
	(2, 'patates', 'Solanacées', 'RACINE', 'Ombragé', 0),
	(3, 'Radis', 'Brassicacées', 'FEUILLE', 'Ensoleillé', 0),
	(4, 'courgette', 'Cucurbitacées', 'FRUIT', 'Ensoleillé', 2),
	(5, 'concombre', 'Cucurbitacées', 'FRUIT', 'Ensoleillé', 3),
	(6, 'Laitue', 'Astéracées', 'FEUILLE', 'Ensoleillé', 2),
	(7, 'mache', 'Valérianacées', 'FEUILLE', 'Ombragé', 2),
	(8, 'carotte', 'Apiacées', 'RACINE', 'Ombragé', 3),
	(9, 'poireaux', 'Alliacée', 'FEUILLE', 'Ensoleillé', 4),
	(10, 'basilic', 'Lamiacées', 'FEUILLE', 'Ensoleillé', 2),
	(11, 'betterave', 'Chénopodiacées', 'RACINE', 'Ensoleillé', 0),
	(12, 'zzzzzzzzzzzzz', 'Solanacées', 'SEC', 'Ensoleillé', 0);
/*!40000 ALTER TABLE `legume` ENABLE KEYS */;

-- Listage de la structure de la table jardin. parcelle
CREATE TABLE IF NOT EXISTS `parcelle` (
  `idParcelle` int(11) DEFAULT NULL,
  `nom` varchar(50) DEFAULT NULL,
  `largeur` double DEFAULT NULL,
  `longueur` double DEFAULT NULL,
  `exposition` varchar(10) DEFAULT NULL,
  UNIQUE KEY `idParcelle` (`idParcelle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- Listage des données de la table jardin.parcelle : ~13 rows (environ)
DELETE FROM `parcelle`;
/*!40000 ALTER TABLE `parcelle` DISABLE KEYS */;
INSERT INTO `parcelle` (`idParcelle`, `nom`, `largeur`, `longueur`, `exposition`) VALUES
	(1, 'parcelle 1 ', 3, 3.5, 'Sud-Ouest'),
	(2, 'parcelle 2', 2, 12, 'Sud-Ouest'),
	(3, 'entre 1', 0.4, 3, 'Sud-Ouest'),
	(4, 'parcelle 3', 3, 4, 'Sud-Ouest'),
	(5, 'parcelle4', 3, 4, 'Sud-Ouest'),
	(6, 'entre 2', 0.4, 3, 'Sud-Ouest'),
	(7, 'parcelle 5', 3, 4, 'Ouest'),
	(8, 'entre 3', 3, 4, 'Ouest'),
	(9, 'parcelle 6', 3, 4, 'Ouest'),
	(10, 'parcelle 7', 3, 4, 'Ouest'),
	(11, 'parcelle 8', 3, 4, 'Ouest'),
	(12, 'hauteur', 2, 8, 'Ouest'),
	(13, 'serre', 3, 4, 'Sud');
/*!40000 ALTER TABLE `parcelle` ENABLE KEYS */;

-- Listage de la structure de la table jardin. planning
CREATE TABLE IF NOT EXISTS `planning` (
  `idParcelle` int(11) DEFAULT NULL,
  `dateDebut` varchar(50) DEFAULT NULL,
  `dateMax` varchar(50) DEFAULT NULL,
  `tache` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- Listage des données de la table jardin.planning : ~0 rows (environ)
DELETE FROM `planning`;
/*!40000 ALTER TABLE `planning` DISABLE KEYS */;
/*!40000 ALTER TABLE `planning` ENABLE KEYS */;

-- Listage de la structure de la table jardin. semis
CREATE TABLE IF NOT EXISTS `semis` (
  `idLegume` int(11) DEFAULT NULL,
  `espacementLigneCm` int(11) DEFAULT NULL,
  `espacementPlantCm` int(11) DEFAULT NULL,
  `periode` varchar(25) DEFAULT NULL,
  `remarque` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- Listage des données de la table jardin.semis : ~0 rows (environ)
DELETE FROM `semis`;
/*!40000 ALTER TABLE `semis` DISABLE KEYS */;
/*!40000 ALTER TABLE `semis` ENABLE KEYS */;

-- Listage de la structure de la table jardin. voisinage_legume
CREATE TABLE IF NOT EXISTS `voisinage_legume` (
  `idLegume` int(11) NOT NULL,
  `idLegumeVoisinage` int(11) NOT NULL,
  `description` text NOT NULL,
  `type` varchar(2) NOT NULL COMMENT 'voisinage ok ou ko',
  UNIQUE KEY `idLegume_idLegumeVosinage` (`idLegume`,`idLegumeVoisinage`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COMMENT='vosinage bon ou nocif';

-- Listage des données de la table jardin.voisinage_legume : ~0 rows (environ)
DELETE FROM `voisinage_legume`;
/*!40000 ALTER TABLE `voisinage_legume` DISABLE KEYS */;
/*!40000 ALTER TABLE `voisinage_legume` ENABLE KEYS */;

-- Listage de la structure de la table jardin. voisinage_parcelle
CREATE TABLE IF NOT EXISTS `voisinage_parcelle` (
  `idParcelle` int(11) NOT NULL,
  `idParcelleVoisinage` int(11) NOT NULL,
  PRIMARY KEY (`idParcelle`,`idParcelleVoisinage`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf16;

-- Listage des données de la table jardin.voisinage_parcelle : ~0 rows (environ)
DELETE FROM `voisinage_parcelle`;
/*!40000 ALTER TABLE `voisinage_parcelle` DISABLE KEYS */;
INSERT INTO `voisinage_parcelle` (`idParcelle`, `idParcelleVoisinage`) VALUES
	(1, 2),
	(2, 1),
	(2, 3),
	(2, 4),
	(3, 2),
	(3, 4),
	(4, 2),
	(4, 3),
	(4, 5),
	(5, 4),
	(5, 6),
	(5, 7),
	(6, 5),
	(6, 7),
	(7, 5),
	(7, 6),
	(7, 8),
	(7, 9),
	(8, 7),
	(8, 9),
	(9, 7),
	(9, 8),
	(10, 11),
	(11, 10);
/*!40000 ALTER TABLE `voisinage_parcelle` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
