-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 24 fév. 2022 à 09:39
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `paymybuddy`
--

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `soldes` double NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7m8ru44m93ukyb61dfxw0apf6` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `account`
--

INSERT INTO `account` (`id`, `soldes`, `user_id`) VALUES
(1, 8.55, 1),
(2, 2786.45, 2),
(3, 555, 3),
(4, 655, 4),
(5, 497.5, 5),
(6, 2497.5, 6);

-- --------------------------------------------------------

--
-- Structure de la table `bank`
--

DROP TABLE IF EXISTS `bank`;
CREATE TABLE IF NOT EXISTS `bank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bank_name` varchar(255) DEFAULT NULL,
  `iban` varchar(255) DEFAULT NULL,
  `soldes` double NOT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfmtgh08sjvnod9orx3kg6e2va` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `bank`
--

INSERT INTO `bank` (`id`, `bank_name`, `iban`, `soldes`, `account_id`) VALUES
(1, 'Société générale', 'FR765323514634654', 2000, 2),
(2, 'Société générale', 'FR765323514634654', 5000, 3),
(3, 'Société générale', 'FR765323514634654', 4000, 5),
(4, 'Société générale', 'FR765323514634654', 5000, 4),
(5, 'Société générale', 'FR765323514634654', 2000, 6);

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

DROP TABLE IF EXISTS `operation`;
CREATE TABLE IF NOT EXISTS `operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `account_from_id` bigint(20) DEFAULT NULL,
  `account_to_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsj35ws8ip7btad4p6380pslmx` (`account_from_id`),
  KEY `FKp8sqsf0xn1hq90o51ih291c71` (`account_to_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `operation`
--

INSERT INTO `operation` (`id`, `amount`, `description`, `account_from_id`, `account_to_id`) VALUES
(1, 155, 'test', 2, 4),
(2, 0.775, 'frais de transaction', 2, 1),
(3, 555, 'test', 2, 3),
(4, 2.775, 'frais de transaction', 2, 1),
(5, 500, 'test', 5, 4),
(6, 2.5, 'frais de transaction', 5, 1),
(7, 500, 'test', 6, 2),
(8, 2.5, 'frais de transaction', 6, 1);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `FKn82ha3ccdebhokx3a8fgdqeyy` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `password`, `role_id`) VALUES
(1, 'vishal@live.fr', 'Sandhiran', 'Vishal', '$2a$10$XbiZhOjc3RiDz77nOb16eeWCCjRZUyiPPdNrZnr5OqI4qdeYopQg6', 1),
(2, 'visalinthan@gmail.com', 'Visalinthan', 'Chanthirarajah', '$2a$10$7vwadASQOb./SGyQ9uOl3.iYFD/H1N4L4c071TStLOLrQ3v1eiHoq', 2),
(3, 'selliah59@hotmail.fr', 'Selliah', 'chanthirarajah', '$2a$10$USzmv1x8B3n6AU7Hd.Qwduh4CRjUF1iFJEcECK2MOmbg9aDytLjfm', 2),
(4, 'Csuventha@gmail.com', 'Suventha', 'Chanthirarajah', '$2a$10$Ui9pv9kgs9boY/iP1EMn1u7qIRpcFiSEB2.5DHPdasH1nhBK9Um0e', 2),
(5, 'test54@live.fr', 'Test54', 'gerad', '$2a$10$/pHBhXpwXZw9ypzidt5xj.j5ze9rXVnvBUiEcc5RLNU75s0VDllz.', 2),
(6, 'test@live.fr', 'Test', 'Test1', '$2a$10$MVPhXvgpg75xT4gps5bgZ.hMKy6kSrTVTgLM7f6J9tTXLsVEmIPqe', 2);

-- --------------------------------------------------------

--
-- Structure de la table `user_contact`
--

DROP TABLE IF EXISTS `user_contact`;
CREATE TABLE IF NOT EXISTS `user_contact` (
  `contact_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  KEY `FK9fm1vrfyjcs735xlykhnedyd7` (`user_id`),
  KEY `FKl1pish4f54ohno5twk14w8qbf` (`contact_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user_contact`
--

INSERT INTO `user_contact` (`contact_id`, `user_id`) VALUES
(4, 2),
(3, 2),
(2, 5),
(4, 5),
(3, 5),
(2, 6),
(4, 6);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK7m8ru44m93ukyb61dfxw0apf6` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `bank`
--
ALTER TABLE `bank`
  ADD CONSTRAINT `FKfmtgh08sjvnod9orx3kg6e2va` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`);

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `FKp8sqsf0xn1hq90o51ih291c71` FOREIGN KEY (`account_to_id`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FKsj35ws8ip7btad4p6380pslmx` FOREIGN KEY (`account_from_id`) REFERENCES `account` (`id`);

--
-- Contraintes pour la table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FKn82ha3ccdebhokx3a8fgdqeyy` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Contraintes pour la table `user_contact`
--
ALTER TABLE `user_contact`
  ADD CONSTRAINT `FK9fm1vrfyjcs735xlykhnedyd7` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKl1pish4f54ohno5twk14w8qbf` FOREIGN KEY (`contact_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
