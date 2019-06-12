-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 03, 2019 at 09:56 AM
-- Server version: 5.7.24-log
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `organisep`
--

-- --------------------------------------------------------

--
-- Table structure for table `commentaires`
--

CREATE TABLE `commentaires` (
  `commentaire_id` int(11) NOT NULL,
  `commentaire_contenu` varchar(1000) NOT NULL,
  `commentaire_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `commentaire_lu` tinyint(1) NOT NULL,
  `evenement_id` int(11) NOT NULL,
  `utilisateur_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `evenements`
--

CREATE TABLE `evenements` (
  `evenement_id` int(11) NOT NULL,
  `evenement_titre` varchar(1000) NOT NULL,
  `evenement_participant` int(11) NOT NULL,
  `evenement_date` date DEFAULT NULL,
  `evenement_time` time NOT NULL,
  `evenement_budget` double NOT NULL,
  `evenement_lien` varchar(1028) NOT NULL,
  `evenement_description` varchar(1028) NOT NULL,
  `evenement_validation` int(11) NOT NULL,
  `evenement_statut` int(11) NOT NULL,
  `evenement_image` varchar(256) NOT NULL,
  `utilisateur_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `evenements`
--

INSERT INTO `evenements` (`evenement_id`, `evenement_titre`, `evenement_participant`, `evenement_date`, `evenement_time`, `evenement_budget`, `evenement_lien`, `evenement_description`, `evenement_validation`, `evenement_statut`, `evenement_image`, `utilisateur_id`) VALUES
(1, 'Afterwork Shotgun ep.2', 0, '2019-06-01', '20:00:00', 0, '', 'ça va déchirer !!', 1, 1, 'img/imgEvent/eventShotGun.png', 4),
(2, 'PHNX', 0, '2019-05-11', '15:00:00', 0, '', '', 1, 1, 'img/imgEvent/eventJunior.png', 3);

-- --------------------------------------------------------

--
-- Table structure for table `reservation_salle`
--

CREATE TABLE `reservation_salle` (
  `salle_id` int(11) NOT NULL,
  `evenement_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `reservation_salle`
--

INSERT INTO `reservation_salle` (`salle_id`, `evenement_id`) VALUES
(1, 2),
(6, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ressources`
--

CREATE TABLE `ressources` (
  `evenement_id` int(11) NOT NULL,
  `ressource_nom` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ressources`
--

INSERT INTO `ressources` (`evenement_id`, `ressource_nom`) VALUES
(1, 'Fournitures Réseaux'),
(1, 'Café');

-- --------------------------------------------------------

--
-- Table structure for table `salle`
--

CREATE TABLE `salle` (
  `id_salle` int(11) NOT NULL,
  `salle_nom` varchar(128) NOT NULL,
  `nombre_de_place` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `salle`
--

INSERT INTO `salle` (`id_salle`, `salle_nom`, `nombre_de_place`) VALUES
(1, 'L012', 100),
(2, 'L115', 30),
(3, 'L114', 30),
(4, 'L108', 50),
(5, 'L206', 30),
(6, 'N16', 70),
(7, 'N18', 30);

-- --------------------------------------------------------

--
-- Table structure for table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `utilisateur_id` int(11) NOT NULL,
  `utilisateur_nom` varchar(128) NOT NULL,
  `utilisateur_email` varchar(256) NOT NULL,
  `utilisateur_telephone` varchar(10) NOT NULL,
  `utilisateur_statut` int(11) NOT NULL,
  `utilisateur_mot_de_passe` varchar(256) NOT NULL,
  `utilisateur_image` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `utilisateurs`
--

INSERT INTO `utilisateurs` (`utilisateur_id`, `utilisateur_nom`, `utilisateur_email`, `utilisateur_telephone`, `utilisateur_statut`, `utilisateur_mot_de_passe`, `utilisateur_image`) VALUES
(1, 'Transaharienne', 'TS@email.fr', '0635423187', 2, '1a1dc91c907325c69271ddf0c944bc72', 'img/imgUser/logots.png'),
(2, 'Administrateur', 'admin@email.fr', '0678456124', 1, '1a1dc91c907325c69271ddf0c944bc72', 'img/imgUser/icon.png'),
(3, 'Junior ISEP', 'JI@email.fr', '0682634193', 2, '1a1dc91c907325c69271ddf0c944bc72', 'img/imgUser/logoJunior.png'),
(4, 'Shot Gun', 'SG@email.fr', '0283617354', 2, '1a1dc91c907325c69271ddf0c944bc72', 'img/imgUser/logoShotGun.png'),
(5, 'Move Your AS', 'MYA@email.fr', '0673910347', 2, '1a1dc91c907325c69271ddf0c944bc72', 'img/imgUser/logoMoveYourAS.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `commentaires`
--
ALTER TABLE `commentaires`
  ADD PRIMARY KEY (`commentaire_id`),
  ADD KEY `evenement_id` (`evenement_id`),
  ADD KEY `utilisateur_id` (`utilisateur_id`);

--
-- Indexes for table `evenements`
--
ALTER TABLE `evenements`
  ADD PRIMARY KEY (`evenement_id`),
  ADD KEY `utilisateur_id` (`utilisateur_id`);

--
-- Indexes for table `reservation_salle`
--
ALTER TABLE `reservation_salle`
  ADD KEY `salle_id` (`salle_id`),
  ADD KEY `evenement_id` (`evenement_id`);

--
-- Indexes for table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`id_salle`);

--
-- Indexes for table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`utilisateur_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `commentaires`
--
ALTER TABLE `commentaires`
  MODIFY `commentaire_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `evenements`
--
ALTER TABLE `evenements`
  MODIFY `evenement_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `salle`
--
ALTER TABLE `salle`
  MODIFY `id_salle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `utilisateur_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commentaires`
--
ALTER TABLE `commentaires`
  ADD CONSTRAINT `commentaires_ibfk_1` FOREIGN KEY (`evenement_id`) REFERENCES `evenements` (`evenement_id`),
  ADD CONSTRAINT `commentaires_ibfk_2` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateurs` (`utilisateur_id`);

--
-- Constraints for table `evenements`
--
ALTER TABLE `evenements`
  ADD CONSTRAINT `evenements_ibfk_1` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateurs` (`utilisateur_id`);

--
-- Constraints for table `reservation_salle`
--
ALTER TABLE `reservation_salle`
  ADD CONSTRAINT `reservation_salle_ibfk_1` FOREIGN KEY (`salle_id`) REFERENCES `salle` (`id_salle`),
  ADD CONSTRAINT `reservation_salle_ibfk_2` FOREIGN KEY (`evenement_id`) REFERENCES `evenements` (`evenement_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
