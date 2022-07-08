-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 05 juil. 2022 à 13:37
-- Version du serveur : 10.4.20-MariaDB
-- Version de PHP : 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gdt_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `carpool`
--

CREATE TABLE IF NOT EXISTS `carpool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `arrival_address` varchar(255) NOT NULL,
  `available_seats` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `departure_address` varchar(255) NOT NULL,
  `distance` decimal(19,2) DEFAULT NULL,
  `duration` decimal(19,2) DEFAULT NULL,
  `creator_id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5j9k1ka6vrtuptaiindv5hn0p` (`creator_id`),
  KEY `FKdfj5w4m48x4jr7ewhesqo0fh5` (`vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `carpool`
--

INSERT INTO `carpool` (`id`, `arrival_address`, `available_seats`, `date`, `departure_address`, `distance`, `duration`, `creator_id`, `vehicle_id`) VALUES
(1, 'Lille', 2, '2022-07-05 13:36:03', 'Tourcoing', NULL, NULL, 1, 1),
(2, 'Lille', 2, '2022-07-20 13:36:03', 'Tourcoing', NULL, NULL, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `company_vehicle`
--

CREATE TABLE IF NOT EXISTS `company_vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) NOT NULL,
  `license_plate` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `seats` int(11) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `company_vehicle`
--

INSERT INTO `company_vehicle` (`id`, `brand`, `license_plate`, `model`, `category`, `photo`, `seats`, `status`) VALUES
(1, 'Opel', 'XX-123-XX', 'Corsa', 'BERLINE_TAILLE_S', NULL, 3, 'EN_SERVICE'),
(2, 'Renault', 'XX-999-XX', 'Clio', 'CITADINE_POLYVALENTE', NULL, 2, 'HORS_SERVICE'),
(3, 'Peugeot', 'AA-456-ZZ', '3008', 'BERLINE_TAILLE_L', NULL, 7, 'EN_REPARATION');

-- --------------------------------------------------------

--
-- Structure de la table `company_vehicle_coordinates`
--

CREATE TABLE IF NOT EXISTS `company_vehicle_coordinates` (
  `company_vehicle_id` int(11) NOT NULL,
  `coordinates_id` int(11) NOT NULL,
  PRIMARY KEY (`company_vehicle_id`,`coordinates_id`),
  KEY `FKrej5vu6h6o1iwmj8tetu9cnt4` (`coordinates_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `company_vehicle_coordinates`
--

INSERT INTO `company_vehicle_coordinates` (`company_vehicle_id`, `coordinates_id`) VALUES
(1, 1),
(1, 3),
(2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `coordinates`
--

CREATE TABLE IF NOT EXISTS `coordinates` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `latitude` decimal(9,6) NOT NULL,
  `longitude` decimal(9,6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `coordinates`
--

INSERT INTO `coordinates` (`id`, `date`, `latitude`, `longitude`) VALUES
(1, '2022-07-05 12:25:53', '25.854000', '32.587400'),
(2, '2022-07-05 12:26:02', '44.552400', '85.225000'),
(3, '2022-07-05 12:26:10', '104.250000', '95.258741');

-- --------------------------------------------------------

--
-- Structure de la table `private_vehicle`
--

CREATE TABLE IF NOT EXISTS `private_vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brand` varchar(255) NOT NULL,
  `license_plate` varchar(255) NOT NULL,
  `model` varchar(255) NOT NULL,
  `owner_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcsqm530l44jcpwm06yt7c1ml8` (`owner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `private_vehicle`
--

INSERT INTO `private_vehicle` (`id`, `brand`, `license_plate`, `model`, `owner_id`) VALUES
(1, 'Opel', 'DF-025-SW', 'Astra', 1),
(2, 'Citroen', 'RT-984-MD', 'C3', 2);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE IF NOT EXISTS `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ADMIN'),
(2, 'DRIVER'),
(3, 'COLLAB');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `identification_number` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `license` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `photo` varchar(255) DEFAULT NULL,
  `telephone` decimal(19,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `firstname`, `identification_number`, `lastname`, `license`, `password`, `photo`, `telephone`) VALUES
(1, 'test@gmail.com', 'Test', 'C001', 'Test', NULL, 'Test123!', NULL, NULL),
(2, 'test2@gmail.com', 'Toto', 'C002', 'Test', NULL, 'Test123!', NULL, NULL),
(3, 'admin@gmail.com', 'Admin', 'C002', 'Test', NULL, 'ADMin123!', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `user_carpool_reservations`
--

CREATE TABLE IF NOT EXISTS `user_carpool_reservations` (
  `user_id` int(11) NOT NULL,
  `carpool_reservations_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`carpool_reservations_id`),
  KEY `FKrflix070431h8i282relwod3w` (`carpool_reservations_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user_carpool_reservations`
--

INSERT INTO `user_carpool_reservations` (`user_id`, `carpool_reservations_id`) VALUES
(2, 1),
(3, 1),
(2, 2);

-- --------------------------------------------------------

--
-- Structure de la table `user_roles`
--

CREATE TABLE IF NOT EXISTS `user_roles` (
  `user_id` int(11) NOT NULL,
  `roles_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`roles_id`),
  KEY `FKj9553ass9uctjrmh0gkqsmv0d` (`roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user_roles`
--

INSERT INTO `user_roles` (`user_id`, `roles_id`) VALUES
(1, 3),
(2, 3),
(3, 1),
(3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `vehicle_reservation`
--

CREATE TABLE IF NOT EXISTS `vehicle_reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `return_date` datetime NOT NULL,
  `start_date` datetime NOT NULL,
  `client_id` int(11) NOT NULL,
  `driver_id` int(11) DEFAULT NULL,
  `vehicle_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5ot9xd2bjip1nessxyjnt179a` (`client_id`),
  KEY `FKiy1nj5kupisdfj4wy4q03hy2` (`driver_id`),
  KEY `FKmahqblbck1dnuuse6jwtgsed2` (`vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `vehicle_reservation`
--

INSERT INTO `vehicle_reservation` (`id`, `return_date`, `start_date`, `client_id`, `driver_id`, `vehicle_id`) VALUES
(1, '2022-07-05 13:34:20', '2022-07-05 13:34:20', 1, NULL, 3),
(2, '2022-07-05 13:34:34', '2022-07-05 13:34:34', 2, 3, 2);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `carpool`
--
ALTER TABLE `carpool`
  ADD CONSTRAINT `FK5j9k1ka6vrtuptaiindv5hn0p` FOREIGN KEY (`creator_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKdfj5w4m48x4jr7ewhesqo0fh5` FOREIGN KEY (`vehicle_id`) REFERENCES `private_vehicle` (`id`);

--
-- Contraintes pour la table `company_vehicle_coordinates`
--
ALTER TABLE `company_vehicle_coordinates`
  ADD CONSTRAINT `FK4bhasxdf6j0t8vd5u6oc949ev` FOREIGN KEY (`company_vehicle_id`) REFERENCES `company_vehicle` (`id`),
  ADD CONSTRAINT `FKrej5vu6h6o1iwmj8tetu9cnt4` FOREIGN KEY (`coordinates_id`) REFERENCES `coordinates` (`id`);

--
-- Contraintes pour la table `private_vehicle`
--
ALTER TABLE `private_vehicle`
  ADD CONSTRAINT `FKcsqm530l44jcpwm06yt7c1ml8` FOREIGN KEY (`owner_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `user_carpool_reservations`
--
ALTER TABLE `user_carpool_reservations`
  ADD CONSTRAINT `FKkau8i2cp24pg4a0cisk6285ns` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKrflix070431h8i282relwod3w` FOREIGN KEY (`carpool_reservations_id`) REFERENCES `carpool` (`id`);

--
-- Contraintes pour la table `user_roles`
--
ALTER TABLE `user_roles`
  ADD CONSTRAINT `FK55itppkw3i07do3h7qoclqd4k` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKj9553ass9uctjrmh0gkqsmv0d` FOREIGN KEY (`roles_id`) REFERENCES `role` (`id`);

--
-- Contraintes pour la table `vehicle_reservation`
--
ALTER TABLE `vehicle_reservation`
  ADD CONSTRAINT `FK5ot9xd2bjip1nessxyjnt179a` FOREIGN KEY (`client_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKiy1nj5kupisdfj4wy4q03hy2` FOREIGN KEY (`driver_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKmahqblbck1dnuuse6jwtgsed2` FOREIGN KEY (`vehicle_id`) REFERENCES `company_vehicle` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
